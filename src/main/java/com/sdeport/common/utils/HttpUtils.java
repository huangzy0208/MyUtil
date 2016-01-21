package com.sdeport.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Http工具类
 *
 * @author zhangmeng
 * @date 2015-11-24
 */
public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * httpPost请求
     *
     * @param url          请求地址
     * @param requestParam 请求参数
     * @return
     * @throws IOException
     */
    public static String httpPost(String url, Map<String, Object> requestParam) throws IOException {
        String responseStr = "";
        // 创建HttpClient实例
        HttpClient httpclient = new DefaultHttpClient();
        // 创建Get方法实例
        HttpPost httpPost = new HttpPost(url);
        setRequestParam(httpPost, requestParam);
        HttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream inStream = entity.getContent();
            responseStr = convertStreamToString(inStream, "utf8");
            httpPost.abort();
        }
        return responseStr;
    }

    /**
     * 设置http请求参数
     *
     * @param httpRequest
     * @param requestParam
     */
    private static void setRequestParam(HttpEntityEnclosingRequestBase httpRequest, Map<String, Object> requestParam) {
        if (null != requestParam) {
            for (Map.Entry<String, Object> param : requestParam.entrySet()) {
                httpRequest.getParams().setParameter(param.getKey(), param.getValue());
            }
        }
    }

    /**
     * 从流中读取字符串
     *
     * @param is
     * @param charSet
     * @return
     */
    public static String convertStreamToString(InputStream is, String charSet) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, charSet));
            String line;
            while (null != (line = reader.readLine())) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            logger.error("读取http返回流失败:", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("读取http返回流失败:", e);
            }
        }
        return sb.toString();
    }

}
