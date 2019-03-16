package com.http.bean;


import com.bean.BaseBean;

import java.util.Map;

/**
 * Created by lilfi on 2017/8/21.
 */

public class SendRootBean extends BaseBean {
    public SendRootBean() {
    }

    public SendRootBean(String action) {
        head=new SendHeadBean(action);
    }

    private SendHeadBean head;
    private Map<String,Object> body;

    public Map<String,Object> getBody() {
        return body;
    }

    public void setBody(Map<String,Object> body) {
        this.body = body;
    }

    public SendHeadBean getHead() {
        return head;
    }
    public void setHead(SendHeadBean head) {
        this.head = head;
    }

}
