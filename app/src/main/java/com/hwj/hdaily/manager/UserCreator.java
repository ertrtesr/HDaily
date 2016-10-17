package com.hwj.hdaily.manager;

import com.hwj.hdaily.model.entity.chat.ChatUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/17
 */

public class UserCreator {
    private static List<ChatUser> mUsers = new ArrayList<>();

    /**
     * 初始化一些用户
     */
    static {
        mUsers.add(new ChatUser("Tom", "http://www.avatarsdb.com/avatars/tom_and_jerry2.jpg"));
        mUsers.add(new ChatUser("Jerry", "http://www.avatarsdb.com/avatars/jerry.jpg"));
        mUsers.add(new ChatUser("Harry", "http://www.avatarsdb.com/avatars/young_harry.jpg"));
        mUsers.add(new ChatUser("William", "http://www.avatarsdb.com/avatars/william_shakespeare.jpg"));
        mUsers.add(new ChatUser("Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
    }

    public static List<ChatUser> getAllUsers() {
        return mUsers;
    }
}
