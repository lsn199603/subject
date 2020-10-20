package com.education.subject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.education.subject.Result.TableResult;
import com.education.subject.Tool.CountToArray;
import com.education.subject.Tool.Time;
import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.PrizeCountToArray;
import com.education.subject.entity.Teachingresult;
import com.education.subject.service.TeachingresultService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 博士点信息表 前端控制器
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/teachingresult")
public class TeachingresultController {
    @Autowired
    private TeachingresultService teachingresultService;
    private  Time time = new Time();

    //查询全部博士点信息
    @RequestMapping("/queryAll")
    public TableResult<Teachingresult> queryAll(){
        TableResult<Teachingresult> teachingresultTableResult = new TableResult<>();
        QueryWrapper<Teachingresult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("tr_date");
        queryWrapper.orderByAsc("tr_grade");
        queryWrapper.orderByAsc("si_id");
        List<Teachingresult> dList = teachingresultService.list(queryWrapper);
        int count = teachingresultService.count(queryWrapper);
        teachingresultTableResult.setCount((long)count);
        teachingresultTableResult.setData(dList);
        return teachingresultTableResult;
    }

    //分页显示全部信息
    @RequestMapping("/list2")
    public TableResult<Teachingresult> list2(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        TableResult<Teachingresult> teachingresultTableResult = new TableResult<>();
        QueryWrapper<Teachingresult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("tr_date");
        queryWrapper.orderByAsc("tr_grade");
        queryWrapper.orderByAsc("si_id");
        List<Teachingresult> dList = teachingresultService.list(queryWrapper);
        teachingresultTableResult.setCount(((Page)dList).getTotal());
        teachingresultTableResult.setData(dList);
        return teachingresultTableResult;
    }

    //重载分页显示全部信息
    @RequestMapping("/list1")
    public TableResult<Teachingresult> list1(Integer page,Integer limit,String startTime,String endTime,String school,String dGrade) {
        PageHelper.startPage(page,limit);
        TableResult<Teachingresult> teachingresultTableResult = new TableResult<>();
        QueryWrapper<Teachingresult> queryWrapper = new QueryWrapper<>();
        if (startTime.equals("")&&endTime.equals("")){

        }else {
            queryWrapper.ge("tr_date", startTime).le("tr_date", endTime);
        }

        queryWrapper.like("si_name",school);
        queryWrapper.like("tr_grade",dGrade);
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("tr_date");
        queryWrapper.orderByAsc("tr_grade","si_id");
        List<Teachingresult> dList = teachingresultService.list(queryWrapper);
        teachingresultTableResult.setCount(((Page)dList).getTotal());
        teachingresultTableResult.setData(dList);
        return teachingresultTableResult;
    }


    //批量添加博士点信息
    @RequestMapping("/addAllDoctor")
    @ResponseBody
    public boolean addAllDoctor(@RequestBody Teachingresult teachingresult, HttpServletRequest request) {
        List<Teachingresult> list = teachingresult.getTeachingresultList();
        return teachingresultService.saveBatch(list);
    }

    //查询一条
    @RequestMapping("/getText/{id}")
    public Teachingresult getDoctorById(@PathVariable int id) {
        Teachingresult teachingresult = teachingresultService.getById(id);
        return teachingresult;
    }

    //伪删除一条博士点信息
    @RequestMapping("/remove/{id}")
    public boolean remove(@PathVariable int id){
        Teachingresult teachingresult = new Teachingresult();
        teachingresult.setSign(1);
        UpdateWrapper<Teachingresult> teachingresultUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        teachingresultUpdateWrapper.eq("id",id);
        boolean update = teachingresultService.update(teachingresult,teachingresultUpdateWrapper);
        return update;
    }

    //伪删除多条博士点信息
    @RequestMapping("/removeAll/{ids}")
    public boolean removeAll(@PathVariable Integer[] ids){
        Teachingresult teachingresult = new Teachingresult();
        teachingresult.setSign(1);
        UpdateWrapper<Teachingresult> teachingresultUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        teachingresultUpdateWrapper.in("id",ids);
        boolean update = teachingresultService.update(teachingresult,teachingresultUpdateWrapper);
        return update;
    }

    //修改博士点信息
    @RequestMapping("/update")
    public boolean update(Teachingresult teachingresult){
        return teachingresultService.updateById(teachingresult);
    }

    //教学成果统计
    @RequestMapping("/list")
    public TableResult<Prize> list(){
        TableResult<Prize> prizeCountResult = new TableResult<>();
        QueryWrapper<Teachingresult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tr_date", time.getNowYear());
        long count = teachingresultService.selectPrizeCount().size();
        List<Prize> dList = teachingresultService.selectPrizeCount();
        prizeCountResult.setCount(count);
        prizeCountResult.setData(dList);
        return prizeCountResult;
    }

    //根据年时间段搜索成果统计
    @RequestMapping("/queryByDate")
    public TableResult<Prize> queryByDate(String startTime,String endTime){
        TableResult<Prize> prizeCountResult = new TableResult<>();
        long count = teachingresultService.selectPrizeCount().size();
        if (startTime.equals("")&&endTime.equals("")){
            List<Prize> dList = teachingresultService.selectPrizeCount();
            prizeCountResult.setCount(count);
            prizeCountResult.setData(dList);
        }else {
            List<Prize> dList = teachingresultService.queryByDate(startTime, endTime);

            prizeCountResult.setCount(count);
            prizeCountResult.setData(dList);
        }
        return prizeCountResult;
    }

    //根据高校名称与获奖等级查询统计结果详细信息
    @RequestMapping("/CountDetail/{schoolname}/{trGrade}")
    public TableResult<Teachingresult> CountDetail(Integer page, Integer limit,@PathVariable String schoolname,@PathVariable String trGrade){
        PageHelper.startPage(page,limit);
        TableResult<Teachingresult> TeachingResult = new TableResult<>();
        QueryWrapper<Teachingresult> queryWrapper = new QueryWrapper<>();
        if (trGrade.equals("统计")){
            queryWrapper.eq("si_name",schoolname);
            queryWrapper.eq("sign",0);
            queryWrapper.orderByDesc("tr_date");
        }else {
            queryWrapper.eq("si_name",schoolname);
            queryWrapper.eq("tr_grade",trGrade);
            queryWrapper.eq("sign",0);
            queryWrapper.orderByDesc("tr_date");
        }
        List<Teachingresult> dList = teachingresultService.list(queryWrapper);
        TeachingResult.setCount(((Page)dList).getTotal());
        TeachingResult.setData(dList);
        return TeachingResult;
    }
    //根据高校名称、获奖时间与获奖等级查询统计结果详细信息
    @RequestMapping("/CountCompareDetail/{schoolname}/{time}/{trGrade}")
    public TableResult<Teachingresult> CountCompareDetail(Integer page, Integer limit,@PathVariable String schoolname,@PathVariable String time, @PathVariable String trGrade){
        PageHelper.startPage(page,limit);
        TableResult<Teachingresult> TeachingResult = new TableResult<>();
        QueryWrapper<Teachingresult> queryWrapper = new QueryWrapper<>();
        if (trGrade.equals("统计")){
            String [] date= time.split(",");
            queryWrapper.between("tr_date",date[0],date[2]);
            queryWrapper.eq("si_name",schoolname);
            queryWrapper.eq("sign",0);
            queryWrapper.orderByDesc("tr_date");
        }else {
            queryWrapper.eq("si_name",schoolname);
            queryWrapper.eq("tr_date",time);
            queryWrapper.eq("tr_grade",trGrade);
            queryWrapper.eq("sign",0);
        }
        List<Teachingresult> dList = teachingresultService.list(queryWrapper);
        TeachingResult.setCount(((Page)dList).getTotal());
        TeachingResult.setData(dList);
        return TeachingResult;
    }

    //导出教学成果统计
    @RequestMapping("/export/{startTime}/{endTime}")
    public TableResult<Prize> export(@PathVariable String startTime,@PathVariable String endTime){
        TableResult<Prize> prizeTableResult = new TableResult<>();
        long count = teachingresultService.selectPrizeCount().size();
        if (startTime.equals("null")&&endTime.equals("null")){
            List<Prize> dList = teachingresultService.selectPrizeCount();
            prizeTableResult.setCount(count);
            prizeTableResult.setData(dList);
        }else {
            List<Prize> dList = teachingresultService.queryByDate(startTime, endTime);
            prizeTableResult.setCount(count);
            prizeTableResult.setData(dList);
        }
        return prizeTableResult;
    }
    //根据年份统计结果的对比的导出
    @RequestMapping("/exportCountCompar/{startTime}/{endTime}")
    public TableResult<PrizeCountToArray> exportCountCompar(@PathVariable String startTime,@PathVariable String endTime){
        if (startTime.equals(time.getNowYear()) && endTime.equals(time.getNowYear())){
            startTime = time.getDateOfIndex(2);
            endTime = time.getNowYear();
        }
        TableResult<PrizeCountToArray> prizeTableResult = CountCompareCommon(startTime,endTime);
        return prizeTableResult;
    }
    //统计结果的对比
    @RequestMapping("/CountCompare")
    public TableResult<PrizeCountToArray> CountCompare(){
        String startTime = time.getDateOfIndex(2);
        String endTime = time.getNowYear();
        TableResult<PrizeCountToArray> prizeTableResult = CountCompareCommon(startTime,endTime);
        return prizeTableResult;
    }
    //根据年份统计结果的对比
    @RequestMapping("/CountCompareByDate")
    public TableResult<PrizeCountToArray> CountCompareByDate(String startTime,String endTime){
        if (startTime == "" && endTime == ""){
             startTime = time.getDateOfIndex(2);
             endTime = time.getNowYear();
        }
        TableResult<PrizeCountToArray> prizeTableResult = CountCompareCommon(startTime,endTime);
        return prizeTableResult;
    }
    //对CountCompare和CountCompareByDate相同方法的抽取
    public TableResult<PrizeCountToArray> CountCompareCommon(String startTime,String endTime){
        TableResult<PrizeCountToArray> prizeTableResult = new TableResult<>();
        List<PrizeCount> dList = teachingresultService.CountByDate(startTime,endTime);
        CountToArray countToArray = new CountToArray();
        List<PrizeCountToArray> newList = countToArray.ToArray(dList,startTime,endTime);
        prizeTableResult.setCount((long) newList.size());
        prizeTableResult.setData(newList);
        return prizeTableResult;
    }
}
