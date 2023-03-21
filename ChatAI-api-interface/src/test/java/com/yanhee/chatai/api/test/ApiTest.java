package com.yanhee.chatai.api.test;

import com.alibaba.fastjson.JSON;
import com.yanhee.chatai.api.domain.chatgpt.IOpenAIApi;
import com.yanhee.chatai.api.domain.zsxq.IZsxqApi;
import com.yanhee.chatai.api.domain.zsxq.model.aggregate.UnAnsweredQuestionsAggregates;
import com.yanhee.chatai.api.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.test
 * @Author: chenyanxi
 * @Date: 2023-03-20  14:45
 * @Version: 1.0
 * @Description: 单元测试
 */
@SuppressWarnings("all")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private static final Logger log = LoggerFactory.getLogger(ApiTest.class);
    @Value("${ChatAI-api.groupId}")
    private String groupId;

    @Value("${ChatAI-api.cookie}")
    private String cookie;

    @Value("${ChatAI-api.chatGPTApiKey}")
    private String chatGPTApiKey;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAIApi openAI;

    @Test
    public void testZsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        String jsonString = JSON.toJSONString(unAnsweredQuestionsAggregates);
        log.info("爬取问题信息测试结果：{}", jsonString);

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for(Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            log.info("问题ID：{}，提问内容：{}", topicId, text);

            zsxqApi.answer(groupId, cookie, topicId, text, false);
        }
    }

    @Test
    public void testOpenAIApi() throws IOException {
        String answer = openAI.doChatGPT(chatGPTApiKey, "你是谁");
        log.info("chatgpt回答问题测试结果：{}", answer);
    }

}
