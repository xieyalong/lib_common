package com.http;


import android.content.DialogInterface;
import android.view.View;

import com.CAPP;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.dialog.CDialogAlert;
import com.http.bean.SendHeadBean;
import com.util.CUDES;
import com.util.CUSP;
import com.util.CUToast;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


public abstract class HttpCallBack extends Callback<String> implements HttpCallBackI {
    public View submit_view;//是否允许
    private boolean isEncryption = true;//加密

    public boolean getIsEncryption() {
        if (CAPP.getCommonAPP().isDebug()) {
            isEncryption = false;
        } else {
            isEncryption = true;
        }

        return isEncryption;
    }

    //抽取这个方法，是为了在有情况的时候统一判断。
    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        onStart();
    }

    @Override
    public void onAfter(int id) {
        super.onAfter(id);
        onEnd();
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        try {
            String fail = "exception=" + e.toString() + "id=" + id + "call.request=" + call.request().toString();
            LogUtils.i(">] onFailure:服务器异常请稍后再试=" + e.toString());
            CUToast.getInstance().showErrorToast("请求异常");
            onFailure(fail);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onResponse(String response, int id) {
        try {
            //1=token失效和密码修改
            //0=业务逻辑成功操作

            onSuccess(response);
            JSONObject root = JSONObject.parseObject(response).getJSONObject("root");
            LogUtils.i(">]数据=" + root.toJSONString());
            SendHeadBean headBean = JSONObject.parseObject(root.getString("head"), SendHeadBean.class);
            if (1000 == headBean.getCode()) {
                try {
                    CDialogAlert.getInstance().showAlert(headBean.getText(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CUSP.getInstance().setUserNull();
//                CUMsg.sendMsg(CC.logout);
            } else {
                if (null == root.getJSONObject("body")) {
                    onSuccessJson(null);
                    onSuccessJson(headBean, null);
                    return;
                }
                String data_str = root.getJSONObject("body").getString("data");
                if ("[]".equals(data_str) || "{}".equals(data_str) || null == data_str) {
                    onSuccessJson(null);
                    onSuccessJson(headBean, null);
                } else {
                    onSuccessJson(root.getJSONObject("body").getJSONObject("data"));
                    onSuccessJson(headBean, root.getJSONObject("body").getJSONObject("data"));
                }
            }

        } catch (Exception e) {
//            CUToast.getInstance().showErrorToast("onSuccess:"+e.getMessage());
            CUToast.getInstance().showErrorToast("数据错误");
            LogUtils.i(">] onSuccess:" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void inProgress(float progress, long total, int id) {
        super.inProgress(progress, total, id);
        inProgress(progress, total, id);
    }

    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        String decrypt = null;
        try {
            String string = response.body().string();
            string = new String(string.getBytes(), "UTF-8");
            decrypt = "";
            if (getIsEncryption()) {
                decrypt = CUDES.decrypt(string);
            } else {
                decrypt = string;
            }

//            decrypt=info;//java.net.URLEncoder.encode(decrypt, "UTF-8");//new String(decrypt.getBytes("UTF-8"),"iso-8859-1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decrypt;

    }

    /**
     * 在httpStart之前
     */
    public void setCallbackData() {

    }

    /**
     * 在onStart之前 设置请求前的参数
     */
    @Override
    public void httpStart() {
        if (submit_view != null) {
            submit_view.setClickable(false);
            submit_view.setEnabled(false);
        }
        if (null != submit_view) {
            LogUtils.i(">]httpView=" + submit_view.isClickable());
        }
    }

    /**
     * 请求开始
     */
    @Override
    public void onStart() {
        if (submit_view != null) {
            submit_view.setClickable(false);
            submit_view.setEnabled(false);
        }
        if (null != submit_view) {
            LogUtils.i(">]httpView=" + submit_view.isClickable());
        }
    }

    /**
     * 请求结束
     */
    @Override
    public void onEnd() {
        if (submit_view != null) {
            submit_view.setClickable(true);
            submit_view.setEnabled(true);
        }
        if (null != submit_view) {
            LogUtils.i(">]httpView=" + submit_view.isClickable());
            submit_view = null;
        }
    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onFailure(String msg) {
        LogUtils.i(">] onFailure=" + msg);
    }

    @Override
    public void onSuccessJson(JSONObject rootJson) {

    }

    @Override
    public void onSuccessJson(SendHeadBean head, JSONObject bodyJson) {

    }
}
