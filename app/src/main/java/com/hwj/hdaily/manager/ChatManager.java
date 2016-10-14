package com.hwj.hdaily.manager;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/14
 */

public class ChatManager {
    private ChatManager mInstance;

    private ChatManager() {
    }

    public ChatManager getInstance() {
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
    public void sendTextMessage(String text, String toChatUsername) {
        EMMessage message = EMMessage.createTxtSendMessage(text, toChatUsername);

        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    /**
     * 发送图片消息
     *
     * @param imagePath      本地图片路径
     * @param toChatUsername
     */
    public void sendImageMessage(String imagePath, String toChatUsername) {
        EMMessage message = EMMessage.createImageSendMessage(imagePath, false, toChatUsername);
        EMClient.getInstance().chatManager().sendMessage(message);
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
        EMClient.getInstance().chatManager().sendMessage(message);
    }
}
