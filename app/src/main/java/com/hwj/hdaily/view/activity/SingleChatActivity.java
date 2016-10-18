package com.hwj.hdaily.view.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwj.hdaily.R;
import com.hwj.hdaily.base.BaseActivity;
import com.hwj.hdaily.conf.Constants;
import com.hwj.hdaily.listener.OnMessageStatusCallback;
import com.hwj.hdaily.manager.ChatManager;
import com.hwj.hdaily.manager.RxBus;
import com.hwj.hdaily.model.entity.chat.ChatUser;
import com.hwj.hdaily.utils.KeyboardUtils;
import com.hwj.hdaily.utils.ToastUtils;
import com.hwj.hdaily.utils.UIUtils;
import com.hyphenate.chat.EMMessage;

import butterknife.BindView;
import butterknife.OnTextChanged;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 单聊页面
 * <p>
 * 时间: 16/10/17
 */

public class SingleChatActivity extends BaseActivity implements View.OnClickListener, OnMessageStatusCallback {

    @BindView(R.id.toolbar_single_chat)
    Toolbar toolbar_single_chat;

    @BindView(R.id.tv_friend_name)
    TextView tv_friend_name;

    @BindView(R.id.srl_single_chat)
    SwipeRefreshLayout srl_single_chat;

    @BindView(R.id.et_send)
    EditText et_send;

    @BindView(R.id.btn_send)
    Button btn_send;

    @BindView(R.id.iv_more)
    ImageView iv_more;

    @BindView(R.id.ll_more)
    LinearLayout ll_more;

    private ChatUser mChatUser;
    private CompositeSubscription mCs = new CompositeSubscription();
    private boolean isOpen;             //设置底部布局是否处于打开状态,默认为false

    @Override
    protected void initInject() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_single_chat;
    }

    @Override
    public void initData() {
        Intent singleChatIntent = getIntent();
        mChatUser = (ChatUser) singleChatIntent.getSerializableExtra(Constants.CHAT_USER);

        mCs.add(RxBus.getDefault()
                .toObserverable(ChatUser.class)
                .subscribe(
                        new Action1<ChatUser>() {
                            @Override
                            public void call(ChatUser chatUser) {

                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                ToastUtils.showToast(throwable.getMessage());
                            }
                        }));
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar_single_chat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        tv_friend_name.setText(mChatUser.getClientId());
    }

    @Override
    public void initListener() {
        srl_single_chat.setColorSchemeResources(
                android.R.color.holo_purple,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        srl_single_chat.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                UIUtils.postTaskDelay(new Runnable() {
                    @Override
                    public void run() {
                        srl_single_chat.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        btn_send.setOnClickListener(this);
        iv_more.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCs.unsubscribe();
    }

    /**
     * 发送文字消息
     *
     * @param text 消息内容
     */
    private void sendTextMessage(String text) {
        ChatManager.getInstance().sendTextMessage(text, mChatUser.getClientId(), this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                String text = et_send.getText().toString().trim();
                if (TextUtils.isEmpty(text)) {
                    ToastUtils.showToast("消息内容不能为空");
                } else {
                    sendTextMessage(text);
                    UIUtils.clearInputText(et_send);
                }
                break;
            case R.id.iv_more:
                toggleBottom();
                break;
            default:
                break;
        }
    }

    /**
     * 当输入框中内容改变时调用
     *
     * @param inputText
     */
    @OnTextChanged(R.id.et_send)
    void onMessgeInputChanged(CharSequence inputText) {
        String text = inputText.toString().trim();                   //得到输入的文本内容
        if (!TextUtils.isEmpty(text)) { //如果输入内容不为空,则显示发送按钮,隐藏更多的icon
            btn_send.setVisibility(View.VISIBLE);
            iv_more.setVisibility(View.GONE);
        } else {
            btn_send.setVisibility(View.GONE);
            iv_more.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSuccess(EMMessage message) {
        System.out.println(message.getFrom());
    }

    @Override
    public void onError(EMMessage message, String error) {
        System.out.println(error);
    }

    @Override
    public void onProgress(int progress, String status) {

    }

    private void toggleBottom() {
        if (isOpen) {   //打开-->关闭
            closeBottom();                          //关闭
        } else {        //关闭-->打开
            openBottom();                           //打开
            KeyboardUtils.hideSoftInput(this);      //关闭软键盘
        }
    }

    /**
     * 打开底部更多布局
     */
    private void openBottom() {
        ll_more.setVisibility(View.VISIBLE);    //打开
        isOpen = true;
    }

    /**
     * 关闭底部更多布局
     */
    private void closeBottom() {
        ll_more.setVisibility(View.GONE);
        isOpen = false;
    }

    private void refreshMessageAdapter() {

    }
}
