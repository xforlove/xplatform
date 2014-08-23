package net.rockey.auth.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "auth_user")
public class AuthUser {

	private Long id;
	private String name;
	private String loginId;
	private String loginPass;
	private String phone;
	private String email;
	private int passErrorCnt;
	private String passUpdateTime;
	private String statFlag;
	private Long creator;
	private String createTime;
	private Long updator;
	private String updateTime;

	private List<AuthRole> roles = new ArrayList<AuthRole>(0);

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "login_id", nullable = false, length = 50)
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Column(name = "login_pass", nullable = false, length = 64)
	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	@Column(name = "phone", nullable = false, length = 50)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", nullable = false, length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "pass_error_cnt", nullable = false, length = 2)
	public int getPassErrorCnt() {
		return passErrorCnt;
	}

	public void setPassErrorCnt(int passErrorCnt) {
		this.passErrorCnt = passErrorCnt;
	}

	@Column(name = "pass_update_time", length = 14)
	public String getPassUpdateTime() {
		return passUpdateTime;
	}

	public void setPassUpdateTime(String passUpdateTime) {
		this.passUpdateTime = passUpdateTime;
	}

	@Column(name = "stat_flag", nullable = false, length = 50)
	public String getStatFlag() {
		return statFlag;
	}

	public void setStatFlag(String statFlag) {
		this.statFlag = statFlag;
	}

	@ManyToMany
	@JoinTable(name = "auth_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	public List<AuthRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AuthRole> roles) {
		this.roles = roles;
	}

	@Column(name = "creator")
	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	@Column(name = "create_time", length = 14)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "updator")
	public Long getUpdator() {
		return updator;
	}

	public void setUpdator(Long updator) {
		this.updator = updator;
	}

	@Column(name = "update_time", length = 14)
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
