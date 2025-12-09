package com.kzl.controller;

import com.kzl.service.StatisticsService;
import com.kzl.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("manage")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping("statistics")
    public String statistics(HttpServletRequest request){
        boolean state = ManageController.judgeUserLoginState(request);
        return state ? "manage/statistics" : "redirect:/";
    }

    @ResponseBody
    @RequestMapping("statisticsData")
    public Result statisticsData(){
        Map<String,Object> data = statisticsService.statisticsOverview();
        return Result.createSuccess(data);
    }
}
