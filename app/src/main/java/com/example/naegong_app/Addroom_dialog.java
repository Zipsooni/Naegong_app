package com.example.naegong_app;

import android.app.Dialog;
import android.content.Context;
<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
=======
import android.util.TypedValue;
import android.view.Gravity;
>>>>>>> de0d1796355a0975ba96d2265ee2bdbb4a58f638
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

<<<<<<< HEAD
import androidx.annotation.NonNull;

public class Addroom_dialog extends Dialog{

    private CustomDialogClickListener customDialogClickListener;
=======
public class Addroom_dialog {
>>>>>>> de0d1796355a0975ba96d2265ee2bdbb4a58f638
    private Context context;

<<<<<<< HEAD
    public void setmode(Boolean m){ // study room mode에 따라 달라짐.
        mode = m; //False : 소리, 마이크 켜짐, True : 조용히 공부
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addroom);

        //항목 선언
        roomname = (EditText)findViewById(R.id.roomname_type);
        hashtag = (EditText)findViewById(R.id.hashtag_type);
        num = (EditText)findViewById(R.id.number_type);
        save = (Button)findViewById(R.id.makestudyroom);
        cancel = (Button)findViewById(R.id.cancelstudyroom);

        String roomname_str = roomname.getText().toString();
        String hashtag_str = hashtag.getText().toString();
        String number_str = num.getText().toString();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 방만들기 버튼 누르면 Jitsi 실행
                Intent intent = new Intent(context, StudyActivity.class);
                intent.putExtra("roomname", roomname.getText().toString());
                intent.putExtra("hashtag", hashtag.getText().toString());
                intent.putExtra("num", num.getText().toString());
                intent.putExtra("mode", mode);
                context.startActivity(intent);
            }
        });
        cancel.setOnClickListener(v->{
            this.customDialogClickListener.onNegativeClick(); //취소 버튼 (아직 없엉)
            dismiss();
        });
=======
    public Addroom_dialog(Context context){
        this.context = context;
>>>>>>> de0d1796355a0975ba96d2265ee2bdbb4a58f638
    }

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
        final EditText roomname = (EditText) dlg.findViewById(R.id.roomname_type); //방 이름
        final EditText hashtag = (EditText) dlg.findViewById(R.id.hashtag_type); //해시태그
        final EditText number = (EditText) dlg.findViewById(R.id.number_type); //인원 수
        final Button okButton = (Button) dlg.findViewById(R.id.makestudyroom);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number_s = Integer.parseInt(number.getText().toString());
                if(number_s > 10){
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

