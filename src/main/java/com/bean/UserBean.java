package com.bean;


public class UserBean extends BaseBean {
    private String id;
    private String mobile;
    private String session_id;
    private String gender;
    private String id_card_no;
    private String birthday;
    private String age;
    private String nickname;
    private String avatar_url;
    private String is_userinfo;
    private String weight;
    private String height;
    private String is_free; //最近一次是否免费,1免费，0不免费
    private String is_bind; //是否绑定邀请码

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId_card_no() {
        return id_card_no;
    }

    public void setId_card_no(String id_card_no) {
        this.id_card_no = id_card_no;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getIs_userinfo() {
        return is_userinfo;
    }

    public void setIs_userinfo(String is_userinfo) {
        this.is_userinfo = is_userinfo;
    }

    public String getIs_free() {
        return is_free;
    }

    public void setIs_free(String is_free) {
        this.is_free = is_free;
    }

    public String getIs_bind() {
        return is_bind;
    }

    public void setIs_bind(String is_bind) {
        this.is_bind = is_bind;
    }
}

//            id
//    用户ID
//            整型
//    mobile
//            手机号
//    session_id
//            生成的登陆校验字符
//    base64加密字符串
//            gender
//性别
//        0未知，1男，2女
//        id_card_no
//        身份证号
//        没有返回空字符串
//        birthday
//        生日
//        没有返回空字符串
//        age
//        年龄
//        没有返回0
//        nickname
//        昵称
//        没有返回空字符串
//        avatar_url
//        头像URL
//        没有返回空字符串
//        is_userinfo
//        是否授权过
//        true or false