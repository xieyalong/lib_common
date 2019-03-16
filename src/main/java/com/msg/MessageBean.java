package com.msg;

import java.util.List;
import java.util.Map;

/**
 * @author xyl
 * @date 2018/05/09
 * @describe
 */
public class MessageBean {
  /** 事件的唯一标示
   * 规范：activity_add  activity:接收类名称，add：功能名称，如果是接口要和接口名称一致
   * */
  private String key;

  public int what;
  public int what1;
  public int what2;
  public String str;
  public String str1;
  public String str2;
  public Object obj;
  public  boolean bool;
  public List<String> listString;
  public Map<String, String> mapStirng;
  public List<Object> listObject;
  public Map<String, Object> mapObject;

  private MessageBean(String key) {
    this.key=key;
  }
  public static MessageBean obtain(String key) {
    return new MessageBean(key);
  }

  private MessageBean(String key, Object obj) {
    this.key=key;
    this.obj=obj;
  }

  public static MessageBean obtain(String key, Object obj) {
    return new MessageBean(key,obj);
  }
}
