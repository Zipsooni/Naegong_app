package com.example.naegong_app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Addroom_dialog extends Dialog{

    private CustomDialogClickListener customDialogClickListener;
    private Context context;
    private EditText roomname, hashtag, num;
    private Button save, cancel;
    private boolean mode = false;
    public Addroom_dialog(@NonNull Context context, CustomDialogClickListener customDialogClickListener) {
        super(context);
        this.context = context;
        this.customDialogClickListener = customDialogClickListener;
    }

    public void setmode(Boolean m){ // study room mode에 따라 달라짐.
        mode = m; //False : 소리, 마이크 켜짐, True : 조용히 공부
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addroom);

        //항목 선언
        roomname = (EditText)findViewById(R.id.roomname_type);
        System.out.println("roomname"+ roomname.getText().toString());
        hashtag = (EditText)findViewById(R.id.hashtag_type);
        num = (EditText)findViewById(R.id.number_type);
        save = (Button)findViewById(R.id.makestudyroom);
        cancel = (Button)findViewById(R.id.cancelstudyroom);



        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) { // 방만들기 버튼 누르면 Jitsi 실행

                try {
                    if (roomname.getText().toString().length() > 0)  roomopen();

                } catch (MalformedURLException e) { e.printStackTrace(); }
            }
        });

        cancel.setOnClickListener(v->{
            this.customDialogClickListener.onNegativeClick(); //취소 버튼 (아직 없엉)
            dismiss();
        });
    }


    @Override
    public void onBackPressed() {
        System.out.println("Back Pressed");
        dismiss();
        super.onBackPressed();
    }

    public void roomopen() throws MalformedURLException {
        JitsiMeetConferenceOptions options
                = new JitsiMeetConferenceOptions.Builder()
                .setServerURL(new URL("https://meet.jit.si"))
                .setRoom(roomname.getText().toString())
                .setAudioMuted(mode) //homeatab1에서는 소리 켜짐, hometab2에서는 소리 꺼
                .setVideoMuted(false)
                .setAudioOnly(false)
                .setWelcomePageEnabled(false)
                .setFeatureFlag("pip.enabled", false) // pip mode 불가
                .setFeatureFlag("chat.enabled", false) // 채팅 불가
                .setFeatureFlag("overflow-menu.enabled", false)
                .setFeatureFlag("audio-mute.enabled", false) //음소거 버튼 버튼 표시 안 함
                .setFeatureFlag("invite.enabled", false) // 사람 초대 기능 불가
                .setFeatureFlag("conference-timer.enabled", false) // 회의 창 타이머 off
                .build();

        JitsiMeetActivity.launch(getContext(), options);
    }

}

/*
public class Addroom_dialog {
    private Context context;
    public Addroom_dialog(Context context){ this.context = context; }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.addroom);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        EditText roomname = (EditText) dlg.findViewById(R.id.roomname_type); //방 이름
        EditText hashtag = (EditText) dlg.findViewById(R.id.hashtag_type); //해시태그
        EditText number = (EditText) dlg.findViewById(R.id.number_type); //인원 수

        String roomname_str = roomname.getText().toString();
        String hashtag_str = hashtag.getText().toString();
        String number_str = number.getText().toString();



        Button okButton = (Button) dlg.findViewById(R.id.makestudyroom);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number_s = Integer.parseInt(number.getText().toString());
                if(number_s > 20){
                    //토스트 메세지로 경고창
                    Toast toast = Toast.makeText(context, "최대 인원 수는 10명입니다.", Toast.LENGTH_SHORT);
                    //토스트 메시지 띄울 위치
                    int offsetX = 0;
                    int offsetY = 0;
                    //토스트메시지 글씨크기 지정
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                    //토스트 메시지 위치 최종 지정
                    toast.setGravity(Gravity.CENTER, offsetX, offsetY);
                    toast.show();
                } else{


                    //인원 수나 해시태그, 이름에 문제 없으면 intent로 방 액티비티 넘겨주기
                    Intent intent =  new Intent(dlg.getContext(), StudyActivity.class);
                    intent.putExtra("roomname", roomname_str);
                    intent.putExtra("hashtag", hashtag_str);
                    intent.putExtra("number", number_str);

                    dlg.getContext().startActivity(intent);

                    System.out.println("else");
                }

                // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
                // 커스텀 다이얼로그에서 입력한 메시지를 대입한다.
//                main_label.setText(roomname.getText().toString());
//                Toast.makeText(context, "\"" + "방 이름은 " +  roomname.getText().toString() + "\" 입니다.", Toast.LENGTH_SHORT).show();
//
//                main_label.setText(hashtag.getText().toString());
//                Toast.makeText(context, "\"" + "해시태그로 " +  hashtag.getText().toString() + "\" 를 입력했습니다.", Toast.LENGTH_SHORT).show();


                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }

        });
    }

}
*/