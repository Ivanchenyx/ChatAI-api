package com.yanhee.chatai.api.domain.zsxq.model.req;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.zsxq.model.req
 * @Author: chenyanxi
 * @Version: 1.0
 * @Description: 请求问答接口信息，包装ReqData
 */
@SuppressWarnings("all")
public class AnswerReq {

    private ReqData req_data;

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }
}
