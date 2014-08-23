package net.rockey.auth.support;

public class AuthUserDTO {

	private Long id;
	private String name;
	private String loginId;
	private String loginPass;
	private String phone;
	private String email;
	private int passErrorCnt;
	private String passUpdateTime;
	private String statFlag;
	private String statFlagCn;
	private Long creator;
	private String createTime;
	private Long updator;
	private String updateTime;

	/** string-based. */
	private String roleCodes;
	private String roleNames;

	/** string-based. */
	private String permissionCodes;
	private String permissionNames;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPassErrorCnt() {
		return passErrorCnt;
	}

	public void setPassErrorCnt(int passErrorCnt) {
		this.passErrorCnt = passErrorCnt;
	}

	public String getPassUpdateTime() {
		return passUpdateTime;
	}

	public void setPassUpdateTime(String passUpdateTime) {
		this.passUpdateTime = passUpdateTime;
	}

	public String getStatFlag() {
		return statFlag;
	}

	public void setStatFlag(String statFlag) {
		this.statFlag = statFlag;
	}

	public String getStatFlagCn() {
		return statFlagCn;
	}

	public void setStatFlagCn(String statFlagCn) {
		this.statFlagCn = statFlagCn;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getUpdator() {
		return updator;
	}

	public void setUpdator(Long updator) {
		this.updator = updator;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(String roleCodes) {
		this.roleCodes = roleCodes;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getPermissionCodes() {
		return permissionCodes;
	}

	public void setPermissionCodes(String permissionCodes) {
		this.permissionCodes = permissionCodes;
	}

	public String getPermissionNames() {
		return permissionNames;
	}

	public void setPermissionNames(String permissionNames) {
		this.permissionNames = permissionNames;
	}

}
