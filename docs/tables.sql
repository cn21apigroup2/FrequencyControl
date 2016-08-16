DROP TABLE IF EXISTS `user`;  
CREATE TABLE `user` (  
  user_id INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  username VARCHAR(30) NOT NULL COMMENT '用户名',
  password VARCHAR(50) COMMENT '密码MD5加密',
  email VARCHAR(30) COMMENT '用户邮箱',
  regist_date TIMESTAMP COMMENT '注册日期',
  deleted TINYINT DEFAULT 0 COMMENT '0标识启用，1标识停用',
  PRIMARY KEY (`user_id`)  
) ENGINE=INNODB  DEFAULT CHARSET=utf8 COMMENT '用户信息表';

DROP TABLE IF EXISTS `application`;  
CREATE TABLE `application`(
app_id INT NOT NULL AUTO_INCREMENT COMMENT '应用唯一标识 主键',
user_id INT NOT NULL COMMENT '该应用属于哪一个用户的标识',
app_key VARCHAR(30) NOT NULL COMMENT '该应用标识 颁发给用户',
secret VARCHAR(30) DEFAULT NULL COMMENT '该应用的秘钥,颁发给用户,签证使用',
app_descrition TEXT DEFAULT NULL  COMMENT '应用描述',
platform VARCHAR(30) DEFAULT NULL COMMENT '应用的平台',
deleted TINYINT DEFAULT 0 COMMENT '0表示未删除，1表示已删除',
PRIMARY KEY(app_id),
FOREIGN KEY(user_id) REFERENCES USER(user_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '应用表';
CREATE INDEX index_app_key ON application(app_key);

DROP TABLE IF EXISTS `interface_control`;  
CREATE TABLE `interface_control`(
interface_id INT NOT NULL AUTO_INCREMENT COMMENT '唯一标识 自增主键',
app_id INT NOT NULL COMMENT '应用标识',
signature VARCHAR(50) NOT NULL COMMENT '接口标识符 需要被频次控制的方法签名包括方法名和参数',
frequency INT DEFAULT 100 COMMENT '接口调用频次',
timeout BIGINT DEFAULT 0  COMMENT '接口频次控制的时间长度 与unit配合使用',
unit ENUM('s','m','h','d') DEFAULT 's' COMMENT '时间单位 与timeout配合使用 s/秒 m/分 h/时 d/天',
can_loan TINYINT DEFAULT 0 COMMENT '是否支持借贷机制',
quota INT DEFAULT 0  COMMENT '可借贷的额度',
deleted TINYINT DEFAULT 0 COMMENT '0表示未删除，1表示已删除',
PRIMARY KEY(interface_id),
FOREIGN KEY(app_id) REFERENCES application(app_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '频次控制接口表';
CREATE INDEX index_signature ON interface_control(signature);