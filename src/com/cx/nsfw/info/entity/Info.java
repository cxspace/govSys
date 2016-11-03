package com.cx.nsfw.info.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxspace on 16-10-27.
 */
public class Info implements Serializable{

    private String infoId;

    private String type;

    private String source;

    private String title;

    private String content;

    private String memo;

    private String creator;

    private Timestamp createTime;

    private String state;

    //消息的状态
    public static String INFO_STATE_PUBLIC = "1";

    public static String INFO_STATE_STOP = "0";

    //信息分类
    public static String INFO_TYPE_TZGG = "tzgg";

    public static String INFO_TYPE_ZCSD = "zcsd";

    public static String INFO_TYPE_NSZD = "nszd";

    public static Map<String,String> INFO_TYPE_MAP;

    static {

        INFO_TYPE_MAP = new HashMap<String, String>();

        INFO_TYPE_MAP.put(INFO_TYPE_TZGG,"通知公告");

        INFO_TYPE_MAP.put(INFO_TYPE_ZCSD,"政策速递");

        INFO_TYPE_MAP.put(INFO_TYPE_NSZD,"纳税指导");

    }

    public Info() {
    }

    public Info(String content, String creator, Timestamp createTime, String infoId, String memo, String source, String state, String title, String type) {
        this.content = content;
        this.creator = creator;
        this.createTime = createTime;
        this.infoId = infoId;
        this.memo = memo;
        this.source = source;
        this.state = state;
        this.title = title;
        this.type = type;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
