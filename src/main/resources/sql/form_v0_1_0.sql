

INSERT INTO form_template(id, code, descn, name, url) VALUES
	(1, 'leave:apply', '', '�������', '/leave/apply.do'),

INSERT INTO form_template(id, code, descn, name, url) VALUES
	(2, 'leave:leaderAudit', '', '�ϼ�����', '/leave/leaderAudit.do'),
	
INSERT INTO form_template(id, code, descn, name, url) VALUES	
	(3, 'leave:modifyApply', '', '��������', '/leave/modifyApply.do');
	
	
-------------------------------------------------------------------------------
--  keyvalue record
-------------------------------------------------------------------------------
CREATE TABLE KV_RECORD(
	ID BIGINT auto_increment,
	CATEGORY VARCHAR(200),
	STATUS INT,
	REF VARCHAR(200),
	CREATOR VARCHAR(64),
	CREATE_TIME VARCHAR(64),
	CONSTRAINT PK_KV_RECORD PRIMARY KEY(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-------------------------------------------------------------------------------
--  keyvalue property
-------------------------------------------------------------------------------
CREATE TABLE KV_PROP(
        ID BIGINT auto_increment,
	CODE VARCHAR(200),
	TYPE INT,
	VALUE VARCHAR(200),
	RECORD_ID BIGINT,
	CONSTRAINT PK_KV_PROP PRIMARY KEY(ID),
	CONSTRAINT FK_KV_PROP_RECORD FOREIGN KEY(RECORD_ID) REFERENCES KV_RECORD(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;