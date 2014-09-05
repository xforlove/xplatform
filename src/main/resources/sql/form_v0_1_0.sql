

INSERT INTO form_template(id, code, descn, name, url) VALUES
	(1, 'leave:apply', '', '�������', '/leave/apply.do'),

INSERT INTO form_template(id, code, descn, name, url) VALUES
	(2, 'leave:leaderAudit', '', '�ϼ�����', '/leave/leaderAudit.do'),
	
INSERT INTO form_template(id, code, descn, name, url) VALUES
	(3, 'leave:modifyApply', '', '��������', '/leave/modifyApply.do'),
	
INSERT INTO form_template(id, code, descn, name, url) VALUES
	(4,	'leave:hrRecord', '',	'���¹鵵', '/leave/hrRecord.do');

/*
DROP TABLE KV_PROP;
DROP TABLE KV_RECORD;
*/

CREATE TABLE KV_RECORD(
	ID BIGINT auto_increment,
	CATEGORY VARCHAR(200),
	STATUS VARCHAR(64),
	REF VARCHAR(200),
	CREATOR VARCHAR(64),
	CREATE_TIME VARCHAR(64),
	CONSTRAINT PK_KV_RECORD PRIMARY KEY(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE KV_PROP(
        ID BIGINT auto_increment,
	CODE VARCHAR(200),
	TYPE INT,
	VALUE VARCHAR(200),
	RECORD_ID BIGINT,
	CONSTRAINT PK_KV_PROP PRIMARY KEY(ID),
	CONSTRAINT FK_KV_PROP_RECORD FOREIGN KEY(RECORD_ID) REFERENCES KV_RECORD(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;