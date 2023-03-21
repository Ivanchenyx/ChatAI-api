package com.yanhee.chatai.api.domain.zsxq.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanhee.chatai.api.domain.zsxq.IZsxqApi;
import com.yanhee.chatai.api.domain.zsxq.model.aggregate.UnAnsweredQuestionsAggregates;
import com.yanhee.chatai.api.domain.zsxq.model.req.AnswerReq;
import com.yanhee.chatai.api.domain.zsxq.model.req.ReqData;
import com.yanhee.chatai.api.domain.zsxq.model.res.AnswerRes;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.zsxq.service
 * @Author: chenyanxi
 * @Date: 2023-03-19  16:32
 * @Version: 1.0
 * @Description: 知识星球API 接口实现
 */
@SuppressWarnings("all")
@Service
public class ZsxqApi implements IZsxqApi {

    private static final Logger log = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", cookie);
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);  // 处理get请求
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();  // 获取响应结果
            String jsonStr = EntityUtils.toString(entity);
            log.info("爬取问题数据：groupId：{} jsonStr：{}", groupId, jsonStr);
            // 通过parseObject()，将json字符串转换成UnAnsweredQuestionsAggregates对象
            return JSON.parseObject(jsonStr, UnAnsweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("queryUnAnsweredQuestionsTopicId Error Code is " + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/answer");
        post.addHeader("cookie", cookie);
        post.addHeader("Content-Type", "application/json;charset=utf8");
        // 伪装浏览器
        post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36 Edg/110.0.1587.50");

       /*
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"答案 \\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";
        */
        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        String paramJson = JSONObject.toJSONString(answerReq);

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            log.info("回答问题结果：groupId：{} topicId：{} jsonStr：{}", groupId, topicId, jsonStr);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("answer Error Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
