package com.yanhee.chatai.api.domain.zsxq.model.aggregate;

import com.yanhee.chatai.api.domain.zsxq.model.res.RespData;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.zsxq.model.aggregate
 * @Author: chenyanxi
 * @Date: 2023-03-19  16:20
 * @Version: 1.0
 * @Description: 未回答问题的聚合信息
 */
@SuppressWarnings("all")
public class UnAnsweredQuestionsAggregates {

    private boolean succeeded;  // 接口是否请求成功

    private RespData resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespData getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }
}
