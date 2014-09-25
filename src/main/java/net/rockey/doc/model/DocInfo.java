package net.rockey.doc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doc_info")
public class DocInfo implements Serializable {

	/** null. */
	private Long id;

	/** null. */
	private String name;

	/** null. */
	private String path;

	/** null. */
	private int type;

	/** null. */
	private String createTime;

	/** null. */
	private Long userId;

	/** null. */
	private String descn;

	public DocInfo() {
	}

	public DocInfo(String name, String path, int type, String createTime,
			Long userId, String descn) {
		this.name = name;
		this.path = path;
		this.type = type;
		this.createTime = createTime;
		this.userId = userId;
		this.descn = descn;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", length = 128)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "path", length = 256)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "create_time", length = 64)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "descn", length = 256)
	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

}
