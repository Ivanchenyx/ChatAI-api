package com.yanhee.chatai.api.domain.zsxq.model.vo;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.zsxq.model.vo
 * @Author: chenyanxi
 * @Date: 2023-03-19  15:59
 * @Version: 1.0
 * @Description: 星球信息
 */
@SuppressWarnings("all")
public class Group {

    private String group_id;

    private String name;

    private String type;

    public void setGroup_id(String group_id){
        this.group_id = group_id;
    }
    public String getGroup_id(){
        return this.group_id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
}
