package com.yanhee.chatai.api.domain.zsxq.model.res;

import com.yanhee.chatai.api.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.zsxq.model.res
 * @Author: chenyanxi
 * @Date: 2023-03-19  16:24
 * @Version: 1.0
 * @Description: 结果数据
 */
@SuppressWarnings("all")
public class RespData {

    private List<Topics> topics;

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }
}
