package socket;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by admin on 2017/11/14.
 */
public class OkHttpClientSample {
    private final static Logger logger = LoggerFactory.getLogger(OkHttpClientSample.class);
    private final AtomicLong sequence = new AtomicLong();

    private OkHttpClient client = new OkHttpClient.Builder()
            //连接超时时间
            .connectTimeout(3, TimeUnit.SECONDS)
            //读取响应的超时时间
            .readTimeout(10, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(1,2,TimeUnit.MINUTES))
            .build();
    private String apiUrl = "http://192.168.10.236:8080/test";

    private Request request = new Request.Builder()
            .url(apiUrl).get()
            .build();

    /**
     * 每次请求结束后，重新发起请求
     *
     */
    private void longPolling(){
        //循环执行，保证每次request结束，再次发起request
        while (!Thread.interrupted()) {
            sendRequest();
        }
    }

    private void sendRequest(){
        logger.info("第" + (sequence.incrementAndGet()) + "次 request begin");
        try {
            Response response = client.newCall(request).execute();
            response.body().string();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }finally {
           logger.info("end request");
        }
    }

    public static void main(String[] args) throws IOException {
        OkHttpClientSample sample = new OkHttpClientSample();
        sample.longPolling();
    }
}
