/*
package id;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import yunnex.payment.common.constant.SystemConstants;
import yunnex.payment.common.log.PLogger;
import yunnex.payment.common.log.PaymentLoggerFactory;

*/
/**
 * 64位ID (42(毫秒)+5(机器ID)+5(业务编码)+12(重复累加))
 * 
 * @author tajo
 *//*

public class IdWorker {

    private static final PLogger LOGGER = PaymentLoggerFactory.getLogger(IdWorker.class);

    private final static long twepoch = 1288834974657L;

    // 机器标识位数
    private final static long workerIdBits = 5L;

    // 数据中心标识位数
    private final static long datacenterIdBits = 5L;

    // 机器ID最大值
    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);

    // 数据中心ID最大值
    private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    // 毫秒内自增位
    private final static long sequenceBits = 12L;

    // 机器ID偏左移12位
    private final static long workerIdShift = sequenceBits;

    // 数据中心ID左移17位
    private final static long datacenterIdShift = sequenceBits + workerIdBits;

    // 时间毫秒左移22位
    private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

    private static long lastTimestamp = -1L;

    private long sequence = 0L;

    private final long workerId;

    private final long datacenterId;

    public IdWorker() {
        int workerId = -1;
        Random random = new Random();
        try {
            String localHost = null;



            String osName = System.getProperty("os.name");
            if (StringUtils.isNotBlank(osName) && osName.startsWith("Windows")) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    localHost = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    LOGGER.error("获取本地IP异常：", e);
                    System.exit(-1);
                }
            } else {
                try {
                    Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
                    while (enumeration.hasMoreElements()) {
                        NetworkInterface networkInterface = enumeration.nextElement();
                        if (!"eth0".equalsIgnoreCase(networkInterface.getName())) {
                            continue;
                        }

                        Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses();
                        while (addressEnumeration.hasMoreElements()) {
                            InetAddress inetAddress = addressEnumeration.nextElement();
                            if (inetAddress instanceof Inet6Address)
                                continue;
                            localHost = inetAddress.getHostAddress();
                        }
                    }
                } catch (SocketException e) {
                    LOGGER.error("获取本地IP异常：", e);
                }
            }
            LOGGER.info("localHost=" + localHost);

            String hostAndWorkerIdConfig = SystemConstants.PAYMENT_HOST_AND_WORKERID_PAIR;
            LOGGER.info("hostAndWorkerIdConfig=" + hostAndWorkerIdConfig);
            if (StringUtils.isBlank(hostAndWorkerIdConfig)) {
                LOGGER.error("IdWorker配置为空");
                System.exit(-1);
            }
            String[] hostAndWorkerIdPairArray = hostAndWorkerIdConfig.split(";");
            for (String hostAndWorkerIdPair : hostAndWorkerIdPairArray) {
                if (StringUtils.isNotBlank(localHost) && hostAndWorkerIdPair.startsWith(localHost)) {
                    String[] hostAndWorkerIdSplits = hostAndWorkerIdPair.split(":");
                    if (hostAndWorkerIdSplits.length != 2 || StringUtils.isBlank(hostAndWorkerIdSplits[1]) || !NumberUtils
                                    .isDigits(hostAndWorkerIdSplits[1])) {
                        LOGGER.error("IdWorker配置错误");
                        System.exit(-1);
                    }
                    workerId = Integer.valueOf(hostAndWorkerIdSplits[1]);
                }
            }

            if (workerId > maxWorkerId || workerId < 0) {
                throw new IllegalArgumentException("workerId can't be greater than " + maxWorkerId + " or less than 0");
            }
        } catch (Exception e) {
            LOGGER.error("获取workerId异常", e);
            workerId = random.nextInt((int) maxWorkerId);
        }
        this.workerId = workerId;
        this.datacenterId = random.nextInt((int) maxDatacenterId);
        LOGGER.info("workerId={}, datacenterId={}", this.workerId, this.datacenterId);
    }


    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            LOGGER.warn("Clock moved backwards.  Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }

        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则+1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        // ID偏移组合生成最终的ID，并返回ID
        long nextId = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;

        return nextId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

}
*/
