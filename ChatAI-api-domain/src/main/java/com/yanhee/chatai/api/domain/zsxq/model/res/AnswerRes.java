package com.yanhee.chatai.api.domain.zsxq.model.res;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.zsxq.model.res
 * @Author: chenyanxi
 * @Version: 1.0
 * @Description: 请求问答接口结果，是否成功
 */
@SuppressWarnings("all")
public class AnswerRes {

    private boolean succeeded;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

}
