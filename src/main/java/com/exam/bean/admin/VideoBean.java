package com.exam.bean.admin;

import java.sql.Timestamp;

/**
 * @Project: video
 * @Package: com.exam.bean.admin
 * @Author: 韩涛
 * @Date: 2018-10-11 10:52
 * @Description:
 * @Param:
 **/
public class VideoBean {
    private Integer videoId;
    private String videoName;
    private String videoDescribe;
    private Integer grade;
    private Timestamp createTime;
    private Integer labelId;
    private Integer playAmount;
    private Integer status;

    @Override
    public String toString() {
        return "VideoBean{" +
                "videoId=" + videoId +
                ", videoName='" + videoName + '\'' +
                ", videoDescribe='" + videoDescribe + '\'' +
                ", grade=" + grade +
                ", createTime=" + createTime +
                ", labelId=" + labelId +
                ", playAmount=" + playAmount +
                ", status=" + status +
                '}';
    }

    public Integer getPlayAmount() {
        return playAmount;
    }

    public void setPlayAmount(Integer playAmount) {
        this.playAmount = playAmount;
    }
    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoDescribe() {
        return videoDescribe;
    }

    public void setVideoDescribe(String videoDescribe) {
        this.videoDescribe = videoDescribe;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
