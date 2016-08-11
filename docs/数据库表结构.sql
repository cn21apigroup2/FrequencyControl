（1）	用户表
userId
用户名
密码
创建日期
用户标识（启用 停用状态）

（2）	登录日志表
logID
用户ID
登录时间
登录IP
（3）	用户app表
APPID
用户ID
APP名称
APP描述
平台
删除标识
（4）	接口调用频次控制表
接口ＩＤ
APPID
接口标识符
访问频次
（是否支持借贷）
（借贷额度）（括号为可选）
（5）	访问日志表
logID
接口ＩＤ
用户标识
访问ＩＰ
访问时间
（6）	黑名单
APPID
用户唯一标识（IP/Username）
进入黑名单次数
是否生效

CREATE TABLE `example` (
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8
