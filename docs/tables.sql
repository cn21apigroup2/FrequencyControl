DROP TABLE IF EXISTS `user`;  
CREATE TABLE `user` (  
  user_id INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  username VARCHAR(30) NOT NULL COMMENT '用户名',
  PASSWORD VARCHAR(50) COMMENT '密码MD5加密',
  email VARCHAR(30) COMMENT '用户邮箱',
  register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期',
  deleted TINYINT DEFAULT 0 COMMENT '0标识启用，1标识停用',
  PRIMARY KEY (`user_id`)  
) ENGINE=INNODB  DEFAULT CHARSET=utf8 COMMENT '用户信息表';
CREATE INDEX index_username ON USER(username);
COMMIT;

DROP TABLE IF EXISTS `application`;  
CREATE TABLE `application`(
app_id INT NOT NULL AUTO_INCREMENT COMMENT '应用唯一标识 主键',
user_id INT NOT NULL COMMENT '该应用属于哪一个用户的标识',
app_name VARCHAR(30) NOT NULL COMMENT '应用名称',
app_key VARCHAR(30) NOT NULL COMMENT '该应用标识 颁发给用户',
secret VARCHAR(30) DEFAULT NULL COMMENT '该应用的秘钥,颁发给用户,签证使用',
app_description TEXT DEFAULT NULL  COMMENT '应用描述',
platform VARCHAR(30) DEFAULT NULL COMMENT '应用的平台',
create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间日期',
is_reviewed ENUM('0','1','2') DEFAULT '0' COMMENT '审核状态 0表示未审核，1表示已审核通过，2标识审核未通过',
deleted TINYINT DEFAULT 0 COMMENT '0表示未删除，1表示已删除',
PRIMARY KEY(app_id),
FOREIGN KEY(user_id) REFERENCES USER(user_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '应用表';
CREATE INDEX index_app_key ON application(app_key);
COMMIT;

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
COMMIT;

DROP TABLE IF EXISTS blacklist;
CREATE TABLE blacklist (
  blacklist_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '黑名单ID',
  app_key CHAR(36) NOT NULL COMMENT 'APP的唯一标识',
  customer_id VARCHAR(30) NOT NULL COMMENT '每个APP下的用户名',
  limited_ip BINARY(16) NOT NULL COMMENT '被禁IP地址',
  times TINYINT NOT NULL COMMENT '被加入黑名单次数',
  fir_date DATETIME COMMENT '第一次加入黑民单时间',
  sec_date DATETIME COMMENT '第二次加入黑名单时间',
  thr_date DATETIME COMMENT '第三次加入黑名单时间',
  absoulte_date DATETIME COMMENT  '完全冻结时间'
)ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT '黑名单信息记录表' ;

CREATE INDEX APP_KEY_INDEX ON blacklist(app_key);
CREATE INDEX CUSTOMER_ID_INDEX ON blacklist(customer_id);
CREATE INDEX LIMITED_IP_INDEX ON blacklist(limited_ip);
COMMIT;