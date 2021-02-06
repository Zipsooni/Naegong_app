package com.example.naegong_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;


public class StudyActivity extends AppCompatActivity {

    // study room 만들기 (최초 룸 개설)
    // study room 참가 (이미 존재하는 방에 참가하는 경우)
    // study room mode // 모드에 따라 다른 환경 설정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        //intent data 수
        Intent intent = new Intent(this.getIntent());
        String roomname = intent.getExtras().getString("roomname");
        String hashtag = intent.getExtras().getString("hashtag");
        String number = intent.getExtras().getString("number");
        Boolean mode = intent.getExtras().getBoolean("mode");
        //jitsi meet room 개설
       try {
            JitsiMeetConferenceOptions options
                    = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL("https://meet.jit.si"))
                    .setRoom(roomname)
                    .setAudioMuted(mode)
                    .setVideoMuted(false)
                    .setAudioOnly(false)
                    .setWelcomePageEnabled(false)
                    .build();

           JitsiMeetActivity.launch(this, options);
           System.out.println("try");

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("catch");
        }
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy()");
        startActivity(new Intent(this,MainActivity.class));
        super.onDestroy();
    }

    @Override
    public void finish() {
        System.out.println("finish()");
        startActivity(new Intent(this,MainActivity.class));

        super.finish();
        }


    //뒤로가기 버튼 눌렀을 때 Main으로 이동 안 되고 PIP모드가 동작함. 왜지?
    @Override
    public void onBackPressed() {
        System.out.println("Back Pressed");
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
    }





}
