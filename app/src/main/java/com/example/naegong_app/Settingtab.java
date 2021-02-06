package com.example.naegong_app;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class Settingtab extends Fragment {

    Context context;

    ImageView accountinfo;
    LinearLayout notice;
    LinearLayout camsetting;
    LinearLayout notification;
    LinearLayout customerservice;
    LinearLayout currentversion;
    LinearLayout logout;

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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
        });

        return view;
    }
}
