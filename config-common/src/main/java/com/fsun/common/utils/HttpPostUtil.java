/*
 * Copyright 2015-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fsun.common.utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;


public class HttpPostUtil {
	
	protected static Logger logger = LoggerFactory.getLogger(HttpPostUtil.class);
	
    Map<String, String> params;
    String url;

    public static String post(String url, Map<String, String> params) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
        String body;        
        logger.info("create httppost:" + url);
        HttpPost post = postForm(url, params);

        body = invoke(httpclient, post);

        return body;
    }

    public static String post(String url, Map<String, String> params, Map<String, String> headers) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
        String body;

        logger.info("create httppost:" + url);
        HttpPost post = postForm(url, params);

        for (String s : headers.keySet()) {
            post.setHeader(s, headers.get(s));
        }

        body = invoke(httpclient, post);

        return body;
    }

    public static String post(String url, String content) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
        String body;

        logger.info("create httppost:" + url);
        HttpPost post = postForm(url, content);

        body = invoke(httpclient, post);

        return body;
    }

    public static String post(String url, String content, Map<String, String> headers) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
        String body;

        logger.info("create httppost:" + url);
        HttpPost post = postForm(url, content);

        for (String s : headers.keySet()) {
            post.setHeader(s, headers.get(s));
        }

        body = invoke(httpclient, post);

        return body;
    }

    public static String httpsPost(String url, Map<String, String> params) {
    	//方法过时
        //PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(cm).build();
        CloseableHttpClient httpclient = sslClient();
        String body;
        logger.info("create httppost:" + url);
        HttpPost post = postForm(url, params);

        body = invoke(httpclient, post);

        return body;
    }
    
    /**
     * 在调用SSL之前需要重写验证方法，取消检测SSL
     * 创建ConnectionManager，添加Connection配置信息
     * @return HttpClient 支持https
     */
    private static CloseableHttpClient sslClient() {
        try {
            // 在调用SSL之前需要重写验证方法，取消检测SSL
            X509TrustManager trustManager = new X509TrustManager() {
                @Override public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                @Override public void checkClientTrusted(X509Certificate[] xcs, String str) {}
                @Override public void checkServerTrusted(X509Certificate[] xcs, String str) {}
            };
            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[] { trustManager }, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
            // 创建Registry
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(Boolean.TRUE).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https",socketFactory).build();
            // 创建ConnectionManager，添加Connection配置信息
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig).build();
            return closeableHttpClient;
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public HttpPostUtil(String url, Map<String, String> params) {
        this.url = url;
        this.params = params;
    }

    public static String get(String url) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
        String body = null;

        logger.info("create httppost:" + url);
        HttpGet get = new HttpGet(url);
        body = invoke(httpclient, get);

        return body;
    }


    private static String invoke(CloseableHttpClient httpclient, HttpUriRequest httpost) {

        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);

        return body;
    }

    private static String paseResponse(HttpResponse response) {
        String body = "";
        if (response == null) {
            return body;
        }
        logger.info("get response from http server..");
        HttpEntity entity = response.getEntity();

        logger.info("response status: " + response.getStatusLine());
/*        String charset = ContentType.getOrDefault(entity).getCharset().toString();
        logger.info(charset);*/

        try {
            body = EntityUtils.toString(entity);
            logger.info(body);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

    private static HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost) {
        logger.info("execute post...");
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static HttpPost postForm(String url, Map<String, String> params) {

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        logger.info("set utf-8 form entity to httppost");
        httpost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));

        return httpost;
    }

    private static HttpPost postForm(String url, String content) {
        HttpPost httpost = new HttpPost(url);

        EntityBuilder builder = EntityBuilder.create();
        builder.setContentType(ContentType.APPLICATION_JSON);
        builder.setContentEncoding(StandardCharsets.UTF_8.toString());
        builder.setText(content);

        logger.info("set utf-8 form entity to httppost");
        httpost.setEntity(builder.build());

        return httpost;
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }
    
    
    /**
     * 将结果转换成JSONObject
     * @param httpResponse
     * @return
     * @throws IOException
     */
    /*public static JSONObject getJson(HttpResponse httpResponse) throws IOException {
        HttpEntity entity = httpResponse.getEntity();
        String resp = EntityUtils.toString(entity, "UTF-8");
        EntityUtils.consume(entity);
        return JSON.parseObject(resp);
    }
*/
    
    
    

    public static void main(String[] agrs) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "jeeplus");
        params.put("password", "admin");

        String xml = HttpPostUtil.post("http://localhost:8080/HeartCare/a/login", params);
    }

    public String post() {

        //		Map<String, String> params = new HashMap<String, String>();
        //		params.put("name", "admin");
        //		params.put("password", "admin");

        String xml = HttpPostUtil.post(url, params);
        return xml;
    }
}
