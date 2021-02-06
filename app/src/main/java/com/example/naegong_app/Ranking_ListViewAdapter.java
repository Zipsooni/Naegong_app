package com.example.naegong_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Ranking_ListViewAdapter extends BaseAdapter {

    private CircleImageView userimg;
    private TextView username;
    private TextView usertime;

    //추가 될 데이터를 저장함
    private ArrayList<Ranking_ListViewItem> listViewItemRankingList = new ArrayList<Ranking_ListViewItem>();

    public Ranking_ListViewAdapter(){} //생성

    @Override
    //추가 될 데이터 개수 반
    public int getCount() { return listViewItemRankingList.size(); }

    @Override
    //position에 위치한 데이터를 화면에 출력하는데 사용될 View 리
    public View getView(int position, View convertView, ViewGroup parent){

        final int pos = position;
        final Context context = parent.getContext();
        if (convertView == null){ // inflate하여 convertView 참조 획득
            LayoutInflater inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ranking_item, parent, false);
        }
        userimg = (CircleImageView)convertView.findViewById(R.id.listview_img);
        username = (TextView)convertView.findViewById(R.id.listview_name);
        usertime = (TextView)convertView.findViewById(R.id.listview_time);

        Ranking_ListViewItem rankingListViewItem = listViewItemRankingList.get(position);

        userimg.setImageResource(rankingListViewItem.getIcon());
        username.setText(rankingListViewItem.getTitle());
        usertime.setText(rankingListViewItem.getContent());

        return convertView;
    }
    @Override
    public Object getItem(int position) { return listViewItemRankingList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    public void addItems(String title, int icon, String content){
        Ranking_ListViewItem item = new Ranking_ListViewItem();
        item.setTitle(title);
        item.setIcon(icon);
        item.setContent(content);

        listViewItemRankingList.add(item);
    }
}