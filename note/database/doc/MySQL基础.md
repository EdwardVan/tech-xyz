## 数据库相关概念
1. DB:数据库,保存一组有组织的数据的容器
2. DBMS:数据库管理系统,又称为数据库软件(产品),用于管理DB中的数据
3. SQL:结构化查询语言,用于和DBMS通信的语言

## 数据库存储数据的特点
1. 将数据放到表中,表再放到库中
2. 一个数据库中可以有多个表,每个表都有一个的名字,用来标识自己.表名具有唯一性.
3. 表具有一些特性,这些特性定义了数据在表中如何存储,类似java中 "类"的设计.
4. 表由列组成,我们也称为字段.所有表都是由一个或多个列组成的,每一列类似java 中的"属性"
5. 表中的数据是按行存储的,每一行类似于java中的"对象".

## SQL的语言分类
1. DQL(Data Query Language):数据查询语言 select 
2. DML(Data Manipulate Language):数据操作语言 insert 、update、delete
3. DDL(Data Define Languge):数据定义语言 create、drop、alter
4. TCL(Transaction Control Language):事务控制语言 commit、rollback

## DQL语言的学习
### 进阶1:基础查询
1. 查询表中的单个字段
    >SELECT last_name FROM employees;
2. 查询表中的多个字段
    >SELECT last_name,salary,email FROM employees;
3. 查询表中的所有字段
    >SELECT * FROM employees;
4. 查询常量值
    >SELECT 100;
    >SELECT 'john';
5. 查询表达式
    >SELECT 100%98;
6. 查询函数
    >SELECT VERSION();
    >SELECT IFNULL(manager_id,'999');
7. 起别名
	1. 使用as
        >SELECT 100%98 AS 结果;
        >SELECT last_name AS 姓,first_name AS 名 FROM employees;
	2. 使用空格
	    >SELECT last_name 姓,first_name 名 FROM employees;
	3. 案例:查询salary,显示结果为 out put
	    >SELECT salary AS "out put" FROM employees;
8. 去重
    >查询员工表中涉及到的所有的部门编号
    >SELECT DISTINCT department_id FROM employees;
9. +号的作用(运算符)
	1. select 100+90; 两个操作数都为数值型,则做加法运算
	2. select '123'+90;只要其中一方为字符型,试图将字符型数值转换成数值型,如果转换成功,则继续做加法运算
	3. select 'john'+90;	如果转换失败,则将字符型数值转换成0
	4. select null+10; 只要其中一方为null,则结果肯定为null
10. 案例:查询员工名和姓连接成一个字段,并显示为 姓名
    >SELECT CONCAT(last_name,first_name) AS 姓名 FROM employees;

### 进阶2:条件查询
1. 分类
	- 条件运算符
	    > < > = != <> >= <=
	- 逻辑运算符(用于连接条件表达式)
	    > && || ! 
	    > and or not
	- 模糊查询
	    >like & between and & in & is null
2. 按条件表达式筛选
	- 案例:查询工资>12000的员工信息
	>SELECT * FROM employees WHERE salary>12000;
	- 查询部门编号不等于90号的员工名和部门编号
	>SELECT last_name,department_id FROM employees WHERE department_id<>90;
3. 按逻辑表达式筛选
	- 查询工资在10000到20000之间的员工名、工资以及奖金
	>SELECT last_name, salary, commission_pct FROM employees WHERE salary >= 10000 AND salary <= 20000;
	- 查询部门编号不是在90到110之间,或者工资高于15000的员工信息
	>SELECT * FROM employees WHERE NOT(department_id>=90 AND department_id<=110) OR salary>15000;
4. 模糊查询
	1. like
		- 查询员工名中包含字符a的员工信息
		>select * from employees where last_name like '%a%';
		- 查询员工名中第三个字符为e,第五个字符为a的员工名和工资
		>select last_name, salary FROM employees WHERE last_name LIKE '__n_a%';
		- 查询员工名中第二个字符为_的员工名
		>SELECT last_name FROM employees WHERE last_name LIKE '_\_%';
	2. between and(替换and)
		- 查询员工编号在100到120之间的员工信息
		>SELECT * FROM employees WHERE employee_id BETWEEN 100 AND 120;
	3. in(替换or)
		- 查询员工的工种编号是 IT_PROG、AD_VP、AD_PRES中的一个员工名和工种编号
		>SELECT last_name, job_id FROM employees WHERE job_id IN( 'IT_PROT' ,'AD_VP','AD_PRES');
	4. is null & is not null(=不能判断null值)
		- 查询没有奖金的员工名和奖金率
		>SELECT last_name, commission_pct FROM employees WHERE commission_pct IS NULL;
		- 查询有奖金的员工名和奖金率
		>SELECT last_name, commission_pct FROM employees WHERE commission_pct IS NOT NULL;

### 进阶3:排序查询	
1. 按单个字段排序
    >SELECT * FROM employees ORDER BY salary DESC;
2. 添加筛选条件再排序
	- 查询部门编号>=90的员工信息,并按员工编号降序
	>SELECT * FROM employees WHERE department_id>=90 ORDER BY employee_id DESC;
3. 按表达式排序
	- 查询员工信息 按年薪降序
	>SELECT *,salary*12*(1+IFNULL(commission_pct,0)) FROM employees ORDER BY salary*12*(1+IFNULL(commission_pct,0)) DESC;
4. 按别名排序
	- 查询员工信息 按年薪升序
	>SELECT *,salary*12*(1+IFNULL(commission_pct,0)) 年薪 FROM employees ORDER BY 年薪 ASC;
5. 按函数排序
	- 查询员工名,并且按名字的长度降序
	>SELECT LENGTH(last_name),last_name FROM employees ORDER BY LENGTH(last_name) DESC;
6. 按多个字段排序
	- 查询员工信息,要求先按工资降序,再按employee_id升序
	>SELECT * FROM employees ORDER BY salary DESC,employee_id ASC;

### 进阶4:常见函数
1. 单行函数
	1. 字符函数
		- length 获取参数值的字节个数
		>SELECT LENGTH('john');
		>SELECT LENGTH('张三丰john');
		>SHOW VARIABLES LIKE '%char%' 查看系统字符集
		- concat 拼接字符串
		>SELECT CONCAT(last_name,'_',first_name) 姓名 FROM employees;
		- upper、lower 大小写转换
		>SELECT CONCAT(UPPER(last_name),LOWER(first_name))  姓名 FROM employees;
		- substr、substring 截取字符串(注意：索引从1开始)
			- 截取从指定索引处后面所有字符
			>SELECT SUBSTR('李莫愁爱上了陆展元',7)  out_put; 
			- 截取从指定索引处指定字符长度的字符
			>SELECT SUBSTR('李莫愁爱上了陆展元',1,3) out_put;
			- 姓名中首字符大写,其他字符小写然后用_拼接,显示出来
			>SELECT CONCAT(UPPER(SUBSTR(last_name,1,1)),'_',LOWER(SUBSTR(last_name,2)))  out_put FROM employees;
		- instr 返回子串第一次出现的索引,如果找不到返回0
		>SELECT INSTR('杨不殷六侠悔爱上了殷六侠','殷六侠') AS out_put;
		- trim 去除字符
		>SELECT LENGTH('    张翠山    ') AS out_put;
		>SELECT LENGTH(TRIM('    张翠山    ')) AS out_put;
		>SELECT TRIM('a' FROM 'aa张a翠a山aa')  AS out_put;
		- replace 替换
		>SELECT REPLACE('张无忌爱上了周芷若','周芷若','赵敏') AS out_put;
		- lpad rpad 左填充 右填充
		>SELECT LPAD('赵敏',5,'-') AS out_put;
	2. 数学函数
		- round 四舍五入
		>SELECT ROUND(-1.55);
		>SELECT ROUND(1.567,2);
		- ceil 向上取整,返回>=该参数的最小整数
		>SELECT CEIL(-1.02);
		- floor 向下取整,返回<=该参数的最大整数
		>SELECT FLOOR(-1.02);
		- truncate 截断
		>SELECT TRUNCATE(1.69999,1);
		- mod 取余(mod(a,b) => a-(a/b)*b)
		>SELECT MOD(10,-3);
		- rand 随机数
	3. 日期函数
		- now 返回当前系统日期+时间
		>SELECT NOW();
		- curdate 返回当前系统日期,不包含时间
		>SELECT CURDATE();
		- curtime 返回当前时间,不包含日期
		>SELECT CURTIME();
		- 可以获取指定的部分,年、月、日、小时、分钟、秒
		>SELECT YEAR(NOW()) 年;
		>SELECT YEAR('1998-1-1') 年;
		>SELECT  YEAR(hiredate) 年 FROM employees;
		>SELECT MONTH(NOW()) 月;
		>SELECT MONTHNAME(NOW()) 月;
		- str_to_date 将字符通过指定的格式转换成日期
		>SELECT * FROM employees WHERE hiredate = STR_TO_DATE('4-3 1992','%c-%d %Y');
		- date_format 将日期转换成字符
		>SELECT DATE_FORMAT(NOW(),'%y年%m月%d日') AS out_put;
	4. 流程控制函数
		- if函数： if else 的效果
		>SELECT IF(10<5,'大','小');
		>SELECT last_name,commission_pct,IF(commission_pct IS NULL,'没奖金','有奖金') 备注 FROM employees;
		- case函数
			- switch case的效果
			>SELECT salary 原始工资, department_id, CASE department_id WHEN 30 THEN salary * 1.1 WHEN 40 THEN salary * 1.2 WHEN 50 THEN salary * 1.3 ELSE salary END AS 新工资 FROM employees;
			- 多重if的效果
			>SELECT salary, CASE WHEN salary>20000 THEN 'A' WHEN salary>15000 THEN 'B' WHEN salary>10000 THEN 'C' ELSE 'D' END AS 工资级别 FROM employees;
			>SELECT salary FROM employees WHERE CASE WHEN salary>20000 THEN 'A' WHEN salary>15000 THEN 'B' WHEN salary>10000 THEN 'C' ELSE 'D' END = 'C';
	5. 其他函数
		- version版本
		>SELECT VERSION();
		- database当前库
		>SELECT DATABASE();
		- user当前连接用户
		>SELECT USER();

2. 分组函数
    - 功能:用作统计使用,又称为聚合函数或统计函数或组函数
    - 分类:sum(求和)avg(平均值)max(最大值)min(最小值)count(计算个数)
    - 特点:
        1. sum、avg一般用于处理数值型,max、min、count可以处理任何类型
        2. 以上分组函数都忽略null值
        3. 可以和distinct搭配实现去重的运算

## 进阶5:分组查询
1. 简单的分组
	- 查询每个工种的员工平均工资
	>SELECT AVG(salary),job_id FROM employees GROUP BY job_id;
	- 查询每个位置的部门个数
	>SELECT COUNT(*),location_id FROM departments GROUP BY location_id;
2. 可以实现分组前的筛选
	- 查询邮箱中包含a字符的 每个部门的最高工资
	>SELECT MAX(salary),department_id FROM employees WHERE email LIKE '%a%' GROUP BY department_id;
3. 分组后筛选
	- 查询哪个部门的员工个数>5
	>SELECT COUNT(*),department_id FROM employees GROUP BY department_id HAVING COUNT(*)>5;
	- 每个工种有奖金的员工的最高工资>12000的工种编号和最高工资
	>SELECT job_id,MAX(salary) FROM employees WHERE commission_pct IS NOT NULL GROUP BY job_id HAVING MAX(salary)>12000;
4. 按多个字段分组
	- 查询每个工种每个部门的最低工资,并按最低工资降序
	>SELECT MIN(salary),job_id,department_id FROM employees GROUP BY department_id,job_id ORDER BY MIN(salary) DESC;
                                        >
## 进阶6:多表连接查询
1. 内连接
	1. 等值连接
		- 查询每个工种的工种名和员工的个数,并且按员工个数降序
		>SELECT job_title,COUNT(*) FROM employees e,jobs j WHERE e.job_id=j.job_id GROUP BY job_title ORDER BY COUNT(*) DESC;
		>SELECT job_title, COUNT(*) FROM employees e INNER JOIN jobs j ON e.job_id = j.job_id GROUP BY job_title ORDER BY COUNT( * ) DESC;
	2. 非等值连接
		- 查询员工的工资和工资级别 
		>SELECT salary,grade_level FROM employees e,job_grades g WHERE salary BETWEEN g.lowest_sal AND g.highest_sal;
	3. 自连接
		- 查询员工名和上级的名称
		>SELECT e.employee_id,e.last_name,m.employee_id,m.last_name FROM employees e,employees m WHERE e.manager_id=m.employee_id;
2. 外连接
	1. 左外连接
		- 查询哪个部门没有员工
		>SELECT d.*,e.employee_id FROM departments d LEFT OUTER JOIN employees e ON d.department_id = e.department_id WHERE e.employee_id IS NULL;
	2. 右外连接
		- 查询哪个部门没有员工
		>SELECT d.*,e.employee_id FROM employees e RIGHT OUTER JOIN departments d ON d.department_id = e.department_id WHERE e.employee_id IS NULL;
	3. 全外连接
	4. 交叉连接(笛卡尔积)
	>SELECT * FROM departments d CROSS JOIN employees e;

## 进阶7:子查询
- 分类
	- 按子查询出现的位置
		- select后面:仅仅支持标量子查询
			- 查询每个部门的员工个数
			>SELECT d.*,( SELECT COUNT(*) FROM employees e WHERE e.department_id = d.department_id ) 个数 FROM departments d;
		- from后面:支持表子查询
			- 查询每个部门的平均工资的工资等级
			>SELECT ag_dep.*,g.grade_level FROM ( SELECT AVG(salary) ag,department_id FROM employees GROUP BY department_id ) ag_dep INNER JOIN job_grades g ON ag_dep.ag BETWEEN lowest_sal AND highest_sal;
		- where或having后面
			- 标量子查询(单行)
				- 比Abel工资的高员工
				>SELECT * FROM employees WHERE salary>( SELECT salary FROM employees WHERE last_name = 'Abel' );
			- 列子查询(多行)
				- 返回location_id是1400或1700的部门中的所有员工姓名
				>SELECT last_name FROM employees WHERE department_id IN( SELECT DISTINCT department_id FROM departments WHERE location_id IN(1400,1700) );
		- exists后面(相关子查询):表子查询
			- 查询有员工的部门名
			>SELECT department_name FROM departments d WHERE EXISTS( SELECT * FROM employees e WHERE d.department_id=e.department_id );
	- 结果集的行列数不同
		- 标量子查询(结果集只有一行一列)
		- 列子查询(结果集只有一列多行)
		- 行子查询(结果集有一行多列)
		- 表子查询(结果集一般为多行多列)

## 进阶8:分页查询
- 语法:
	- pageSize表示每页显示条目数,pageNumber表示要显示的页数 
	- 公式:select * from  表 limit (pageNumber-1)*pageSize,pageSize
- 查询第11条——第25条
>SELECT * FROM  employees LIMIT 10,15;

## 进阶9:联合查询
- 查询部门编号>90或邮箱包含a的员工信息
>SELECT * FROM employees WHERE email LIKE '%a%' UNION SELECT * FROM employees WHERE department_id>90;

## DML语言
### 插入
- 语法
>insert into 表名(字段名,...) values(值1,...);
- 特点
	1. 字段类型和值类型一致或兼容,而且一一对应
	2. 可以为空的字段,可以不用插入值,或用null填充
	3. 不可以为空的字段,必须插入值
	4. 字段个数和值的个数必须一致
	5. 字段可以省略,但默认所有字段,并且顺序和表中的存储顺序一致
### 修改
- 修改单表语法:
>update 表名 set 字段=新值,字段=新值 where 条件
### 删除
- delete语句 
>delete from 表名 where 筛选条件
- truncate语句
>truncate table 表名
- 两种方式的区别
	1. truncate不能加where条件,而delete可以加where条件
	2. truncate的效率高一点
	3. truncate 删除带自增长的列的表后,如果再插入数据,数据从1开始;delete 删除带自增长列的表后,如果再插入数据,数据从上一次的断点处开始
	4. truncate删除不能回滚,delete删除可以回滚

## DDL语句
### 库和表的管理
- 库的管理
	1. 创建库
	>create database [if not exists]库名;
	2. 修改库
	>ALTER DATABASE books CHARACTER SET gbk;
	2. 删除库
	>drop database [IF EXISTS] 库名
- 表的管理
	1. 创建表
	>create table 表名(列名 列的类型[(长度) 约束],...)
	>DESC 表名;
	2. 修改表 alter
		1. 修改字段名
		>ALTER TABLE 表名 CHANGE  COLUMN 旧列名 新列名 列的类型;
		2. 修改字段类型和列级约束
		>ALTER TABLE 表名 MODIFY COLUMN 列名 列的类型;
		3. 添加字段
		>ALTER TABLE 表名 ADD COLUMN 列名 列的类型;
		4. 删除字段
		>ALTER TABLE 表名 DROP COLUMN 列名;
	3. 删除表
	>DROP TABLE [IF EXISTS] 表名;
### 常见类型
- 整型
- 小数
	- 浮点型
	- 定点型(精确度较高)
- 字符型
- 日期型
- Blob类型
### 常见约束
- NOT NULL:非空,用于保证该字段的值不能为空
- DEFAULT:默认,用于保证该字段有默认值
- UNIQUE:唯一,用于保证该字段的值具有唯一性,可以为空
- CHECK:检查约束,mysql中不支持
- PRIMARY KEY:主键,用于保证该字段的值具有唯一性,并且非空
- FOREIGN KEY:外键,用于限制两个表的关系,用于保证该字段的值必须来自于主表的关联列的值

## 数据库事务
### 含义
>通过一组逻辑操作单元(一组DML——sql语句),将数据从一种状态切换到另外一种状态
###特点
- 原子性:要么都执行,要么都回滚
- 一致性:保证数据的状态操作前和操作后保持一致
- 隔离性:多个事务同时操作相同数据库的同一个数据时,一个事务的执行不受另外一个事务的干扰
- 持久性:一个事务一旦提交,则数据将持久化到本地,除非其他事务对其进行修改
###事务的分类
- 隐式事务,没有明显的开启和结束事务的标志,insert、update、delete语句本身就是一个事务
- 显式事务,具有明显的开启和结束事务的标志
	1. 开启事务,取消自动提交事务的功能
	2. 编写事务的一组逻辑操作单元(多条sql语句)
	3. 提交事务或回滚事务
###事务的隔离级别
- 事务并发问题如何发生?
	- 当多个事务同时操作同一个数据库的相同数据时
- 事务的并发问题有哪些?
	- 脏读:一个事务读取到了另外一个事务未提交的数据
	- 不可重复读:同一个事务中,多次读取到的数据不一致
	- 幻读:一个事务读取数据时,另外一个事务进行更新,导致第一个事务读取到了没有更新的数据
- 如何避免事务的并发问题?通过设置事务的隔离级别
	1. READ UNCOMMITTED
	2. READ COMMITTED 可以避免脏读
	3. REPEATABLE READ 可以避免脏读、不可重复读
	4. SERIALIZABLE可以避免脏读、不可重复读和幻读	
- 设置隔离级别
>set session|global  transaction isolation level 隔离级别名;
- 查看隔离级别
>select @@tx_isolation;

##视图
- 视图的好处
	1. sql语句提高重用性,效率高
	2. 和表实现了分离,提高了安全性
###视图的创建
>CREATE VIEW  视图名 AS 查询语句;
###视图逻辑的更新
1. CREATE OR REPLACE VIEW 视图名 AS 查询语句
2. ALTER VIEW 视图名 AS 查询语句
###视图的删除
>DROP VIEW 视图名;
###视图结构的查看	
>DESC 视图名;
>SHOW CREATE VIEW 视图名;

##SQL执行顺序
1. FORM: 对FROM的左边的表和右边的表计算笛卡尔积.产生虚表VT1
2. ON: 对虚表VT1进行ON筛选,只有那些符合<join-condition>的行才会被记录在虚表VT2中.
3. JOIN: 如果指定了LEFT JOIN或RIGHT JOIN,那么保留表中未匹配的行就会作为外部行添加到虚拟表VT2中,产生虚拟表VT3.
   (from子句中包含两个以上的表的话,那么就会对上一个join连接产生的结果VT3和下一个表重复执行步骤1~3这三个步骤,一直到处理完所有的表为止)
4. WHERE: 对虚拟表VT3进行WHERE条件过滤.只有符合<where-condition>的记录才会被插入到虚拟表VT4中.
5. GROUP BY: 根据group by子句中的列,对VT4中的记录进行分组操作,产生VT5.
6. CUBE | ROLLUP: 对表VT5进行cube或者rollup操作,产生表VT6.
7. HAVING: 对虚拟表VT6应用having过滤,只有符合<having-condition>的记录才会被插入到虚拟表VT7中.
8. SELECT: 执行select操作,选择指定的列,插入到虚拟表VT8中.
9. DISTINCT: 对VT8中的记录进行去重.产生虚拟表VT9.
10. ORDER BY: 将虚拟表VT9中的记录按照<order_by_list>进行排序操作,产生虚拟表VT10.
11. LIMIT:取出指定行的记录,产生虚拟表VT11,并将结果返回.