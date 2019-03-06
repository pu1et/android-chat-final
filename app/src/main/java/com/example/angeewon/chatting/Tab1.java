package com.example.angeewon.chatting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Tab1 extends Fragment{

    private SimpleAdapter simpleAdapter;
    private ArrayList<HashMap<String,String>> Friends_list;
    private ListView listView;
    private String id, name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1,null);
        listView = (ListView) view.findViewById(R.id.idList);

        //id = getArguments().getString("id");
        //name = getArguments().getString("name");
        //showToast(id);


        // 아이디+이름 리스트 생성
        Friends_list = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> item;
        CharSequence[] id_list= getResources().getTextArray(R.array.id_list);
        CharSequence[] name_list = getResources().getTextArray(R.array.name_list);
        for(int i=0; i< id_list.length;i++) {
            item = new HashMap<String, String>();
            item.put("id_list", id_list[i].toString());
            item.put("name_list", name_list[i].toString());
            Friends_list.add(item);
        }

        simpleAdapter = new SimpleAdapter(getActivity(),Friends_list,R.layout.list_form,new String[]{"name_list","id_list"},new int[]{R.id.id_text, R.id.name_text});
        // default설정값들 사용
        //simpleAdapter = new SimpleAdapter(getActivity(),Friends_list,android.R.layout.two_line_list_item,new String[]{"id_list","name_list"}, new int[]{android.R.id.text1, android.R.id.text2});

        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String strText = parent.getItemAtPosition(position).toString();
                    String[] strText1 = strText.split(",");
                    strText1 = strText1[0].split("=");
                    showToast(strText1[1]);
            }
        });


        return view; // 표시할 뷰 반환
       // return inflater.inflate(R.layout.tab1,container,false);
    }

    public void showToast(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    public void add_friend(String id, String name){
        HashMap<String,String> item = new HashMap<String, String>();
        item.put("id_list",id);
        item.put("name_list",name);
        Friends_list.add(item);

        simpleAdapter = new SimpleAdapter(getActivity(),Friends_list,R.layout.list_form,new String[]{"name_list","id_list"},new int[]{R.id.id_text, R.id.name_text});
        // default설정값들 사용
        //simpleAdapter = new SimpleAdapter(getActivity(),Friends_list,android.R.layout.two_line_list_item,new String[]{"id_list","name_list"}, new int[]{android.R.id.text1, android.R.id.text2});

        listView.setAdapter(simpleAdapter);
    }
}
