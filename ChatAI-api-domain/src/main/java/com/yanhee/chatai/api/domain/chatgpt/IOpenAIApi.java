package com.yanhee.chatai.api.domain.chatgpt;

import java.io.IOException;

/**
 * @Project: ChatAI-api
 * @Package: com.yanhee.chatai.api.domain.chatgpt
 * @Author: chenyanxi
 * @Date: 2023-03-21  10:45
 * @Version: 1.0
 * @Description: chatgpt api 接口：https://beta.openai.com/account/api-keys
 */
@SuppressWarnings("all")
public interface IOpenAIApi {

    String doChatGPT(String chatGPTApiKey, String question) throws IOException;

}
