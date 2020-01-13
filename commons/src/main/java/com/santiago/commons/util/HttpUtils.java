package com.santiago.commons.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
public class HttpUtils {
    public HttpUtils() {
    }

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private static CloseableHttpClient client;
    private static RequestConfig defaultRequestConfig = RequestConfig.custom()
            .setExpectContinueEnabled(false)
            // 禁用重定向
            .setRedirectsEnabled(false)
            .setRelativeRedirectsAllowed(false)
            .build();

    /**
     * 发送简单的Post请求
     *
     * @param url    接口地址
     * @param params 接口参数
     * @return
     */
    public static String sendSimplePost(String url, Map<String, String> params) {
        return sendPost(url, params, null, null, 20000, 20000, "UTF-8");
    }

    /**
     * 发送简单的Json格式的post请求
     *
     * @param url    接口地址
     * @param params 接口参数
     * @return
     */
    public static String sendSimpleJsonPost(String url, Object params) {
        return sendJsonPost(url, params, null, null, 20000, 20000, "UTF-8");
    }

    /**
     * 发送post请求，较为全面
     *
     * @param url            接口地址
     * @param params         接口参数
     * @param headers        请求头信息
     * @param cookies        请求cookies信息
     * @param connectTimeout 连接超时时间
     * @param socketTimeout  会话超时时间
     * @param charset        接口编码默认UTF-8
     * @return
     */
    public static String sendPost(String url, Map<String, String> params, Map<String, String> headers, List<String> cookies,
                                  int connectTimeout, int socketTimeout, String charset) {
        if (StringUtils.isEmpty(url)) {
            return StringUtils.EMPTY;
        }
        HttpPost post = getHttpPost(url, params, headers, cookies, connectTimeout, socketTimeout);
        return send(url, params, connectTimeout, socketTimeout, charset, post);
    }

    public static String sendJsonPost(String url, Object params, Map<String, String> headers, List<String> cookies,
                                      int connectTimeout, int socketTimeout, String charset) {
        if (StringUtils.isEmpty(url)) {
            return StringUtils.EMPTY;
        }
        HttpPost post = getJsonHttpPost(url, params, headers, cookies, connectTimeout, socketTimeout);
        return send(url, params, connectTimeout, socketTimeout, charset, post);
    }

    /**
     * HttpPost对象生成器
     *
     * @param url            接口地址
     * @param params         参数
     * @param headers        请求报头
     * @param cookies        请求Cookie
     * @param connectTimeout 请求超时时间
     * @param socketTimeout  等待数据超时时间
     * @return HttpPost
     */
    private static HttpPost getHttpPost(String url, Map<String, String> params, Map<String, String> headers, List<String> cookies,
                                        int connectTimeout, int socketTimeout) {
        try {
            HttpPost post = getDefHttpPost(url, cookies, connectTimeout, socketTimeout);
            addHeaders(post, headers, cookies, "");
            if (MapUtils.isNotEmpty(params)) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramsToNameValuePairs(params));
                post.setEntity(entity);
            }
            return post;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json格式post生成器
     *
     * @param url            接口地址
     * @param params         接口参数
     * @param headers        请求头信息
     * @param cookies        请求cookies信息
     * @param connectTimeout 连接超时时间
     * @param socketTimeout  会话超时时间
     * @return
     */
    private static HttpPost getJsonHttpPost(String url, Object params, Map<String, String> headers, List<String> cookies,
                                            int connectTimeout, int socketTimeout) {
        try {
            HttpPost post = getDefHttpPost(url, cookies, connectTimeout, socketTimeout);
            addHeaders(post, headers, cookies, ContentType.APPLICATION_JSON.getMimeType());
            if (params != null) {
                StringEntity entity = new StringEntity(JsonUtil.obj2JsonStrExcludeNull(params));
                post.setEntity(entity);
            }
            return post;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成默认的Post信息
     *
     * @param url            接口地址
     * @param cookies        请求cookies信息
     * @param connectTimeout 连接超时时间
     * @param socketTimeout  会话超时时间
     * @return
     */
    private static HttpPost getDefHttpPost(String url, List<String> cookies,
                                           int connectTimeout, int socketTimeout) {
        HttpPost post = new HttpPost(url);
        post.setProtocolVersion(HttpVersion.HTTP_1_1);
        RequestConfig requestConfig = generateRequestConfig(connectTimeout, socketTimeout);
        post.setConfig(requestConfig);
        return post;
    }

    /**
     * 发送简单的GET请求
     *
     * @param url    接口地址
     * @param params 请求参数
     * @return
     */
    public static String sendSimpleGet(String url, Map<String, String> params) {
        return sendGet(url, params, null, null, 20000, 10000, "UTF-8");

    }

    /**
     * 发送详细的GET请求
     *
     * @param url            接口地址
     * @param params         接口参数
     * @param headers        请求头信息
     * @param cookies        请求cookies信息
     * @param connectTimeout 连接超时时间
     * @param socketTimeout  会话超时时间
     * @param charset        接口编码默认UTF-8
     * @return
     */
    public static String sendGet(String url, Map<String, String> params, Map<String, String> headers, List<String> cookies,
                                 int connectTimeout, int socketTimeout, String charset) {
        if (StringUtils.isEmpty(url)) {
            return StringUtils.EMPTY;
        }

        HttpGet httpGet = getHttpGet(url, params, headers, cookies, connectTimeout, socketTimeout);
        if (httpGet == null) {
            return StringUtils.EMPTY;
        }
        return send(url, params, connectTimeout, socketTimeout, charset, httpGet);
    }

    /**
     * 用于将数据进行发送并将响应解析为字符串
     *
     * @param url            接口地址
     * @param params         接口参数
     * @param connectTimeout 连接超时时间
     * @param socketTimeout  会话超时时间
     * @param charset        接口编码默认UTF-8
     * @return
     */
    private static String send(String url, Object params, int connectTimeout, int socketTimeout, String charset, HttpRequestBase requestBase) {
        HttpResponse response = null;
        try {
            response = getClient().execute(requestBase);
            if (response == null) {
                logger.error("HttpClientUtil.sendHttpGetRequest error >>>>>> response is null, url:{}, params:{}, connectTimeout:{}, socketTimeout:{}",
                        url, params, connectTimeout, socketTimeout);
                return StringUtils.EMPTY;
            }
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                logger.error("HttpClientUtil.sendHttpGetRequest error >>>>>> method failed:{}, url:{}, params:{}, connectTimeout:{}, socketTimeout:{}",
                        response.getStatusLine(), url, params, connectTimeout, socketTimeout);
                /* 当状态码不是200时，默认返回空字符串 */
                return StringUtils.EMPTY;
            }
            if (StringUtils.isEmpty(charset)) {
                charset = "UTF-8";
            }
            return EntityUtils.toString(response.getEntity(), charset);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    // 会自动释放连接
                    EntityUtils.consume(response.getEntity());
                    requestBase.releaseConnection();
                }
            } catch (IOException e) {
                logger.error("HttpClientUtil.sendHttpPostRequest error, stream connection close failure, url:{}, params:{}, connectTimeout:{}, socketTimeout:{}",
                        url, params, connectTimeout, socketTimeout, e);
            } catch (Exception e) {
                logger.error("HttpClientUtil.sendHttpPostRequest error, url:{}, params:{}, connectTimeout:{}, socketTimeout:{}",
                        url, params, connectTimeout, socketTimeout, e);
            }
        }
        return StringUtils.EMPTY;
    }


    /**
     * HttpGet对象生成器
     *
     * @param url            接口地址
     * @param params         参数
     * @param headers        请求报头
     * @param cookies        请求Cookie
     * @param connectTimeout 请求超时时间
     * @param socketTimeout  等待数据超时时间
     * @return HttpGet
     */
    private static HttpGet getHttpGet(String url, Map<String, String> params, Map<String, String> headers, List<String> cookies,
                                      int connectTimeout, int socketTimeout) {
        try {
            //生成URLBuilder用于构建url以及参数请求连接
            URIBuilder builder = new URIBuilder(url);
            if (MapUtils.isNotEmpty(params)) {
                builder.addParameters(paramsToNameValuePairs(params));
            }
            HttpGet httpGet = new HttpGet(builder.build());
            //设置HTTP协议版本
            httpGet.setProtocolVersion(HttpVersion.HTTP_1_1);
            //添加消息头
            addHeaders(httpGet, headers, cookies, "");
            //添加请求设施参数
            httpGet.setConfig(generateRequestConfig(connectTimeout, socketTimeout));
            return httpGet;
        } catch (URISyntaxException e) {
            logger.error("HttpClientUtil.generateHttpGet error, url:{}, params:{}, connectTimeout:{}, socketTimeout:{}",
                    url, params, connectTimeout, socketTimeout, e);
        }
        return null;
    }

    /**
     * 将Map参数解析为list参数
     *
     * @param params 参数
     * @return
     */
    private static List paramsToNameValuePairs(Map<String, String> params) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (MapUtils.isNotEmpty(params)) {
            for (String key : params.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        return nameValuePairs;
    }

    /**
     * 单例方式获取Client实例
     *
     * @return
     */
    private static HttpClient getClient() {
        if (client == null) {
            synchronized (HttpUtils.class) {
                if (client == null) {
                    client = makeClient();
                }
            }
        }
        return client;
    }

    /**
     * http header
     *
     * @param httpRequest
     * @param headers     请求报头
     * @param cookies     通过httpRequest.addHeader方法设置Cookie
     * @param contentType 发送信息编码类型，默认application/x-www-form-urlencoded
     */
    private static void addHeaders(HttpRequestBase httpRequest, Map<String, String> headers, List<String> cookies, String contentType) {
        if (StringUtils.isEmpty(contentType)) {
            contentType = ContentType.APPLICATION_FORM_URLENCODED.getMimeType();
        }
        httpRequest.addHeader("Content-type", contentType);
        // 设置HTTP短连接, 在一次请求/响应之后, 就会关闭连接
        httpRequest.addHeader("Connection", "close");
        if (MapUtils.isNotEmpty(headers)) {
            for (String key : headers.keySet()) {
                httpRequest.addHeader(key, headers.get(key));
            }
        }
        if (CollectionUtils.isNotEmpty(cookies)) {
            for (String cookie : cookies) {
                if (StringUtils.isNotBlank(cookie)) {
                    httpRequest.addHeader("Cookie", cookie);
                }
            }
        }
    }

    /**
     * RequestConfig对象生成器
     *
     * @param connectTimeout 请求超时时间
     * @param socketTimeout  等待数据超时时间
     * @return RequestConfig
     */
    private static RequestConfig generateRequestConfig(int connectTimeout, int socketTimeout) {
        return RequestConfig.copy(defaultRequestConfig)
                // 设置从Connect Manager获取Connection的超时时间, 即连接不够用的时候的等待超时时间, 一定要设置, 而且不能太大
                .setConnectionRequestTimeout(connectTimeout / 2)
                // 设置请求超时时间
                .setConnectTimeout(connectTimeout)
                // 设置等待数据超时时间
                .setSocketTimeout(socketTimeout)
                .build();
    }

    /**
     * 生成cilent
     *
     * @return
     */
    private static CloseableHttpClient makeClient() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .setDefaultRequestConfig(getDefaultRequestConfig())
                .setConnectionManager(getConnectionManager()).build();
        return httpClient;
    }

    /**
     * 获取连接管理器
     *
     * @return
     */
    private static HttpClientConnectionManager getConnectionManager() {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        //最大连接数
        manager.setMaxTotal(10);
        //每个路由最大连接数
        manager.setDefaultMaxPerRoute(100);
        //设置到某个路由的最大连接数，会覆盖defaultMaxPerRoute
        //manager.setMaxPerRoute(new HttpRoute(new HttpHost()));
        return manager;
    }

    /**
     * 获取默认连接设置
     *
     * @return
     */
    private static RequestConfig getDefaultRequestConfig() {
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setExpectContinueEnabled(false)
                // 禁用重定向
                .setRedirectsEnabled(false)
                .setRelativeRedirectsAllowed(false)
                .build();
        return defaultRequestConfig;
    }

}