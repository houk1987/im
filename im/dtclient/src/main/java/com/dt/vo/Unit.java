package com.dt.vo;


import java.util.List;

public class Unit {
    private String unitId;
    private String unitName;
    private String parentUnitId;
    private List<UserInfo> memberList;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getParentUnitId() {
        return parentUnitId;
    }

    public void setParentUnitId(String parentUnitId) {
        this.parentUnitId = parentUnitId;
    }

    public List<UserInfo> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<UserInfo> memberList) {
        this.memberList = memberList;
    }
}
