package com.hwj.hdaily.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hwj.hdaily.R;
import com.hwj.hdaily.holder.FriendHolder;
import com.hwj.hdaily.model.entity.chat.ChatUser;
import com.hwj.hdaily.utils.UIUtils;

import java.util.List;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/17
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {

    private List<ChatUser> mChatUserList;

    public FriendAdapter(List<ChatUser> chatUserList) {
        mChatUserList = chatUserList;
    }

    @Override
    public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtils.inflate(R.layout.item_friend, parent);
        return new FriendHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FriendHolder holder, int position) {
        if (mChatUserList != null && mChatUserList.size() > 0) {
            ChatUser chatUser = mChatUserList.get(position);
            UIUtils.loadImage(chatUser.getAvatarUrl(), holder.iv_item_friend);
            holder.tv_item_friend.setText(chatUser.getClientId());
        }
    }

    @Override
    public int getItemCount() {
        if (mChatUserList != null && mChatUserList.size() > 0)
            return mChatUserList.size();
        return 0;
    }

    public void setChatUserList(List<ChatUser> chatUserList) {
        this.mChatUserList = chatUserList;
    }
}
