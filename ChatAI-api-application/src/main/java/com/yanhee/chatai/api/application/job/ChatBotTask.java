package com.yanhee.chatai.api.application.job;

import com.alibaba.fastjson.JSON;
import com.yanhee.chatai.api.domain.chatgpt.IOpenAIApi;
import com.yanhee.chatai.api.domain.zsxq.IZsxqApi;
import com.yanhee.chatai.api.domain.zsxq.model.aggregate.UnAnsweredQuestionsAggregates;
import com.yanhee.chatai.api.domain.zsxq.model.vo.Question;
import com.yanhee.chatai.api.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.application.job
 * @Author: chenyanxi
 * @Version: 1.0
 * @Description: 任务——轮询爬取问题信息并利用chatgpt回复
 */
@SuppressWarnings("all")
@Configuration
@EnableScheduling
public class ChatBotTask {

    private static final Logger log = LoggerFactory.getLogger(ChatBotTask.class);

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

    @Scheduled(cron = "0 0/1 * * * ?")  // 每一分钟爬取一次
    public void run(){
        try {

            // 防止查询频率过于规律被抓
            if (new Random().nextBoolean()) {
                log.info("随机打烊中...");
                return;
            }

            // 知识星球api检索待回答问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            String jsonString = JSON.toJSONString(unAnsweredQuestionsAggregates);
            log.info("爬取问题信息：{}", jsonString);
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if(topics.isEmpty() || topics == null) {  // topics == null 是以防万一出异常，直接没有生成topics
                log.info("本次轮询没有检索到等待回答问题");
                return;
            }
            Topics topic = topics.get(0);  // 一次就回答一个，回答多个有风险
            String topicId = topic.getTopic_id();
            String question = topic.getQuestion().getText().trim();  //trim()可以移除字符串两侧的空白字符或其他预定义字符
            // chatgpt api 回答问题，得到结果
            String answer = openAI.doChatGPT(chatGPTApiKey, question);
            // 知识星球api将结果回答给提问者
            boolean status = zsxqApi.answer(groupId, cookie, topicId, answer, false);
            log.info("状态：{}。问题号{}的问题\"{}\"，其答案是\"{}\"。", status, topicId, question, answer);
        } catch (Exception e) {
            log.error("问题处理异常", e);
        }
    }


}
