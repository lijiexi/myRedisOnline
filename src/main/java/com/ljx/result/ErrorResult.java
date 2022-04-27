package com.ljx.result;

import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * 当用户键入错误指令时返回帮助信息
 */
public class ErrorResult {
    private String error;
    private String session_id;

    public ErrorResult() {
        this.error = "";
        this.session_id = "";
        load();
    }
    public static ErrorResult ok () {

        return new ErrorResult();
    }

    private void load () {
        String encoding = "UTF-8";
        ClassPathResource classPathResource = new ClassPathResource("error.txt");
        byte[] fileContent = new byte[4029];
        try {
            InputStream inputStream = classPathResource.getInputStream();
            inputStream.read(fileContent);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            error = new String(fileContent,encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
