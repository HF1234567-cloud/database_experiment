## 一.功能简介
这是一个功能基本齐全的学生选课管理系统，使用java实现，用到的后端框架是SpringBoot、Mybatis，前端框架是layui，数据库使用MySQL 等技术.

**系统中用户分为三种：**
```
管理员(管理可以以及相关的数据)
教师(查询教授课程以及对学生选择的当前课程的成绩评定)
学生(选择课程，查询课程以及选课成绩/学分)
```
####  1.1 管理员模块功能

```
1.菜单管理
2.教务管理
	2.1. 学院管理
	2.2. 首页公告管理
3.人员管理
	3.1. 教师管理
	3.2. 学生管理
4.角色管理
	4.1. 角色分配管理
5.课程管理
	1. 选课管理
	2. 学年管理
```
####  1.2 教师模块功能

```
1. 首页公告展示
2. 课程信息
3. 成绩管理
4. 统计信息
```
#### 1.3. 学生模块功能

```
1. 首页公告展示
2. 选课中心
3. 已选课程
4. 选课统计
```
## 二. 项目截图
####  1.注册
![学生注册](https://img-blog.csdnimg.cn/20200810170509244.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgyNzg2Ng==,size_16,color_FFFFFF,t_70)
####  2.登录
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200810175948292.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgyNzg2Ng==,size_16,color_FFFFFF,t_70)
####  3.管理员模块
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201112153539162.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgyNzg2Ng==,size_16,color_FFFFFF,t_70#pic_center)
####  4.教师模块
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201112153641554.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgyNzg2Ng==,size_16,color_FFFFFF,t_70#pic_center)

####  5.学生模块
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201112153732328.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgyNzg2Ng==,size_16,color_FFFFFF,t_70#pic_center)
## 三. 系统设计图
####  1.管理模块设计
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201112154015409.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgyNzg2Ng==,size_16,color_FFFFFF,t_70#pic_center)

####  2.教师模块设计
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201112154033634.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgyNzg2Ng==,size_16,color_FFFFFF,t_70#pic_center)

####  3.学生模块设计
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201112153908191.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgyNzg2Ng==,size_16,color_FFFFFF,t_70#pic_center)


**项目gitee地址: https://gitee.com/kangz1/graduation-design.git,有兴趣的点个Star**

**有问题可以加我微信kzlear一起讨论~~~**



##bug修改
    1.学生注册跳转问题修改
    2.管理员模块中添加课程不成功问题修改
    3.管理员模块中添加课程时不需要添加选择选择，默认添加的课程导当前的学年
    4.注册的学院查询改为从数据库中查询
    5.管理员模块中添加课程删除功能
    6.管理员模块中课程列表查询中的学院下拉选改为从数据库中查询
    7.管理员模块中人员管理下的教师管理和学生管理的学院下拉选改为从数据库中查询
    8.管理员模块中添加课程中学院下拉选和教师的下拉选改为从数据库中查询

                                                        
                                                            -2021-01-05 