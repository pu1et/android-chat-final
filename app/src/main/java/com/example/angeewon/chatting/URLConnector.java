package com.example.angeewon.chatting;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnector extends Thread{
    private String result;
    private String URL;

    public URLConnector(String url){
        URL = url; // Set up url when make URLConnector object
                   // i.e Set up URL in generator
    }

    @Override
    public void run(){
        final String output = request(URL);
        result = output; // http 요청 끝나서 결과 가져온 뒤 result 멤버 변수에 저장
    }
    public String getResult(){ // result 멤버 변수 가져올 수 있도록 함
        return result;
    }

    private String request(String urlStr){
        StringBuilder output = new StringBuilder();

        try{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                int resCode = conn.getResponseCode();
                if(resCode == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line = null;
                    while(true){
                        line = reader.readLine();
                        if(line == null){
                            break;
                        }
                        output.append(line + "\n");
                    }

                    reader.close();
                    conn.disconnect();
                }
            }
        }catch(Exception ex){
            Log.e("SampleHTTP","Exception in processing response",ex);
            ex.printStackTrace();
        }
        return output.toString();
    }
}
