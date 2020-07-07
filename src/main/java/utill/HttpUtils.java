package utill;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.*;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lixiliang
 */
public class HttpUtils {

    private HttpUtils() {
    }

    private static final String GROUP_ID = "group_http_util";

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static final String DEFAULT_ENCODING = "UTF-8"; // 默认编码

    private static final String TIMEOUT_KEY = "timeout";

    private static final String RETRY_KEY = "retries";

    /**
     * 默认传输超时20s
     */
    public static final int DEFAULT_TIMEOUT = 20 * 1000;

    /**
     * 默认传输超时20s
     */
    public static final int DEFAULT_SOCKET_TIMEOUT = 20 * 1000;

    private static Config config = new Config();

    private static CloseableHttpClient HTTP_CLIENT = null;

    private static RequestConfig DEFAULT_REQUEST_CONFIG = null;

    static {
        DEFAULT_REQUEST_CONFIG = RequestConfig.custom().setConnectionRequestTimeout(DEFAULT_SOCKET_TIMEOUT).setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setConnectTimeout(DEFAULT_SOCKET_TIMEOUT).setCookieSpec("easy").setRedirectsEnabled(false).setStaleConnectionCheckEnabled(true).build();

        config.maxConnections = 1000;
        config.maxConnectionsPerRoute = 500;
        config.retryCount = 0;
        config.timeout = DEFAULT_TIMEOUT;

        HTTP_CLIENT = getHttpClient();
    }

    public static void rebuild(Config conf) {
        config = conf;
        if (HTTP_CLIENT != null) {
            try {
                HTTP_CLIENT.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }

            HTTP_CLIENT = null;
        }

        HTTP_CLIENT = getHttpClient();
    }

    /**
     * 获取 http client
     *
     * @return
     */
    public static CloseableHttpClient getHttpClient() {
        return getHttpClient(config);
    }

    /**
     * 获取 http client
     *
     * @return
     */
    public static CloseableHttpClient getHttpClient(Config conf) {
        CloseableHttpClient client = null;
        if (conf == null) {
            conf = config;
        }

        try {
            SSLConnectionSocketFactory sslsf = buildSSLConnectionSocketFactory();
            Registry<CookieSpecProvider> registry = buildRegistry();

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
            // 设置握手连接超时
            SocketConfig sc = SocketConfig.custom().setSoTimeout(conf.timeout).build();

            // 连接池配置
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            cm.setMaxTotal(conf.maxConnections);// 连接池最大并发连接数
            cm.setDefaultMaxPerRoute(conf.maxConnectionsPerRoute);// 单路由最大并发数
            cm.setDefaultSocketConfig(sc);

            // 长连接保持时长，默认10秒，如果服务端有指定，以服务端为准
            ConnectionKeepAliveStrategy keepAliveStrategy = buildConnectionKeepAliveStrategy();
            DefaultHttpRequestRetryHandler retryHander = buildRetryHandler(conf.retryCount);
            HttpClientBuilder clientBuilder = HttpClients.custom().setConnectionManager(cm).setDefaultCookieSpecRegistry(registry).setDefaultRequestConfig(DEFAULT_REQUEST_CONFIG).setSSLSocketFactory(sslsf).setRetryHandler(retryHander).setDefaultSocketConfig(sc).setKeepAliveStrategy(keepAliveStrategy);

            client = clientBuilder.build();
        } catch (Exception e) {
            logger.error("初始化 httpclient 失败！", e);
        }
        return client;
    }

    private static ConnectionKeepAliveStrategy buildConnectionKeepAliveStrategy() {
        return new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(org.apache.http.HttpResponse response, HttpContext context) {
                if (response != null) {
                    HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                    while (it.hasNext()) {
                        HeaderElement he = it.nextElement();
                        String param = he.getName();
                        String value = he.getValue();
                        if (value != null && param.equalsIgnoreCase("timeout")) {
                            return Long.parseLong(value) * 1000;
                        }
                    }
                }

                return 10000;
            }
        };
    }

    private static SSLConnectionSocketFactory buildSSLConnectionSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        SSLContextBuilder builder = new SSLContextBuilder();
        try {
            builder.loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有证书
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {// 信任所有
                    return true;
                }
            });

            sslsf = new SSLConnectionSocketFactory(builder.build());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return sslsf;
    }

    private static Registry<CookieSpecProvider> buildRegistry() {
        CookieSpecProvider easySpecProvider = new CookieSpecProvider() {

            public CookieSpec create(HttpContext context) {
                return new BrowserCompatSpec() {
                    @Override
                    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
                        // Oh, I am easy
                    }
                };
            }

        };

        return RegistryBuilder.<CookieSpecProvider>create().register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory()).register(CookieSpecs.BROWSER_COMPATIBILITY, new BrowserCompatSpecFactory()).register("easy", easySpecProvider).build();
    }


    /**
     * json post
     *
     * @param url
     * @param params 支持对象或字符串
     * @return
     */
    public static HttpResponse jsonPost(String url, Object params) {
        return post(url, params, ContentType.APPLICATION_JSON);
    }

    /**
     * json post
     *
     * @param url
     * @param params
     * @param retry
     * @param timeout 支持对象或字符串
     * @return
     */
    public static HttpResponse jsonPost(String url, Object params, int retry, int timeout) {
        return post(url, params, ContentType.APPLICATION_JSON, retry, timeout);
    }


    /**
     * 支持不同类型的contentType请求，支持http和https
     *
     * @param url
     * @param params      与contentType对应格式的参数,比如contentType=ContentType.APPLICATION_JSON
     *                    params={\"mobile\":\"18520866952\",\"message\":\"测试\",\"from\":\"智慧餐厅\"}
     * @param contentType
     * @return
     */
    public static HttpResponse post(String url, Object params, final ContentType contentType) {
        HttpPost post = buildHttpPostFromJson(url, params, contentType);
        return execute(post);
    }

    /**
     * 支持不同类型的contentType请求，支持http和https
     *
     * @param url
     * @param params      与contentType对应格式的参数,比如contentType=ContentType.APPLICATION_JSON
     *                    params={\"mobile\":\"18520866952\",\"message\":\"测试\",\"from\":\"智慧餐厅\"}
     * @param contentType
     * @return
     */
    public static HttpResponse post(String url, Object params, final ContentType contentType, int retry, int timeout) {
        HttpPost post = buildHttpPostFromJson(url, params, contentType);
        return execute(post, retry, timeout);
    }


    /**
     * 构建httpost
     *
     * @param url
     * @param params
     * @param contentType
     * @return
     */
    private static HttpPost buildHttpPostFromJson(String url, Object params, final ContentType contentType) {
        HttpPost httpPost = new HttpPost(url);
        EntityBuilder entityBuilder = EntityBuilder.create();
        entityBuilder.setContentType(contentType);
        if (params instanceof String) {
            entityBuilder.setText((String) params);
        } else {
            entityBuilder.setText(JSON.toJSONString(params));
        }
        httpPost.setEntity(entityBuilder.build());

        return httpPost;

    }

    /**
     * get 请求(支持http,https)
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static HttpResponse get(String url) {
        HttpGet httpGet = new HttpGet(url);
        return execute(httpGet);
    }


    /**
     * get 请求(支持http,https)
     *
     * @param url
     * @param retry
     * @param timeout 毫秒
     * @return
     */
    public static HttpResponse get(String url, int retry, int timeout) {
        HttpGet httpGet = new HttpGet(url);
        return execute(httpGet, retry, timeout);
    }

    /**
     * 构建httpput对象
     *
     * @param url
     * @param param
     * @return
     */
    private static HttpPut buildHttpPut(String url, Map<String, String> param) {

        List<NameValuePair> formparams = new ArrayList<>();

        for (Map.Entry<String, String> item : param.entrySet()) {
            String val = item.getValue();
            if (StringUtils.isEmpty(val) && !"null".equals(val) && !"NULL".equals(val)) {
                formparams.add(new BasicNameValuePair(item.getKey(), val));
            }
        }

        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(formparams, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(entity);

        return httpPut;
    }

    /**
     * put 请求
     *
     * @param url
     * @return
     */
    public static HttpResponse mapPut(String url, Map<String, String> params) {
        HttpPut httpPut = buildHttpPut(url, params);
        return execute(httpPut);
    }
    private static HttpResponse execute(final HttpUriRequest request) {
        return execute(request, config.retryCount, config.timeout);
    }
    private static HttpResponse execute(final HttpUriRequest request, int retry, int timeout) {
        HttpResponse response = null;
        CloseableHttpResponse res = null;
        try {
            if (request instanceof HttpRequestBase) {
                RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).setConnectTimeout(timeout).setCookieSpec("easy").setRedirectsEnabled(false).setStaleConnectionCheckEnabled(true).build();

                ((HttpRequestBase) request).setConfig(requestConfig);
            }

            HttpContext httpContext = HttpClientContext.create();
            httpContext.setAttribute(TIMEOUT_KEY, timeout);
            httpContext.setAttribute(RETRY_KEY, retry);
            res = HTTP_CLIENT.execute(request, httpContext);
            StatusLine statusLine = res.getStatusLine();
            HttpEntity httpEntity = res.getEntity();
            response = new HttpResponse(statusLine.getStatusCode(), EntityUtils.toString(httpEntity, DEFAULT_ENCODING));
            res.close();
            res = null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            if (res != null) {
                try {
                    res.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }

        return response;

    }
    /**
     * put 请求
     *
     * @param url
     * @return
     */
    public static HttpResponse mapPut(String url, Map<String, String> params, int retry, int timeout) {
        HttpPut httpPut = buildHttpPut(url, params);
        return execute(httpPut, retry, timeout);
    }


    /**
     * 根据map 创建post 对象
     *
     * @param url
     * @param param
     * @return
     */
    private static HttpPost buildHttpPostFromMap(String url, Map<String, String> param) {
        List<NameValuePair> formparams = new ArrayList<>();

        for (Map.Entry<String, String> item : param.entrySet()) {
            String val = String.valueOf(item.getValue());
            if (StringUtils.isEmpty(val) && !"null".equals(val) && !"NULL".equals(val)) {
                formparams.add(new BasicNameValuePair(item.getKey(), val));
            }
        }
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(formparams, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);

        return httpPost;
    }

    /**
     * post 请求
     *
     * @param url
     * @return
     */
    public static HttpResponse mapPost(String url, Map<String, String> params) {
        HttpPost post = buildHttpPostFromMap(url, params);
        return execute(post);
    }

    /**
     * post 请求
     *
     * @param url
     * @return
     */
    public static HttpResponse mapPost(String url, Map<String, String> params, int retry, int timeout) {
        HttpPost post = buildHttpPostFromMap(url, params);
        return execute(post, retry, timeout);
    }

    /**
     * 构建httpDelete
     *
     * @return
     */
    private static MyHttpDelete buildHttpDeleteFromMap(String url, Map<String, String> param) {
        List<NameValuePair> formparams = new ArrayList<>();

        for (Map.Entry<String, String> item : param.entrySet()) {
            String val = String.valueOf(item.getValue());
            if (val != null && !"".equals(val) && !"null".equals(val) && !"NULL".equals(val)) {
                formparams.add(new BasicNameValuePair(item.getKey(), val));
            }
        }
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(formparams, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        MyHttpDelete httpDelete = new MyHttpDelete(url);
        httpDelete.setEntity(entity);

        return httpDelete;
    }

    /**
     * delete 请求
     *
     * @param url
     * @return
     */
    public static HttpResponse mapDelete(String url, Map<String, String> params) {
        MyHttpDelete httpDel = buildHttpDeleteFromMap(url, params);
        return execute(httpDel);
    }

    /**
     * delete 请求
     *
     * @param url
     * @return
     */
    public static HttpResponse mapDelete(String url, Map<String, String> params, int retry, int timeout) {
        MyHttpDelete httpDel = buildHttpDeleteFromMap(url, params);
        return execute(httpDel, retry, timeout);
    }

    static class MyHttpDelete extends HttpEntityEnclosingRequestBase {
        public static final String METHOD_NAME = "DELETE";

        public String getMethod() {
            return METHOD_NAME;
        }

        public MyHttpDelete(final String uri) {
            super();
            setURI(URI.create(uri));
        }

        public MyHttpDelete(final URI uri) {
            super();
            setURI(uri);
        }

        public MyHttpDelete() {
            super();
        }

    }

    /**
     * post 请求（支持http,https）
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static HttpResponse post(String url, String params) {
        return post(url, params, ContentType.create("application/x-www-form-urlencoded", DEFAULT_ENCODING));
    }

    /**
     * post 请求（支持http,https）
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static HttpResponse post(String url, String params, int retry, int timeout) {
        return post(url, params, ContentType.create("application/x-www-form-urlencoded", DEFAULT_ENCODING), retry, timeout);
    }




    /**
     * 构建带header 信息的httppost
     *
     * @param url
     * @param params
     * @param headerParams
     * @return
     */
    private static HttpPost buildHttpPostFromJsonWithHeader(String url, Object params, final Map<String, Object> headerParams) {
        HttpPost httpPost = new HttpPost(url);
        if (headerParams != null && headerParams.size() > 0) {
            for (Map.Entry<String, Object> entry : headerParams.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue().toString());
            }
        }

        EntityBuilder entityBuilder = EntityBuilder.create();
        if (params instanceof String) {
            entityBuilder.setBinary(params.toString().getBytes());
        } else {
            entityBuilder.setBinary(JSON.toJSONString(params).getBytes());
        }
        httpPost.setEntity(entityBuilder.build());

        return httpPost;

    }

    /**
     * 带请求头的post请求（支持http和https）
     *
     * @param url
     * @param params
     * @param headerParams
     * @return
     * @throws Exception
     */
    public static HttpResponse post(String url, Object params, final Map<String, Object> headerParams) {
        HttpPost post = buildHttpPostFromJsonWithHeader(url, params, headerParams);
        return execute(post);
    }

    /**
     * 带请求头的post请求（支持http和https）
     *
     * @param url
     * @param params
     * @param headerParams
     * @return
     * @throws Exception
     */
    public static HttpResponse post(String url, Object params, final Map<String, Object> headerParams, int retry, int timeout) {
        HttpPost post = buildHttpPostFromJsonWithHeader(url, params, headerParams);
        return execute(post, retry, timeout);
    }

    /**
     * 构建重试处理器
     *
     * @param retryCount
     */
    private static DefaultHttpRequestRetryHandler buildRetryHandler(final int retryCount) {
        return new DefaultHttpRequestRetryHandler() {

            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                int activeRetryCount = (int) (context.getAttribute(RETRY_KEY) == null ? retryCount : context.getAttribute(RETRY_KEY));

                if (executionCount > activeRetryCount) {

                    logger.warn("Maximum tries reached, exception would be thrown to outer block");
                    return false;
                }

                if (exception instanceof SocketException) {
                    logger.warn("SocketException occured on {} call:{}", executionCount, exception.getMessage());
                    return true;
                }

                // 连接超时重试
                if (exception instanceof ConnectTimeoutException) {
                    logger.warn("Connection timeout from server on {} call", executionCount);
                    return true;
                }

                // 读取响应重试
                if (exception instanceof SocketTimeoutException) {
                    logger.warn("socket timeout from server on {} call", executionCount);
                    return true;
                }

                // NoHttpResponseException
                if (exception instanceof NoHttpResponseException) {
                    logger.warn("No response from server on {} call", executionCount);
                    return true;
                }

                /**
                 * 未处理异常：1、 java.net.SocketException: Software caused connection abort: recv
                 * failed 2、java.net.SocketException: Connection reset
                 */

                return super.retryRequest(exception, executionCount, context);
            }
        };
    }

    /**
     * 将url去除queryString后作为taskId
     *
     * @param url
     * @return
     */
    private static String genTaskId(String url) {
        if (StringUtils.isEmpty(url)) {
            return StringUtils.EMPTY;
        }
        return url.split("\\?")[0];
    }

    public static class Config {

        private int retryCount = 0;

        private int timeout = 0;

        private int maxConnections = 0;

        private int maxConnectionsPerRoute = 0;

    }
    
}

/**
 * Http 响应类
 *
 */


