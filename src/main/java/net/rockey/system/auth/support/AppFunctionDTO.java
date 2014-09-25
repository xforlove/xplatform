package net.rockey.system.auth.support;

import net.rockey.system.auth.model.AppFuncGroup;
import net.rockey.system.auth.model.AppFunction;

public class AppFunctionDTO {

	private Long id;

	/** 功能代号 */
	private String code;

	/** 功能名称 */
	private String name;

	/** 级别 */
	private int level;

	/** 动作 */
	private String action;

	/** 所属功能组 */
	private AppFuncGroup group;

	private Long groupId;

	/** 上级节点 */
	private AppFunction parent;

	private Long parentId;

	/** 状态标志 */
	private String statFlag;

	/** 描述 */
	private String descn;

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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
