package com.giousa.daily.model;

import java.util.Date;

public class QtCompanyEmploye {
    private String id;

    private String companyId;

    private String userId;

    private String levelId;

    private Date entryDt;

    private Date quitDt;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId == null ? null : levelId.trim();
    }

    public Date getEntryDt() {
        return entryDt;
    }

    public void setEntryDt(Date entryDt) {
        this.entryDt = entryDt;
    }

    public Date getQuitDt() {
        return quitDt;
    }

    public void setQuitDt(Date quitDt) {
        this.quitDt = quitDt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}