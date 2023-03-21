package com.yanhee.chatai.api.domain.zsxq;

import com.yanhee.chatai.api.domain.zsxq.model.aggregate.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.zsxq
 * @Author: chenyanxi
 * @Date: 2023-03-19  15:58
 * @Version: 1.0
 * @Description: 知识星球API 接口
 */
@SuppressWarnings("all")
public interface IZsxqApi {
    /**
     * 返回未回答问题的聚合信息
     * @param groupId 知识星球id
     * @param cookie
     * @return
     * @throws IOException
     */
    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    /**
     * 回答问题，只需知道是否调用成功
     * @param groupId 知识星球id
     * @param cookie
     * @param topicId 问题id
     * @param text 回答内容
     * @param silenced 是否公开
     * @return
     * @throws IOException
     */
    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;
}
