package com.yanhee.chatai.api.domain.chatgpt.model.aggregate;

import com.yanhee.chatai.api.domain.chatgpt.model.vo.Choices;

import java.util.List;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.chatgpt.model.aggregate
 * @Author: chenyanxi
 * @Version: 1.0
 * @Description: 回答聚合信息
 */
@SuppressWarnings("all")
public class AIAnswerAggregate {

    private String id;

    private String object;

    private int created;

    private String model;

    private List<Choices> choices;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setObject(String object){
        this.object = object;
    }
    public String getObject(){
        return this.object;
    }
    public void setCreated(int created){
        this.created = created;
    }
    public int getCreated(){
        return this.created;
    }
    public void setModel(String model){
        this.model = model;
    }
    public String getModel(){
        return this.model;
    }
    public void setChoices(List<Choices> choices){
        this.choices = choices;
    }
    public List<Choices> getChoices(){
        return this.choices;
    }
}
