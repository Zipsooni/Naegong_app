package com.example.naegong_app;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.data.OAuthLoginState;

public class Settingtab extends Fragment {

    Context context;

    ImageView accountinfo;
    LinearLayout notice;
    LinearLayout camsetting;
    LinearLayout notification;
    LinearLayout customerservice;
    LinearLayout currentversion;
    LinearLayout logout;

    Session session;

    OAuthLogin mOAuthLoginModule;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.settinglayout, container, false);
        context = view.getContext();

        accountinfo = view.findViewById(R.id.account_info);
        notice = view.findViewById(R.id.notice);
        camsetting = view.findViewById(R.id.camera_setting);
        notification = view.findViewById(R.id.notification);
        customerservice = view.findViewById(R.id.customerservice);
        currentversion = view.findViewById(R.id.currentversion);
        logout = view.findViewById(R.id.logout);

        session = Session.getCurrentSession();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //카카오 로그아웃
                if (session.checkAndImplicitOpen()) {
                    UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                        @Override
                        public void onCompleteLogout() {

                            Intent intent = new Intent(getContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
                }


                //네이버 로그아웃
                mOAuthLoginModule = OAuthLogin.getInstance();
                String loginstate = mOAuthLoginModule.getState(context).toString();

                if(!loginstate.equals("NEED_LOGIN")){
                    mOAuthLoginModule.logout(context);
                    mOAuthLoginModule.logoutAndDeleteToken(context);
                    Toast.makeText(context, "정상적으로 로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();

//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://nid.naver.com/nidlogin.logout"));
//                    startActivity(intent);

                    Intent intent1 = new Intent(context, MainActivity.class);
                    startActivity(intent1);
                }
            }
        });

        return view;
    }
}
