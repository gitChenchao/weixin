package com.cc.weixin.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpJsonUtil {
	/**
     * @param url
     * @param Params
     * @return
     * @throws IOException
     */
    public static String sendPost(String url,String Params)throws IOException{
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        String response="";
        try {
            URL httpUrl = null; //HTTP URL�� �����������������
            httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setUseCaches(false);//���ò�Ҫ����
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            out = new OutputStreamWriter(
                    conn.getOutputStream());
            out.write(Params);
            out.flush();
            reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response+=lines;
            }
            reader.close();
            conn.disconnect();
        } catch (Exception e) {
        e.printStackTrace();
        }
        finally{
        try{
            if(out!=null){
                out.close();
            }
            if(reader!=null){
                reader.close();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

        return response;
    }
}
