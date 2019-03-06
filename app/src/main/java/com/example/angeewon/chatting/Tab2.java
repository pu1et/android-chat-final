package com.example.angeewon.chatting;

import android.content.Intent;
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

public class Tab2 extends Fragment{
    private SimpleAdapter simpleAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1,null);
        ListView listView = (ListView) view.findViewById(R.id.idList);

        // 아이디+이름 리스트 생성
        ArrayList<HashMap<String,String>> Friends_list = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> item;
        CharSequence[] name_list= getResources().getTextArray(R.array.name_list);
        CharSequence[] chat_list = getResources().getTextArray(R.array.chat_list);
        for(int i=0; i< name_list.length;i++) {
            item = new HashMap<String, String>();
            item.put("name_list", name_list[i].toString());
            item.put("chat_list", chat_list[i].toString());
            Friends_list.add(item);
        }

        simpleAdapter = new SimpleAdapter(getActivity(),Friends_list,R.layout.list_form,new String[]{"name_list","chat_list"},new int[]{R.id.id_text, R.id.name_text});
        //simpleAdapter = new SimpleAdapter(getActivity(),Friends_list,android.R.layout.two_line_list_item,new String[]{"id_list","name_list"}, new int[]{android.R.id.text1, android.R.id.text2});

        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = parent.getItemAtPosition(position).toString();
                String[] strText1 = strText.split(",");
                strText1 = strText1[0].split("=");
                showToast(strText1[1]);
                Intent intent = new Intent(getContext(),ChatActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        return view; // 표시할 뷰 반환
        // return inflater.inflate(R.layout.tab1,container,false);
    }

    public void showToast(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}
