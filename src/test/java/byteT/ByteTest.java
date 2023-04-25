package byteT;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import redis.clients.util.SafeEncoder;
import utill.ObjectAndByteUtils;

import java.util.Arrays;

/**
 * @author lixiliang
 * @describe
 * @date 2021/9/1
 */
@Slf4j
public class ByteTest {
    @Test
    public void b1(){
        Object[] args = new Object[2];
        byte[][] data = {{108, 120, 108, 45, 48, 50},{118, 97, 108, 50}};
        args[0] = "SET";
        args[1] = data;
        Arrays.stream(args).forEach(i-> log.info("arg -> {}",i));
        String[] argsStr = new String[args.length];
        byte[][] s = (byte[][])args[1];
        Arrays.stream(s).forEach(d->{
            log.info("data -> {}",SafeEncoder.encode(d));
        });
//        argsStr[0] = SafeEncoder.encode(data);

        if(args.length > 1){
            //transfer 1..n  args to String[]
            for (int i = 1; i < args.length; i++) {
                argsStr[i-1] = SafeEncoder.encode(ObjectAndByteUtils.toByteArray(args[i]));
//                argsStr[i-1] = SafeEncoder.encode(ObjectAndByteUtils.toByteArray(args[i]));
            }
        }
        log.info("args:{}",  argsStr);
    }
}
