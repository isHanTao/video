package com.exam.bean.admin;

import java.sql.Timestamp;

/**
 * @Project: video
 * @Package: com.exam.bean.admin
 * @Author: 韩涛
 * @Date: 2018-10-11 10:53
 * @Description:
 * @Param:
 **/
public class LogBean {
    private Integer logId;
    private Timestamp createTime;
    private String logContent;
    private Integer status;

    @Override
    public String toString() {
        return "LogBean{" +
                "logId=" + logId +
                ", createTime=" + createTime +
                ", logContent='" + logContent + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
