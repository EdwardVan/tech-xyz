## ms-spring-cloud介绍

## 环境搭建

### 开发环境

工具|版本号|下载
---|---|---
JDK | 11 | https://www.oracle.com/cn/java/technologies/oracle-java-archive-downloads.html
Mysql | 5.7.28 | https://downloads.mysql.com/archives/community/
nacos | 1.2.1 | https://github.com/alibaba/nacos/releases
Sentinel | 1.7.2 | https://github.com/alibaba/Sentinel/releases
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
 - 打开控制台新建配置,Data ID为: user-service-dev.yml  product-service-dev.yml  gateway-service-dev.yml
```yaml
spring:
  cloud:
    #sentinel配置
    sentinel:
      transport:
        dashboard: localhost:8080
        #默认端口,如果被占用则从8719依次+1扫描
        port: 8719
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
```