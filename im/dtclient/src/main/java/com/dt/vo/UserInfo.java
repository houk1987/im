package com.dt.vo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id; // 编号
	private String username;// 姓名
	private String age; // 年龄
	private int sex;// 性别（0,1） 0代表女，1代表男
	private String birthday;// 出生日期
	private int rank;// 军衔
	private String workPosition;// 职位
	private String cellPhone;// 手机号码
	private String workPhone;// 办公号码
	private int roleId;// 角色编号  1(训练管理人员),2(受训人员),3(导调人员),4(评估人员)
	private String roleName; // 角色名称
	private int loginStatus; // 账号状态
	private String UnitId;// 部门编号
	private String UnitName;// 部门名称
	
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
