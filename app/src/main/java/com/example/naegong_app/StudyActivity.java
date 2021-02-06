package com.example.naegong_app;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class StudyActivity extends JitsiMeetActivity{

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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
