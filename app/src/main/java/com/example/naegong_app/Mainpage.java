package com.example.naegong_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Mainpage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    public CalendarView calendarView;

    //여기엔 적용할 프래그먼트 화면
    private Viewpage viewPage;
    private Stickerbooktab frag1;
    private Rankingtab frag2;
    private Studyvlogtab frag4;
    private Settingtab frag5;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navi);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.stickertabmenu:
                        setFrag(0);
                        break;
                    case R.id.rankingtabmenu:
                        setFrag(1);
                        break;
                    case R.id.Hometabmenu:
                        setFrag(2);
                        break;
                    case R.id.studyvlogtabmenu:
                        setFrag(3);
                        break;
                    case R.id.settingtabmenu:
                        setFrag(4);
                }
                return true;
            }
        });
        calendarView = findViewById(R.id.calendarView);
        /*
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            }
        });*/
        //프레그먼트 화면 함수
        viewPage = new Viewpage();
        frag1 = new Stickerbooktab();
        frag2 = new Rankingtab();
        frag4 = new Studyvlogtab();
        frag5 = new Settingtab();
        setFrag(0); // 첫 프래그먼트 화면 지정
    }

    // 프레그먼트 교체
    private void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft= fm.beginTransaction();
        switch (n)
        {
            case 0:
                ft.replace(R.id.bottom_layout,frag1);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.bottom_layout,frag2);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.bottom_layout,viewPage);
                ft.commit();
                break;

            case 3:
                ft.replace(R.id.bottom_layout,frag4);
                ft.commit();
                break;

            case 4:
                ft.replace(R.id.bottom_layout, frag5);
                ft.commit();
                break;
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.i("Main", "onStart()");
    }

}
