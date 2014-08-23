package net.rockey.app.support;

import net.rockey.app.model.AppFuncGroup;
import net.rockey.app.model.AppFunction;

public class AppFunctionDTO {

	private Long id;
	private String code;
	private String name;
	private int level;
	private String action;
	private boolean isMenu;
	private Long groupId;
	private AppFuncGroup group;
	private AppFunction parent;
	private String descn;
	private String statFlag;
	private String statFlagCn;
	private Long creator;
	private String createTime;
	private Long updator;
	private String updateTime;
	private boolean selected;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isMenu() {
		return isMenu;
	}

	public void setMenu(boolean isMenu) {
		this.isMenu = isMenu;
	}

	public AppFuncGroup getGroup() {
		return group;
	}

	public void setGroup(AppFuncGroup group) {
		this.group = group;
	}

	public AppFunction getParent() {
		return parent;
	}

	public void setParent(AppFunction parent) {
		this.parent = parent;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

}
