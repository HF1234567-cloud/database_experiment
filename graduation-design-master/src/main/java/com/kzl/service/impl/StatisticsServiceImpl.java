package com.kzl.service.impl;

import com.kzl.dao.StatisticsMapper;
import com.kzl.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public Map<String, Object> statisticsOverview() {
        Map<String,Object> data = new HashMap<>();

        // 顶部总览
        data.put("studentTotal", statisticsMapper.countStudents());
        data.put("teacherTotal", statisticsMapper.countTeachers());
        data.put("collegeTotal", statisticsMapper.countColleges());
        data.put("courseTotal", statisticsMapper.countCourses());
        data.put("informationTotal", statisticsMapper.countInformation());

        // 学生/教师按学院分布
        List<Map<String,Object>> studentByCollege = statisticsMapper.countStudentByCollege();
        List<Map<String,Object>> teacherByCollege = statisticsMapper.countTeacherByCollege();
        data.put("studentByCollege", studentByCollege);
        data.put("teacherByCollege", teacherByCollege);

        // 资讯按展示端分布
        List<Map<String,Object>> informationByRole = statisticsMapper.countInformationByRole();
        data.put("informationByRole", informationByRole);

        // 课程按学年统计 & 选课人数TopN
        List<Map<String,Object>> courseByAcademicYear = statisticsMapper.countCourseByAcademicYear();
        List<Map<String,Object>> topCourseBySelected = statisticsMapper.topCourseBySelected();
        data.put("courseByAcademicYear", courseByAcademicYear);
        data.put("topCourseBySelected", topCourseBySelected);

        return data;
    }
}
