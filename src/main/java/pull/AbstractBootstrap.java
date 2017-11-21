package pull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by andy on 17/7/8.
 */
public class AbstractBootstrap {

    //同步URL
    protected static final String URL = "http://192.168.10.236:8081/deferred";
    //异步URL
    protected static final String ASYNC_URL = "http://localhost:8080/long-polling-async";

    private final AtomicLong sequence = new AtomicLong();

    protected void poll() {
        //循环执行，保证每次longpolling结束，再次发起longpolling
        while (!Thread.interrupted()) {
            doPoll();
        }
    }

    protected void doPoll() {
        System.out.println("第" + (sequence.incrementAndGet()) + "次 longpolling");

        long startMillis = System.currentTimeMillis();

        HttpURLConnection connection = null;
        try {
            URL getUrl = new URL(URL);
            connection = (HttpURLConnection) getUrl.openConnection();

            //50s作为长轮询超时时间
            connection.setReadTimeout(1000*60*2);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept-Charset", "application/json;charset=UTF-8");
            connection.connect();

            if (200 == connection.getResponseCode()) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    StringBuilder result = new StringBuilder(256);
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    System.out.println("结果 " + result);

                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("request failed, "+e);
        } finally {
            long elapsed = (System.currentTimeMillis() - startMillis) / 1000;
            System.out.println("connection close" + "     " + "elapse " + elapsed + "s");
            if (connection != null) {
                connection.disconnect();
            }
            System.out.println("=========");
        }
    }

}
