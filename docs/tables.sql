DROP TABLE IF EXISTS `user`;  
CREATE TABLE `user` (  
  user_id INT(11) NOT NULL AUTO_INCREMENT COMMENT '����',
  username VARCHAR(30) NOT NULL COMMENT '�û���',
  password VARCHAR(50) COMMENT '����MD5����',
  email VARCHAR(30) COMMENT '�û�����',
  regist_date TIMESTAMP COMMENT 'ע������',
  deleted TINYINT DEFAULT 0 COMMENT '0��ʶ���ã�1��ʶͣ��',
  PRIMARY KEY (`user_id`)  
) ENGINE=INNODB  DEFAULT CHARSET=utf8 COMMENT '�û���Ϣ��';

DROP TABLE IF EXISTS `application`;  
CREATE TABLE `application`(
app_id INT NOT NULL AUTO_INCREMENT COMMENT 'Ӧ��Ψһ��ʶ ����',
user_id INT NOT NULL COMMENT '��Ӧ��������һ���û��ı�ʶ',
app_key VARCHAR(30) NOT NULL COMMENT '��Ӧ�ñ�ʶ �䷢���û�',
secret VARCHAR(30) DEFAULT NULL COMMENT '��Ӧ�õ���Կ,�䷢���û�,ǩ֤ʹ��',
app_descrition TEXT DEFAULT NULL  COMMENT 'Ӧ������',
platform VARCHAR(30) DEFAULT NULL COMMENT 'Ӧ�õ�ƽ̨',
deleted TINYINT DEFAULT 0 COMMENT '0��ʾδɾ����1��ʾ��ɾ��',
PRIMARY KEY(app_id),
FOREIGN KEY(user_id) REFERENCES USER(user_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT 'Ӧ�ñ�';
CREATE INDEX index_app_key ON application(app_key);

DROP TABLE IF EXISTS `interface_control`;  
CREATE TABLE `interface_control`(
interface_id INT NOT NULL AUTO_INCREMENT COMMENT 'Ψһ��ʶ ��������',
app_id INT NOT NULL COMMENT 'Ӧ�ñ�ʶ',
signature VARCHAR(50) NOT NULL COMMENT '�ӿڱ�ʶ�� ��Ҫ��Ƶ�ο��Ƶķ���ǩ�������������Ͳ���',
frequency INT DEFAULT 100 COMMENT '�ӿڵ���Ƶ��',
timeout BIGINT DEFAULT 0  COMMENT '�ӿ�Ƶ�ο��Ƶ�ʱ�䳤�� ��unit���ʹ��',
unit ENUM('s','m','h','d') DEFAULT 's' COMMENT 'ʱ�䵥λ ��timeout���ʹ�� s/�� m/�� h/ʱ d/��',
can_loan TINYINT DEFAULT 0 COMMENT '�Ƿ�֧�ֽ������',
quota INT DEFAULT 0  COMMENT '�ɽ���Ķ��',
deleted TINYINT DEFAULT 0 COMMENT '0��ʾδɾ����1��ʾ��ɾ��',
PRIMARY KEY(interface_id),
FOREIGN KEY(app_id) REFERENCES application(app_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT 'Ƶ�ο��ƽӿڱ�';
CREATE INDEX index_signature ON interface_control(signature);