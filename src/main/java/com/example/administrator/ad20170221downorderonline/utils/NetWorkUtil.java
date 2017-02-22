package com.example.administrator.ad20170221downorderonline.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class NetWorkUtil {
    public static  String  connectWebsite(final String urlstr){
        final StringBuffer[] sb = {null};
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(urlstr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream is = conn.getInputStream();
                    sb[0] = new StringBuffer();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb[0].append(line);
                    }
                    br.close();
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return sb[0].toString();
    }
}
