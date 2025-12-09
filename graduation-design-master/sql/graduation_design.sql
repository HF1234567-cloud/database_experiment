/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : graduation_design

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 12/11/2020 15:01:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学院名称',
  `teacherNum` int(4) NOT NULL COMMENT '学院下教师数',
  `studentNum` int(4) NOT NULL COMMENT '学院下学生数',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `createDate` datetime(0) NOT NULL COMMENT '创建时间',
  `updateDate` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学院表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('1', '软件工程学院', 0, 0, '1', '2020-07-23 17:43:30', '2020-07-30 10:38:47');
INSERT INTO `college` VALUES ('2', '计算机技术与科学学院', 11, 11, '0', '2020-07-23 17:44:00', '2020-10-12 15:12:24');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `courseName` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程名称',
  `collegeId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学院id',
  `teacherId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '教师id',
  `classPlace` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '上课地点',
  `classDate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '上课时间',
  `startDate` datetime(0) NOT NULL COMMENT '报考开始时间',
  `endDate` datetime(0) NOT NULL COMMENT '报考结束时间',
  `credits` double(5, 1) NOT NULL COMMENT '学分',
  `academicYear` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学年',
  `teachEndDate` datetime(0) NOT NULL COMMENT '课程结束时间 \r\n课程结束之后教师可以对选择这门课程的学生打分',
  `optional` int(10) NOT NULL COMMENT '可选人数',
  `primaryAmount` int(10) NOT NULL COMMENT '预选人数',
  `selected` int(10) NOT NULL COMMENT '选中人数',
  `remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '课程说明',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '状态 0禁用 1正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '计算机网络基础', '1', '1', '6号主教学楼 202<br/>\r\n6号主教学楼 202', '星期三 下午第二节课 8-11周<br>星期三 下午第二节课 13-14周', '2020-08-03 17:44:59', '2020-08-03 17:45:02', 5.0, '2020上半学年', '2020-08-08 17:44:52', 1, 1, 1, '1、页面上方的【选课中心】，然后点击左侧的【推荐选课】，就进入到推荐选课界面。在【推荐选课】中，学生可以查看自己方案内并且推荐自己（所在行政班）上课的除《体育Ⅱ-Ⅳ》外所有开设的课程。一般都需要修读。<br/>\r\n2、选择【选课志愿】，然后点击【选课】按钮，若上课时间不冲突并且有剩余容量时，则选课成功。<br/> 3、在【方案内课程选课】中，学生可以选择本专业其他学期的课程【原则上不建议选择】，操作同【推荐选课】（第三、四阶段开放）。<br/> 4、在【方案外课程选课】中，学生可以根据自身的特点和兴趣，选择其他专业的专业课程，以扩充自己的知识面，操作同【推荐选课】（第三、四阶段开放）。');
INSERT INTO `course` VALUES ('2', '计算机网络基础2', '1', '1', '6号主教学楼 202<br/>\r\n6号主教学楼 202', '星期三 下午第二节课 8-11周<br>星期三 下午第二节课 13-14周', '2020-08-03 17:45:26', '2020-08-03 17:45:29', 5.0, '2020上半学年', '2020-08-03 17:45:31', 32, 1, 12, '1、页面上方的【选课中心】，然后点击左侧的【推荐选课】，就进入到推荐选课界面。在【推荐选课】中，学生可以查看自己方案内并且推荐自己（所在行政班）上课的除《体育Ⅱ-Ⅳ》外所有开设的课程。一般都需要修读。<br/> 2、选择【选课志愿】，然后点击【选课】按钮，若上课时间不冲突并且有剩余容量时，则选课成功。<br/> 3、在【方案内课程选课】中，学生可以选择本专业其他学期的课程【原则上不建议选择】，操作同【推荐选课】（第三、四阶段开放）。<br/> 4、在【方案外课程选课】中，学生可以根据自身的特点和兴趣，选择其他专业的专业课程，以扩充自己的知识面，操作同【推荐选课】（第三、四阶段开放）。');
INSERT INTO `course` VALUES ('7508a2b591ae4499b3f8d17bc1d6e92a', '计算机网络基础3', '2', '1', '6号主教学楼 202<br/>\r\n6号主教学楼 202', '星期三 下午第二节课 8-11周<br>星期三 下午第二节课 13-14周', '2020-08-03 00:00:00', '2020-08-03 00:00:00', 4.0, '2020上半学年', '2020-08-03 00:00:00', 68, 0, 0, '1、页面上方的【选课中心】，然后点击左侧的【推荐选课】，就进入到推荐选课界面。在【推荐选课】中，学生可以查看自己方案内并且推荐自己（所在行政班）上课的除《体育Ⅱ-Ⅳ》外所有开设的课程。一般都需要修读。<br/> 2、选择【选课志愿】，然后点击【选课】按钮，若上课时间不冲突并且有剩余容量时，则选课成功。<br/> 3、在【方案内课程选课】中，学生可以选择本专业其他学期的课程【原则上不建议选择】，操作同【推荐选课】（第三、四阶段开放）。<br/> 4、在【方案外课程选课】中，学生可以根据自身的特点和兴趣，选择其他专业的专业课程，以扩充自己的知识面，操作同【推荐选课】（第三、四阶段开放）。');
INSERT INTO `course` VALUES ('8beb093dd8b749e199ff18da3ae2fe20', '计算机网络基础4', '2', '1', '6号主教学楼 202<br/>\r\n6号主教学楼 202', '星期三 下午第二节课 8-11周<br>星期三 下午第二节课 13-14周', '2020-08-03 00:00:00', '2020-08-03 00:00:00', 5.0, '2020上半学年', '2020-08-03 00:00:00', 123, 0, 0, '1、页面上方的【选课中心】，然后点击左侧的【推荐选课】，就进入到推荐选课界面。在【推荐选课】中，学生可以查看自己方案内并且推荐自己（所在行政班）上课的除《体育Ⅱ-Ⅳ》外所有开设的课程。一般都需要修读。<br/>2、选择【选课志愿】，然后点击【选课】按钮，若上课时间不冲突并且有剩余容量时，则选课成功。<br/>3、在【方案内课程选课】中，学生可以选择本专业其他学期的课程【原则上不建议选择】，操作同【推荐选课】（第三、四阶段开放）。<br/>4、在【方案外课程选课】中，学生可以根据自身的特点和兴趣，选择其他专业的专业课程，以扩充自己的知识面，操作同【推荐选课】（第三、四阶段开放）。');
INSERT INTO `course` VALUES ('d006bccea7654b26b33be71ee5c3197a', '计算机网络基础5', '2', '1', '6号主教学楼 202<br/>\r\n6号主教学楼 202', '星期三 下午第二节课 8-11周<br>星期三 下午第二节课 13-14周', '2020-07-04 00:00:00', '2020-08-04 00:00:00', 5.0, '2020上半学年', '2020-08-04 00:00:00', 115, 0, 0, '1、页面上方的【选课中心】，然后点击左侧的【推荐选课】，就进入到推荐选课界面。在【推荐选课】中，学生可以查看自己方案内并且推荐自己（所在行政班）上课的除《体育Ⅱ-Ⅳ》外所有开设的课程。一般都需要修读。<br>2、选择【选课志愿】，然后点击【选课】按钮，若上课时间不冲突并且有剩余容量时，则选课成功。<br>3、在【方案内课程选课】中，学生可以选择本专业其他学期的课程【原则上不建议选择】，操作同【推荐选课】（第三、四阶段开放）。<br>4、在【方案外课程选课】中，学生可以根据自身的特点和兴趣，选择其他专业的专业课程，以扩充自己的知识面，操作同【推荐选课】（第三、四阶段开放）。');

-- ----------------------------
-- Table structure for course_academic_year
-- ----------------------------
DROP TABLE IF EXISTS `course_academic_year`;
CREATE TABLE `course_academic_year`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `academicYear` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学年',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程学年选择' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_academic_year
-- ----------------------------
INSERT INTO `course_academic_year` VALUES ('1', '2020上半学年', '1');
INSERT INTO `course_academic_year` VALUES ('2', '2020下半学年', '0');
INSERT INTO `course_academic_year` VALUES ('3', '2019上半学年', '0');
INSERT INTO `course_academic_year` VALUES ('4', '2019下半学年', '0');
INSERT INTO `course_academic_year` VALUES ('75ee03db4e09408198f021602baf0ca2', '2021上半学年', '0');

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `publishDate` datetime(0) NOT NULL COMMENT '发布时间',
  `roleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '首页资讯信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of information
-- ----------------------------
INSERT INTO `information` VALUES ('1', '关于本学期校级任选课相关事宜安排的通知', '各学院及相关单位:<br>\r\n                        根据学校教学安排，本学期全校任选课定于6月20日（星期五） 正式开课，选课工作将从6月16日开始。现将有关事项通知如下：<br>\r\n                        一、选课时间：6月16日----6月20日<br>\r\n                        二、学生登录选课信息管理系统后进入学生选课进行选课。学生选课前请认真阅读选课公告，再进行“网上选课”。<br>\r\n                        三、教师可登陆选课信息管理系统后进入选课管理增删课程， 6月15日14:00之后各位教师及学生登录教务处网站查询自己申报的课程是否停开。<br>\r\n                        四、选课退课均须在选课时间内完成，选课结束后不再进行补退选，请学生注意选课时间。<br>\r\n                        五、请各学院通知并组织学生进行选课，同时请各学院及相关单位通知任课教师按时上课。教务处将组织相关人员对上课情况进行不定期的检查。<br>', '2020-07-18 23:53:18', '1');
INSERT INTO `information` VALUES ('2', '关于本学期校级任选课相关事宜安排的通知', '各学院及相关单位:<br>\r\n                        根据学校教学安排，本学期全校任选课定于6月20日（星期五） 正式开课，选课工作将从6月16日开始。现将有关事项通知如下：<br>\r\n                        一、选课时间：6月16日----6月20日<br>\r\n                        二、学生登录选课信息管理系统后进入学生选课进行选课。学生选课前请认真阅读选课公告，再进行“网上选课”。<br>\r\n                        三、教师可登陆选课信息管理系统后进入选课管理增删课程， 6月15日14:00之后各位教师及学生登录教务处网站查询自己申报的课程是否停开。<br>\r\n                        四、选课退课均须在选课时间内完成，选课结束后不再进行补退选，请学生注意选课时间。<br>\r\n                        五、请各学院通知并组织学生进行选课，同时请各学院及相关单位通知任课教师按时上课。教务处将组织相关人员对上课情况进行不定期的检查。<br>', '2020-07-24 09:47:29', '2');
INSERT INTO `information` VALUES ('3', '关于本学期校级任选课相关事宜安排的通知', '各学院及相关单位:<br>\r\n                        根据学校教学安排，本学期全校任选课定于6月20日（星期五） 正式开课，选课工作将从6月16日开始。现将有关事项通知如下：<br>\r\n                        一、选课时间：6月16日----6月20日<br>\r\n                        二、学生登录选课信息管理系统后进入学生选课进行选课。学生选课前请认真阅读选课公告，再进行“网上选课”。<br>\r\n                        三、教师可登陆选课信息管理系统后进入选课管理增删课程， 6月15日14:00之后各位教师及学生登录教务处网站查询自己申报的课程是否停开。<br>\r\n                        四、选课退课均须在选课时间内完成，选课结束后不再进行补退选，请学生注意选课时间。<br>\r\n                        五、请各学院通知并组织学生进行选课，同时请各学院及相关单位通知任课教师按时上课。教务处将组织相关人员对上课情况进行不定期的检查。<br>', '2020-07-24 09:48:09', '3');

-- ----------------------------
-- Table structure for manage_user
-- ----------------------------
DROP TABLE IF EXISTS `manage_user`;
CREATE TABLE `manage_user`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `loginName` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名 ',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `roleId` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `createDate` datetime(0) NOT NULL COMMENT '创建时间',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manage_user
-- ----------------------------
INSERT INTO `manage_user` VALUES ('1', 'admin', 'admin', 'admin', '1', '2020-07-18 23:12:53', '1');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parentId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '父类id',
  `createDate` datetime(0) NOT NULL COMMENT '创建时间',
  `createId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人id',
  `updateDate` datetime(0) NOT NULL COMMENT '修改时间',
  `sort` int(3) NOT NULL COMMENT '排序',
  `href` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '链接',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `remark` varchar(2555) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '首页', '0', '2020-07-18 23:44:05', '1', '2020-08-18 17:42:18', 1, '/manage/index', '0', '首页数据展示');
INSERT INTO `menu` VALUES ('10', '角色分配', '5', '2020-07-20 10:14:42', '1', '2020-07-20 10:14:42', 1, '/manage/role', '1', '管理角色分配菜单');
INSERT INTO `menu` VALUES ('11', '课程管理', '0', '2020-08-03 16:29:02', '1', '2020-08-05 08:51:05', 6, '	 javascript:;', '1', '课程管理');
INSERT INTO `menu` VALUES ('2', '菜单管理', '0', '2020-07-18 23:44:46', '1', '2020-07-18 23:44:51', 2, '/manage/menu', '1', '管理菜单');
INSERT INTO `menu` VALUES ('3', '教务管理', '0', '2020-07-18 23:45:21', '1', '2020-07-18 23:45:21', 3, 'javascript:;', '1', NULL);
INSERT INTO `menu` VALUES ('4', '人员管理', '0', '2020-07-18 23:45:21', '1', '2020-07-23 17:50:11', 4, 'javascript:;', '1', 'undefined');
INSERT INTO `menu` VALUES ('5', '角色管理', '0', '2020-07-18 23:45:21', '1', '2020-07-18 23:45:21', 5, 'javascript:;', '1', NULL);
INSERT INTO `menu` VALUES ('5b185f5d08224728903575552e15dbf8', '选课管理', '11', '2020-08-05 08:50:12', '1', '2020-08-18 15:20:29', 2, '/course/list', '1', '选课管理');
INSERT INTO `menu` VALUES ('6', '学院管理', '3', '2020-07-20 10:13:28', '1', '2020-07-20 10:13:28', 1, '/manage/college', '1', NULL);
INSERT INTO `menu` VALUES ('7', '首页通告管理', '3', '2020-07-20 10:13:28', '1', '2020-08-17 18:20:31', 2, '/manage/information', '1', NULL);
INSERT INTO `menu` VALUES ('8', '教师管理', '4', '2020-07-20 10:14:42', '1', '2020-07-20 10:14:42', 1, '/manage/teacher', '1', NULL);
INSERT INTO `menu` VALUES ('88bd8c79792346058d2bcd0ef831ec61', '选课中心', '0', '2020-08-04 08:13:45', '1', '2020-08-04 08:13:45', 7, '/student/courseCase', '1', '学生选课中心');
INSERT INTO `menu` VALUES ('8d1ba0638b474dafa943891b14d17f97', '学生信息', '0', '2020-08-18 15:09:17', '1', '2020-08-18 15:11:08', 3, '/teacher/selectedCourseStu', '1', '选修当前课程的学生');
INSERT INTO `menu` VALUES ('9', '学生管理', '4', '2020-07-20 10:14:42', '1', '2020-07-20 10:14:42', 2, '/manage/student', '1', NULL);
INSERT INTO `menu` VALUES ('b69d605f77214bb889c14690ae34150d', '课程信息', '0', '2020-08-05 14:40:25', '1', '2020-08-05 14:40:25', 2, '/teacher/courseInfo', '1', '教师分配的课程信息');
INSERT INTO `menu` VALUES ('ca437bc787404b938308d68925b50f9b', '已选课程', '0', '2020-08-04 08:15:01', '1', '2020-08-04 08:15:01', 8, '/student/selectedCourse', '1', '学生选择的课程');
INSERT INTO `menu` VALUES ('d4100b530ff9403c9f576138006d269f', '选课统计', '0', '2020-08-04 08:16:21', '1', '2020-08-04 08:16:21', 9, '/student/statistical', '1', '学生选课统计');
INSERT INTO `menu` VALUES ('dacb9a4039a34aeb8ae834d409de6082', '学年管理', '11', '2020-08-05 08:50:53', '1', '2020-08-18 15:20:38', 1, '/course/courseAcademicYear', '1', '课程学年管理');
INSERT INTO `menu` VALUES ('e8902e1c3e70451e9fa204b423bd33ec', '成绩管理', '0', '2020-08-05 14:42:54', '1', '2020-08-05 14:43:02', 3, '/teacher/scoreInfo', '1', '教师菜单，学生成绩管理');
INSERT INTO `menu` VALUES ('fe84c18b69144f2485ee157aaedd7c2d', '统计信息', '0', '2020-08-05 14:43:38', '1', '2020-08-05 14:43:38', 4, '/teacher/statisticalInfo', '1', '教师菜单，学生统计信息');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理', '2020-07-18 23:14:08', '1');
INSERT INTO `role` VALUES ('2', '教师', '2020-07-18 23:15:04', '1');
INSERT INTO `role` VALUES ('3', '学生', '2020-07-18 23:15:00', '1');
INSERT INTO `role` VALUES ('f1eae1546dbb4b90bb6af3349aacd5a1', '测试角色', '2020-07-31 18:17:56', '1');

-- ----------------------------
-- Table structure for role_menu_rel
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_rel`;
CREATE TABLE `role_menu_rel`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `roleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `menuId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu_rel
-- ----------------------------
INSERT INTO `role_menu_rel` VALUES ('057d350d8d4541d7adb8f40f5c0b2efd', '3', 'ca437bc787404b938308d68925b50f9b');
INSERT INTO `role_menu_rel` VALUES ('191cd51e89c246f68e29b6cc53696224', 'f1eae1546dbb4b90bb6af3349aacd5a1', '2');
INSERT INTO `role_menu_rel` VALUES ('1e883a1d3a6c4ffa83194ce621887c91', '1', 'dacb9a4039a34aeb8ae834d409de6082');
INSERT INTO `role_menu_rel` VALUES ('21dd3430f950484a9fb9c138acd78e22', '1', '5b185f5d08224728903575552e15dbf8');
INSERT INTO `role_menu_rel` VALUES ('220b8f0ac1d34f059a7469f4511400ad', '', '3');
INSERT INTO `role_menu_rel` VALUES ('27c90e30aa484d49bf4fd2dcc0350704', '2', '1');
INSERT INTO `role_menu_rel` VALUES ('295bf640f63845e88edba93434c04763', '', '1');
INSERT INTO `role_menu_rel` VALUES ('312eeac553b248c99404293fade6415e', '1', '1');
INSERT INTO `role_menu_rel` VALUES ('31a6e6f4d1a149b68403fc1c4d1e6730', '1', '9');
INSERT INTO `role_menu_rel` VALUES ('4db055734d7b45ca8d57fdfe91febf55', '1', '2');
INSERT INTO `role_menu_rel` VALUES ('5339119741df4d0caeb614968ac4a01a', '1', '3');
INSERT INTO `role_menu_rel` VALUES ('5423e50cf39e472e8c329ecea8dc5a59', '1', '7');
INSERT INTO `role_menu_rel` VALUES ('5bcb975730004801b8f2bb49a5084845', '3', '88bd8c79792346058d2bcd0ef831ec61');
INSERT INTO `role_menu_rel` VALUES ('76b22b7900274b44abe3ddcba96ab027', '1', '6');
INSERT INTO `role_menu_rel` VALUES ('77e2632acc0d4615a595d66bbafc3356', '1', '5');
INSERT INTO `role_menu_rel` VALUES ('86ab09b688c64ad0b8b64e066d396e6c', '1', '8');
INSERT INTO `role_menu_rel` VALUES ('8d54362bd599427ba39ec212d6ebbc54', '2', 'e8902e1c3e70451e9fa204b423bd33ec');
INSERT INTO `role_menu_rel` VALUES ('90eb463f60e24365bcdd811f0b67d665', '', '10');
INSERT INTO `role_menu_rel` VALUES ('922b81b6e2ef4834b14a9d199bcc02c3', '2', 'b69d605f77214bb889c14690ae34150d');
INSERT INTO `role_menu_rel` VALUES ('925bf75b893b4664a34804109fcf6923', '2', '8d1ba0638b474dafa943891b14d17f97');
INSERT INTO `role_menu_rel` VALUES ('a9893c0515cc481b871ee1c400c2acf7', 'f1eae1546dbb4b90bb6af3349aacd5a1', '1');
INSERT INTO `role_menu_rel` VALUES ('abc7de1a1e7a405b81611b53d59f442e', '', '6');
INSERT INTO `role_menu_rel` VALUES ('b53af1a98bb74b0997b919194728c457', '1', '4');
INSERT INTO `role_menu_rel` VALUES ('b736db0909f74a518798c2118a0c0676', '2', 'fe84c18b69144f2485ee157aaedd7c2d');
INSERT INTO `role_menu_rel` VALUES ('b75b51364f784f7fada620ec30b11f77', '', '4');
INSERT INTO `role_menu_rel` VALUES ('e3f2fefed6b34c3da8ed8ca295db54ee', '', '8');
INSERT INTO `role_menu_rel` VALUES ('e4482f66a1bc460fac5b59f437890259', '3', 'd4100b530ff9403c9f576138006d269f');
INSERT INTO `role_menu_rel` VALUES ('f161bd1063ee437fab0642b2a3d45168', '3', '1');
INSERT INTO `role_menu_rel` VALUES ('f38eaef8ebfb4db880aa03f9c94df113', '1', '11');
INSERT INTO `role_menu_rel` VALUES ('fe131753898d40988aacef6674e5f47f', '1', '10');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `studentNumber` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `roleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `collegeId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学院id',
  `createDate` datetime(0) NOT NULL COMMENT '创建时间',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态 0禁用 1正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('4b34934c56df4bbdae336a334973e7fc', '741011421564111', '夏九幽', '123456', '13945614520', '32541415264@qq.com', '3', '1', '2020-07-17 17:19:08', '1');
INSERT INTO `student` VALUES ('8c367b8b7b6b4f098dd85efc26969daf', '741011421564112', '涂飞', '123456', '15241254520', '654125478962@qq.com', '3', '1', '2020-07-17 17:29:56', '1');
INSERT INTO `student` VALUES ('af82b7bdba124a4e80e17827fad6647d', '741011421564113', '姜太虚', '123456', '13378974152', '49843214567@qq.com', '3', '2', '2020-07-17 17:26:20', '1');
INSERT INTO `student` VALUES ('f1283ca0cb534f979bd1e2a73077b45e', '741011421564114', '段德', '12345', '18345789870', '74851426348@qq.com', '3', '1', '2020-07-17 11:58:10', '1');

-- ----------------------------
-- Table structure for student_course_rel
-- ----------------------------
DROP TABLE IF EXISTS `student_course_rel`;
CREATE TABLE `student_course_rel`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `studentId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学生id',
  `courseId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程id',
  `teacherId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评分教师',
  `isQualified` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '成绩是否合格 0不合格 1合格',
  `creditsRemark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '教师评分备注',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否已对当前学生评分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生课程关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_course_rel
-- ----------------------------
INSERT INTO `student_course_rel` VALUES ('2e8b63dddcf54b0383b317e1a136e868', '4b34934c56df4bbdae336a334973e7fc', '2', NULL, NULL, NULL, '0');
INSERT INTO `student_course_rel` VALUES ('e1c87ea078c44ba5a0ba4362fb196021', '8c367b8b7b6b4f098dd85efc26969daf', '2', NULL, NULL, NULL, '0');
INSERT INTO `student_course_rel` VALUES ('e94080073128488ea889278d1174b723', '4b34934c56df4bbdae336a334973e7fc', '1', NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `loginName` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `roleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `collegeId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学院id',
  `createDate` datetime(0) NOT NULL COMMENT '创建人id',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态 0禁用 1正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '教师表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '叶凡', '叶凡', '123456', '18385147410', '18414523285@qq.com', '2', '1', '2020-07-17 11:51:24', '1');
INSERT INTO `teacher` VALUES ('2', '庞博', '庞博', '123456', '15345217450', '45147896741@qq.com', '2', '1', '2020-08-01 15:05:43', '1');

SET FOREIGN_KEY_CHECKS = 1;
