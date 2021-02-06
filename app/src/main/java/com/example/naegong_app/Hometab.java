//study together 화면

package com.example.naegong_app;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Hometab extends Fragment {

    Context context;
    FloatingActionButton tgaddroom; //플로팅 액션 버튼(방 생성버튼)

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tglayout, container, false);

        context = view.getContext();
        tgaddroom = view.findViewById(R.id.tgaddRoom);

        //스터디 투게더룸 방 생성 버튼 클릭 시 발생하는 이벤트
        tgaddroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addroom_dialog addRoom = new Addroom_dialog(context); //방 생성하는 다이얼로그 변수
                addRoom.callFunction();
            }
        });

        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}


