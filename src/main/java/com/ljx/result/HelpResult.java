package com.ljx.result;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 当用户键入help时返回帮助信息
 */
public class HelpResult {
    private String notification;
    private String session_id;
    private String address = "/resources/";

    public HelpResult() {
        load("help.txt");
        this.session_id = "";
    }

    public HelpResult (String fineName) {
        load(fineName.toLowerCase()+".html");
    }

    /**
     * 加载具体指令help文件
     */
    private void load (String fileName) {
        try {
            InputStream inputStream = new ClassPathResource(fileName).getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String str = null;
            StringBuilder sb = new StringBuilder();
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str+"\n");
            }
            notification = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            load("error.txt");
            return;
        }
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
