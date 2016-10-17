package com.hwj.hdaily.model.entity.chat;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/17
 */

public class ChatUser {
    private String clientId;            //clientId即用户名,唯一标示
    private String avatarUrl;              //头像

    public ChatUser(String clientId, String avatarUrl) {
        this.clientId = clientId;
        this.avatarUrl = avatarUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
