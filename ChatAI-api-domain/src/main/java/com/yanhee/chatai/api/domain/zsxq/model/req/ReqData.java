package com.yanhee.chatai.api.domain.zsxq.model.req;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.zsxq.model.req
 * @Author: chenyanxi
 * @Date: 2023-03-19  16:10
 * @Version: 1.0
 * @Description: 请求数据
 */
@SuppressWarnings("all")
public class ReqData {
    private String text;

    private String[] image_ids = new String[]{};

    private boolean silenced;  // 该问题的回答是否公开，true不公开，false公开

    public ReqData(String text, boolean silenced) {
        this.text = text;
        this.silenced = silenced;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getImage_ids() {
        return image_ids;
    }

    public void setImage_ids(String[] image_ids) {
        this.image_ids = image_ids;
    }

    public boolean isSilenced() {
        return silenced;
    }

    public void setSilenced(boolean silenced) {
        this.silenced = silenced;
    }
}
