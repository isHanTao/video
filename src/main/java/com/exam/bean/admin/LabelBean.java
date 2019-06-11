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
public class LabelBean {
    private Integer labelId;
    private Integer classify;
    private String label;
    private Integer status;

    @Override
    public String toString() {
        return "LabelBean{" +
                "labelId=" + labelId +
                ", classify=" + classify +
                ", label='" + label + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
