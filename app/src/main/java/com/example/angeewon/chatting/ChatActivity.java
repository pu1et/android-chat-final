package com.example.angeewon.chatting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class ChatActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        ChatView chatView = (ChatView) findViewById(R.id.chat_view);
        chatView.addMessage(new ChatMessage("안녕",System.currentTimeMillis(),ChatMessage.Type.RECEIVED,"이씨"));
        chatView.addMessage(new ChatMessage("응",System.currentTimeMillis(),ChatMessage.Type.SENT));
        chatView.addMessage(new ChatMessage("반가워", System.currentTimeMillis(), ChatMessage.Type.RECEIVED, "이씨"));
        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                // 메시지 보낼때 할거
                Toast.makeText(ChatActivity.this,"send Message",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        chatView.setTypingListener(new ChatView.TypingListener() {
            @Override
            public void userStartedTyping() {
                // 타이핑 시작하면 할 거
            }

            @Override
            public void userStoppedTyping() {
                // 타이핑 끝나면 할 거
            }
        });
    }

    @Override
    public void onBackPressed(){
       Intent intent = new Intent(ChatActivity.this, MainActivity.class);
       startActivity(intent);
       this.finish();
    }
}