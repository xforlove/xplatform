package net.rockey.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.rockey.auth.model.AuthRole;

@Entity
@Table(name = "app_function")
public class AppFunction {

	private Long id;
	private String code;
	private String name;
	private int level;
	private String action;
	private AppFuncGroup group;
	private AppFunction parent;
	private String descn;
	private String statFlag;
	private Long creator;
	private String createTime;
	private Long updator;
	private String updateTime;

	private List<AppFunction> children = new ArrayList<AppFunction>(0);
	private List<AuthRole> roles = new ArrayList<AuthRole>(0);

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "code", nullable = false, length = 100)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "action", nullable = true, length = 200)
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	public AppFuncGroup getGroup() {
		return group;
	}

	public void setGroup(AppFuncGroup group) {
		this.group = group;
	}

	@Column(name = "descn", nullable = true, length = 200)
	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	@Column(name = "stat_flag", nullable = false, length = 50)
	public String getStatFlag() {
		return statFlag;
	}

	public void setStatFlag(String statFlag) {
		this.statFlag = statFlag;
	}

	@Column(name = "level", nullable = false, length = 2)
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id")
	public AppFunction getParent() {
		return parent;
	}

	public void setParent(AppFunction parent) {
		this.parent = parent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
	public List<AppFunction> getChildren() {
		return children;
	}

	public void setChildren(List<AppFunction> children) {
		this.children = children;
	}

	@ManyToMany(mappedBy = "functions")
	public List<AuthRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AuthRole> roles) {
		this.roles = roles;
	}

	@Column(name = "creator", length = 19)
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

	@Column(name = "updator", length = 19)
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
