package com.example.angeewon.chatting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private String test;
    private URLConnector task;
    private com.getbase.floatingactionbutton.FloatingActionButton fab1, fab2, fab3;
    private FloatingActionsMenu fab;
    private String select_item="";
    public View mView;
    private AlertDialog.Builder aBuilder;
    private String select_fab="";
    private ArrayAdapter<CharSequence> arrayAdapter;
    public AlertDialog dialog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        test = "http://localhost"; // localhost address, 실제 아이피 적어야만 함(localhostx)
        task = new URLConnector(test);

        task.start();a

        try{
            task.join(); //
            System.out.println("waiting... for result");
        }catch(InterruptedException e){

        }

        String result = task.getResult();

        System.out.println(result); // 결과 값 콘솔창에 출력
        */


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Button clicked");
            }
        });

        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab_action1);
        fab2 = findViewById(R.id.fab_action2);
        fab3 = findViewById(R.id.fab_action3);

        fab1.setOnClickListener(onButtonClick());
        fab2.setOnClickListener(onButtonClick());
        fab3.setOnClickListener(onButtonClick());

        // 아이디 선택 창 설정
        aBuilder = new AlertDialog.Builder(MainActivity.this);
        mView = getLayoutInflater().inflate(R.layout.choose_user,null);


        final Spinner spinner = (Spinner)mView.findViewById(R.id.spinner_search);

        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.id_list, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        aBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if (select_fab.equals("fab1")) {
                        showToast("fab1 " + select_item);
                        // db에 친구 리스트 넣기
                   /*
                   Tab1 tab1 = new Tab1();
                   Bundle bundle = new Bundle(2);
                   bundle.putString("id",select_item);
                   bundle.putString("name","남씨");
                   tab1.setArguments(bundle);*/
                    } else if (select_fab.equals("fab2")) {
                        showToast("fab2 " + select_item);
                        // db에 채팅 추가하기
                        if(select_item.equals("bbbb2222")) {
                            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                            startActivity(intent);
                            MainActivity.this.finish();
                        }
                    } else {
                        showToast("fab3 " + select_item);
                        // 전화걸기
                        String tel = "tel:01012345678";
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(tel)));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        aBuilder.setView(mView);
        dialog = aBuilder.create();
}

    public View.OnClickListener onButtonClick(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(v == fab1) {
                    select_fab ="fab1";
                    dialog.show();
                }
                else if(v == fab2) {
                    select_fab ="fab2";
                    dialog.show();
                }
                else {
                    select_fab ="fab3";
                    dialog.show();
                }
            }
        };
    }

    public void choose_user() {
        //aBuilder.setView(mView);
       // dialog = aBuilder.create();
        dialog.show();
    }

    public void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Tab1 tab1 = new Tab1();
                    return tab1;
                    /*
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction t = manager.beginTransaction();
                    Tab1 tab1 = new Tab1();
                    Bundle bundle = new Bundle(2);
                    bundle.putString("id",select_item);
                    bundle.putString("name","남씨");
                    tab1.setArguments(bundle);
                    t.add(R.id.tabItem,tab1);
                    t.commit();
                   */
                case 1:
                    Tab2 tab2 = new Tab2();
                    return tab2;
                case 2:
                    Tab3 tab3 = new Tab3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }

        public View getView(){
            return null;
        }
    }

        public class idListAdapter extends ArrayAdapter<String> {
            public idListAdapter(Context context) {
                super(context, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.id_list));
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return super.getView(position,convertView,parent);
            }
        }

    @Override
    public void onBackPressed(){
    }

    // 스피너 선택했을 때
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        select_item = selectedItem;
        /*
        Intent intent = new Intent(MainActivity.this,ChatActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
        */
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
