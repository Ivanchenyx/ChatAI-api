package com.yanhee.chatai.api.domain.zsxq.model.vo;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.zsxq.model.vo
 * @Author: chenyanxi
 * @Date: 2023-03-19  16:05
 * @Version: 1.0
 * @Description: 提问者细节信息
 */
@SuppressWarnings("all")
public class OwnerDetail {

    private int questions_count;

    private String join_time;

    public void setQuestions_count(int questions_count){
        this.questions_count = questions_count;
    }
    public int getQuestions_count(){
        return this.questions_count;
    }
    public void setJoin_time(String join_time){
        this.join_time = join_time;
    }
    public String getJoin_time(){
        return this.join_time;
    }
}
