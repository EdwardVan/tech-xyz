## ms-spring-cloud介绍

## 环境搭建

### 开发环境

工具|版本号|下载
---|---|---
JDK | 11 | https://www.oracle.com/cn/java/technologies/oracle-java-archive-downloads.html
Mysql | 5.7.28 | https://downloads.mysql.com/archives/community/
nacos | 1.2.1 | https://github.com/alibaba/nacos/releases
Sentinel | 1.7.2 | https://github.com/alibaba/Sentinel/releases
Seata | 1.1.0 | https://github.com/seata/seata/releases
zipkin | 2.12.9 | https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server

### 搭建步骤
> mysql安装
 - 解压压缩包到 D:\Program
 - 在 D:\Program\mysql-5.7.28-winx64 目录下新建 data 文件夹
 - 在 D:\Program\mysql-5.7.28-winx64 目录下新建 my.ini 文件(需要windows编码),内容如下
```
[mysqld]
# 设置服务端使用的字符集为utf-8
character-set-server=utf8mb4
# 绑定IPv4地址
bind-address = 0.0.0.0
# 设置mysql的端口号
port = 3306
# 设置mysql的安装目录(能看到bin即可)
basedir=D:\Program\mysql-5.7.28-winx64
# 设置mysql数据库的数据的存放目录(能看到my.ini文件的目录)
datadir=D:\Program\mysql-5.7.28-winx64\data
# 允许最大连接数
max_connections=2000
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
# 设置mysql以及数据库的默认编码
[mysql]
default-character-set=utf8mb4
[mysql.server]
default-character-set=utf8mb4
# 设置客户端默认字符集
[client]
default-character-set=utf8mb4
```
 - 在cmd中进入D:\Program\mysql-5.7.28-winx64\bin 目录执行 mysqld --install 和 mysqld --initialize --user=root --console
 - 记录最后一行的随机密码,执行 net start mysql 启动mysql,执行 mysql -uroot -p 登录mysql
 - 设置密码执行: set password = password('123456');
>创建库表
 - 用户库(demo_mall_user)
```sql
CREATE TABLE `demo_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码,MD5加密',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `question` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '找回密码答案',
  `role` int(4) NOT NULL COMMENT '角色0-管理员,1-普通用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   `del_flag` int(1) default 0 COMMENT '逻辑删除字段,0=未删除,1=删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name_unique`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
INSERT INTO `demo_user` VALUES (1, 'admin', '427338237BD929443EC5D48E24FD2B1A', 'admin@happymmall.com', '13800138000', '问题', '答案', 1, '2016-11-06 16:56:45', '2017-04-04 19:27:36',0);
```
    
 - 产品库(demo_mall_product)
```sql
CREATE TABLE `demo_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `category_id` int(11) NOT NULL COMMENT '分类id,对应demo_category表的主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `subtitle` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品副标题',
  `main_image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品主图,url相对地址',
  `sub_images` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '图片地址,json格式,扩展用',
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '商品详情',
  `price` decimal(20, 2) NOT NULL COMMENT '价格,单位-元保留两位小数',
  `stock` int(11) NOT NULL COMMENT '库存数量',
  `status` int(6) DEFAULT 1 COMMENT '商品状态.1-在售 2-下架 3-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   `del_flag` int(1) default 0 COMMENT '逻辑删除字段,0=未删除,1=删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
INSERT INTO `demo_product` VALUES (26, 100002, 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', 'iPhone 7,现更以红色呈现.', 'test', 'test', 'test', 6999.00, 9991, 1, NULL, '2017-04-13 21:45:41',0);
INSERT INTO `demo_product` VALUES (27, 100006, 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', '送品牌烤箱,五一大促', 'test', 'test', 'test', 3299.00, 8876, 1, '2017-04-13 18:51:54', '2017-04-13 21:45:41',0);
INSERT INTO `demo_product` VALUES (28, 100012, '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', 'NOVA青春版1999元', 'test', 'test', 'test', 1999.00, 9994, 1, '2017-04-13 18:57:18', '2017-04-13 21:45:41',0);
INSERT INTO `demo_product` VALUES (29, 100008, 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '门店机型 德邦送货', 'test', 'test', 'test', 4299.00, 9993, 1, '2017-04-13 19:07:47', '2017-04-13 21:45:41',0);
```
> jar运行命令
```
title xxx 
java -jar xxx.jar
```
> nacos配置
 - 数据库中创建数据库nacos
 - 执行nacos-server-1.2.1\conf\nacos-mysql.sql
 - 修改nacos-server-1.2.1\conf\application.properties中和数据库有关的配置
 - jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
 - 打开控制台新建配置,Data ID为: user-service-dev.yml  product-service-dev.yml
```yaml
spring:
  cloud:
    #sentinel配置
    sentinel:
      transport:
        dashboard: localhost:8080
      #sentinel配置持久化
      datasource:
        dsl:
          nacos:
            server-addr: 127.0.0.1:8848
            dataId: ${spring.application.name}-sentinel
            groupId: DEFAULT_GROUP
            data_type: json
            rule-type: flow
  #指定zipkin服务器地址  
  zipkin:
    base-url: http://localhost:9411/
  #设置采样比例为1.0即全部都需要
  sleuth:
    sampler:
      probability: 1.0
#开启 sentinel对feign的支持
feign:
  sentinel:
    enabled: true

#mybatis-plus配置
mybatis-plus:
  mapper-locations: classpath*:mappers/*.xml
  type-aliases-package: tech.edwardvan.*.model
```
 - 新建Sentinel流控规则 user-service-sentinel
```json
[
    {
        "resource": "getUser",
        "limitApp": "default",
        "grade": 1,
        "count": 5,
        "strategy": 0,
        "controlBehavior": 0,
        "clusterMode": false
    }
]
```
> Seata配置
 - 在数据库中创建seata库, https://github.com/seata/seata/blob/1.1.0/script/server/db/mysql.sql ,运行sql
 - 每个业务库下增加一张表,https://github.com/seata/seata/blob/1.1.0/script/client/at/db/mysql.sql,执行sql
 - 修改 seata\conf\registry.conf 中 registry.type 和 config.type 为 nacos, 修改nacos.serverAddr 为 127.0.0.1:8848
 - 执行 SeataInit 工具类创建配置规则
 