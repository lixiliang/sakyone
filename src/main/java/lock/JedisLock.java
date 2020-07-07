package lock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * Redis distributed lock implementation (fork by Bruno Bossola <bbossola@gmail.com>)
 *
 * @author Alois Belaska <alois.belaska@gmail.com> modified by lixiliang
 */
public class JedisLock {
    // 轮询获取锁的时间间隔
    private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = Integer.getInteger("com.github.jedis.lock.acquiry.resolution.millis", 100);
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisLock.class);
    private static final Lock NO_LOCK = new Lock(new UUID(0L, 0L), 0L);
    private static final int ONE_SECOND = 1000;
    // 锁的占用时长
    private static final int DEFAULT_EXPIRY_TIME_MILLIS = Integer.getInteger("com.github.jedis.lock.expiry.millis", 60 * ONE_SECOND);
    // 或取锁的超时时间
    private static final int DEFAULT_ACQUIRE_TIMEOUT_MILLIS = Integer.getInteger("com.github.jedis.lock.acquiry.millis", 10 * ONE_SECOND);

    private final String lockKeyPath;

    private final int lockExpiryInMillis;
    private final int acquiryTimeoutInMillis;
    private final UUID lockUUID;

    private Lock lock;

    /**
     * Detailed constructor with default acquire timeout 10000 msecs and lock expiration of 60000 msecs.
     *
     * @param lockKey lock key (ex. account:1, ...)
     */
    public JedisLock(String lockKey) {
        this(lockKey, DEFAULT_ACQUIRE_TIMEOUT_MILLIS, DEFAULT_EXPIRY_TIME_MILLIS);
    }


    /**
     * Detailed constructor with default lock expiration of 60000 msecs.
     *
     * @param lockKey              lock key (ex. account:1, ...)
     * @param acquireTimeoutMillis acquire timeout in miliseconds (default: 10000 msecs)
     */
    public JedisLock(String lockKey, int acquireTimeoutMillis) {
        this(lockKey, acquireTimeoutMillis, DEFAULT_EXPIRY_TIME_MILLIS);
    }

    /**
     * Detailed constructor.
     *
     * @param lockKey              lock key (ex. account:1, ...)
     * @param acquireTimeoutMillis acquire timeout in miliseconds (default: 10000 msecs)
     * @param expiryTimeMillis     lock expiration in miliseconds (default: 60000 msecs)
     */
    public JedisLock(String lockKey, int acquireTimeoutMillis, int expiryTimeMillis) {
        this(lockKey, acquireTimeoutMillis, expiryTimeMillis, UUID.randomUUID());
    }

    /**
     * Detailed constructor.
     *
     * @param lockKey              lock key (ex. account:1, ...)
     * @param acquireTimeoutMillis acquire timeout in miliseconds (default: 10000 msecs)
     * @param expiryTimeMillis     lock expiration in miliseconds (default: 60000 msecs)
     * @param uuid                 unique identification of this lock
     */
    public JedisLock(String lockKey, int acquireTimeoutMillis, int expiryTimeMillis, UUID uuid) {
        this.lockKeyPath = lockKey;
        this.acquiryTimeoutInMillis = acquireTimeoutMillis;
        this.lockExpiryInMillis = expiryTimeMillis + 1;
        this.lockUUID = uuid;
    }

    /**
     * @return lock uuid
     */
    public UUID getLockUUID() {
        return lockUUID;
    }


    public Lock getLock() {
        return this.lock;
    }


    public void setLock(Lock lock) {
        this.lock = lock;
    }

    /**
     * @return lock key path
     */
    public String getLockKeyPath() {
        return lockKeyPath;
    }


    public long getLockExpiryInMillis() {
        return this.lockExpiryInMillis;
    }

    /**
     * Acquire lock.
     *
     * @return true if lock is acquired, false acquire timeouted
     * @throws InterruptedException in case of thread interruption
     */
    public synchronized boolean acquire(Jedis jedis) throws InterruptedException {
        try {
            int timeout = acquiryTimeoutInMillis;
            while (timeout >= 0) {
                final Lock newLock = asLock(System.currentTimeMillis() + lockExpiryInMillis);
                if (jedis.setnx(lockKeyPath, newLock.toString()) == 1) {
                    this.lock = newLock;
                    return true;
                }
                final String currentValueStr = jedis.get(lockKeyPath);
                if(StringUtils.isNotBlank(currentValueStr)){
                    final Lock currentLock = Lock.fromString(currentValueStr);
                    if (currentLock.isExpiredOrMine(lockUUID)) {
                        String oldValueStr = jedis.getSet(lockKeyPath, newLock.toString());
                        if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                            this.lock = newLock;
                            return true;
                        }
                    }
                    timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLIS;
                    if (timeout > 0) {
                        this.wait(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
                    }
                }else {
                    LOGGER.warn("redis get key:{} is null",lockKeyPath);
                }
            }
        }catch (Exception e){
            LOGGER.error("acquire error",e);
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * Renew lock.
     *
     * @return true if lock is acquired, false otherwise
     * @throws InterruptedException in case of thread interruption
     */
    public boolean renew(Jedis jedis) throws InterruptedException {
        final Lock currentLock = Lock.fromString(jedis.get(lockKeyPath));
        if (!currentLock.isExpiredOrMine(lockUUID)) {
            return false;
        }

        return acquire(jedis);
    }

    /**
     * Acquired lock release.
     *
     * @param jedis
     */
    public synchronized void release(Jedis jedis) {
        try {
            if (isLocked() && lock.isMine(lockUUID)) {
                jedis.del(lockKeyPath);
                this.lock = null;
            }
        }catch (Exception e){
            LOGGER.error("release error",e);
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Check if owns the lock
     *
     * @return true if lock owned
     */
    public synchronized boolean isLocked() {
        return this.lock != null;
    }

    /**
     * Returns the expiry time of this lock
     *
     * @return the expiry time in millis (or null if not locked)
     */
    public synchronized long getLockExpiryTimeInMillis() {
        return this.lock.getExpiryTime();
    }

    public Lock asLock(long expires) {
        return new Lock(lockUUID, expires);
    }

    public static class Lock {
        private UUID uuid;
        private long expiryTime;

        protected Lock(UUID uuid, long expiryTimeInMillis) {
            this.uuid = uuid;
            this.expiryTime = expiryTimeInMillis;
        }

        protected static Lock fromString(String text) {
            try {
                String[] parts = text.split(":");
                UUID theUUID = UUID.fromString(parts[0]);
                long theTime = Long.parseLong(parts[1]);
                return new Lock(theUUID, theTime);
            } catch (Exception e) {
                LOGGER.error("parse from string:{} error", text,e);
                return NO_LOCK;
            }
        }

        public UUID getUUID() {
            return uuid;
        }

        public long getExpiryTime() {
            return expiryTime;
        }

        @Override
        public String toString() {
            return uuid.toString() + ":" + expiryTime;
        }

        boolean isExpired() {
            return getExpiryTime() < System.currentTimeMillis();
        }

        boolean isExpiredOrMine(UUID otherUUID) {
            return this.isExpired() || this.getUUID().equals(otherUUID);
        }

        boolean isMine(UUID otherUUID) {
            return this.getUUID().equals(otherUUID);
        }
    }

}
