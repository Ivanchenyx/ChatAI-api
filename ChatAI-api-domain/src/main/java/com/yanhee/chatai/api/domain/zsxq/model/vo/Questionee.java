package com.yanhee.chatai.api.domain.zsxq.model.vo;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.zsxq.model.vo
 * @Author: chenyanxi
 * @Date: 2023-03-19  16:06
 * @Version: 1.0
 * @Description: 提问内容
 */
@SuppressWarnings("all")
public class Questionee {

    private String user_id;

    private String name;

    private String avatar_url;

    private String description;

    private String location;

    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getUser_id(){
        return this.user_id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAvatar_url(String avatar_url){
        this.avatar_url = avatar_url;
    }
    public String getAvatar_url(){
        return this.avatar_url;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }
}
