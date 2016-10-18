package com.hwj.hdaily.manager;

import com.hwj.hdaily.listener.OnMessageStatusCallback;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/14
 */

public class ChatManager {
    private static ChatManager mInstance;
    private EMChatManager mChatManager;

    private ChatManager() {
        mChatManager = EMClient.getInstance().chatManager();
    }

    public static ChatManager getInstance() {
        if (mInstance == null) {
            synchronized (ChatManager.class) {
                if (mInstance == null) {
                    mInstance = new ChatManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送文字消息
     *
     * @param text
     * @return
     */
    public void sendTextMessage(String text, String toChatUsername, final OnMessageStatusCallback callback) {
        final EMMessage message = EMMessage.createTxtSendMessage(text, toChatUsername);
        message.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                callback.onSuccess(message);
            }

            @Override
            public void onError(int code, String error) {
                callback.onError(message, error);
            }

            @Override
            public void onProgress(int progress, String status) {
                callback.onProgress(progress, status);
            }
        });

        //发送消息
        mChatManager.sendMessage(message);
    }

    /**
     * 发送图片消息
     *
     * @param imagePath      本地图片路径
     * @param toChatUsername
     */
    public void sendImageMessage(String imagePath, String toChatUsername) {
        EMMessage message = EMMessage.createImageSendMessage(imagePath, false, toChatUsername);
        mChatManager.sendMessage(message);
    }

    /**
     * 发送语音消息
     *
     * @param filePath
     * @param length         录音时间
     * @param toChatUsername
     */
    public void sendVoiceMessage(String filePath, int length, String toChatUsername) {
        EMMessage message = EMMessage.createVoiceSendMessage(filePath, length, toChatUsername);
        mChatManager.sendMessage(message);
    }

    /**
     * 根据用户名获取conversation
     *
     * @param clientId 用户名
     * @return
     */
    public EMConversation getConversation(String clientId) {
        EMConversation conversation = mChatManager.getConversation(clientId);
        return conversation;
    }
}
