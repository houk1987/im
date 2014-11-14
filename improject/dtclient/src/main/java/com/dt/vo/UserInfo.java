package com.dt.vo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id; // ���
    private String username;// ����
    private String age; // ����
    private int sex;// �Ա�0,1�� 0����Ů��1������
    private String birthday;// ��������
    private int rank;// ����
    private String workPosition;// ְλ
    private String cellPhone;// �ֻ�����
    private String workPhone;// �칫����
    private int roleId;// ��ɫ���  1(ѵ��������Ա),2(��ѵ��Ա),3(������Ա),4(������Ա)
    private String roleName; // ��ɫ����
    private int loginStatus; // �˺�״̬
    private String UnitId;// ���ű��
    private String UnitName;// ��������

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        setAge(calculateAge(getBirthday()));
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }



    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getUnitId() {
        return UnitId;
    }

    public void setUnitId(String unitId) {
        UnitId = unitId;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public String getPartmentId() {
        return UnitId;
    }

    public void setPartmentId(String partmentId) {
        this.UnitId = partmentId;
    }

    public String getPartmentName() {
        return UnitName;
    }

    public void setPartmentName(String partmentName) {
        this.UnitName = partmentName;
    }

    private String calculateAge(String birthday) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            final long day = (new Date().getTime() - myFormatter.parse(birthday).getTime())
                    / (24 * 60 * 60 * 1000) + 1;
            return new java.text.DecimalFormat("#").format(day / 365f);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
