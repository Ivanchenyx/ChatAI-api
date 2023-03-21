package com.yanhee.chatai.api.domain.zsxq.model.vo;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.zsxq.model.vo
 * @Author: chenyanxi
 * @Date: 2023-03-19  16:09
 * @Version: 1.0
 * @Description:
 */
@SuppressWarnings("all")
public class UserSpecific {
    private boolean liked;

    private boolean subscribed;

    public void setLiked(boolean liked){
        this.liked = liked;
    }
    public boolean getLiked(){
        return this.liked;
    }
    public void setSubscribed(boolean subscribed){
        this.subscribed = subscribed;
    }
    public boolean getSubscribed(){
        return this.subscribed;
    }
}
