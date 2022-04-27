package com.ljx.result;

/**
 * 返回redis指令交互信息
 */
public class ResponseResult {
    //{"response":"OK","session_id":"d364abf29a7ea548ec29011ad78f985f91c13539f97acf56af86792798cc8e85"}
    private String response;
    private String session_id;

    public ResponseResult() {
        this.response = "OK";
        this.session_id = "fuck";
    }
    public static ResponseResult ok () {
        return new ResponseResult();
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
