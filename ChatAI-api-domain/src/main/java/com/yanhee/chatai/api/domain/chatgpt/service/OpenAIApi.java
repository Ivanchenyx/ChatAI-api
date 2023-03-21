package com.yanhee.chatai.api.domain.chatgpt.service;

import com.alibaba.fastjson.JSON;
import com.yanhee.chatai.api.domain.chatgpt.IOpenAIApi;
import com.yanhee.chatai.api.domain.chatgpt.model.aggregate.AIAnswerAggregate;
import com.yanhee.chatai.api.domain.chatgpt.model.vo.Choices;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.chatgpt.service
 * @Author: chenyanxi
 * @Version: 1.0
 * @Description:
 */
@SuppressWarnings("all")
public class OpenAIApi implements IOpenAIApi {
    @Override
    public String doChatGPT(String chatGPTApiKey, String question) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + chatGPTApiKey);

        // 这里的model：text-davinci-003可以更换
        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"" + question + "\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswerAggregate aiAnswer = JSON.parseObject(jsonStr, AIAnswerAggregate.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice : choices) {
                answers.append(choice.getText());
            }
            return answers.toString();
        } else {
            throw new RuntimeException("api.openai.com Error Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
