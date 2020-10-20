package com.education.subject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.education.subject.Result.TableResult;
import com.education.subject.Tool.CountToArray;
import com.education.subject.Tool.Time;
import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.PrizeCountToArray;
import com.education.subject.entity.Teachingresultnation;
import com.education.subject.service.TeachingresultnationService;
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
@RequestMapping("/teachingresultnation")
public class TeachingresultnationController {
    @Autowired
    private TeachingresultnationService teachingresultnationService;
    private Time time = new Time();

    //查询全部教学成果国家级信息
    @RequestMapping("/queryAll")
    public TableResult<Teachingresultnation> queryAll(){
        TableResult<Teachingresultnation> teachingresultnationTableResult = new TableResult<>();
        QueryWrapper<Teachingresultnation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("tr_date");
        queryWrapper.orderByAsc("tr_grade");
        queryWrapper.orderByAsc("si_id");
        List<Teachingresultnation> dList = teachingresultnationService.list(queryWrapper);
        int count = teachingresultnationService.count(queryWrapper);
        teachingresultnationTableResult.setCount((long)count);
        teachingresultnationTableResult.setData(dList);
        return teachingresultnationTableResult;
    }

    //分页显示全部信息
    @RequestMapping("/list2")
    public TableResult<Teachingresultnation> list2(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        TableResult<Teachingresultnation> teachingresultnationTableResult = new TableResult<>();
        QueryWrapper<Teachingresultnation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("tr_date");
        queryWrapper.orderByAsc("tr_grade");
        queryWrapper.orderByAsc("si_id");
        List<Teachingresultnation> dList = teachingresultnationService.list(queryWrapper);
        teachingresultnationTableResult.setCount(((Page)dList).getTotal());
        teachingresultnationTableResult.setData(dList);
        return teachingresultnationTableResult;
    }

    //重载分页显示全部信息
    @RequestMapping("/list1")
    public TableResult<Teachingresultnation> list1(Integer page,Integer limit,String startTime,String endTime,String school,String dGrade) {
        PageHelper.startPage(page,limit);
        TableResult<Teachingresultnation> teachingresultnationTableResult = new TableResult<>();
        QueryWrapper<Teachingresultnation> queryWrapper = new QueryWrapper<>();
        if (startTime.equals("")&&endTime.equals("")){

        }else {
            queryWrapper.ge("tr_date", startTime).le("tr_date", endTime);
        }

        queryWrapper.like("si_name",school);
        queryWrapper.like("tr_grade",dGrade);
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("tr_date");
        queryWrapper.orderByAsc("tr_grade","si_id");
        List<Teachingresultnation> dList = teachingresultnationService.list(queryWrapper);
        teachingresultnationTableResult.setCount(((Page)dList).getTotal());
        teachingresultnationTableResult.setData(dList);
        return teachingresultnationTableResult;
    }


    //批量添加教学成果国家级信息
    @RequestMapping("/addAllDoctor")
    @ResponseBody
    public boolean addAllDoctor(@RequestBody Teachingresultnation teachingresultnation, HttpServletRequest request) {
        List<Teachingresultnation> list = teachingresultnation.getTeachingresultnationList();
        return teachingresultnationService.saveBatch(list);
    }

    //查询一条
    @RequestMapping("/getText/{id}")
    public Teachingresultnation getDoctorById(@PathVariable int id) {
        Teachingresultnation teachingresultnation = teachingresultnationService.getById(id);
        return teachingresultnation;
    }

    //伪删除一条教学成果国家级信息
    @RequestMapping("/remove/{id}")
    public boolean remove(@PathVariable int id){
        Teachingresultnation teachingresultnation = new Teachingresultnation();
        teachingresultnation.setSign(1);
        UpdateWrapper<Teachingresultnation> teachingresultnationUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        teachingresultnationUpdateWrapper.eq("id",id);
        boolean update = teachingresultnationService.update(teachingresultnation,teachingresultnationUpdateWrapper);
        return update;
    }

    //伪删除多条信息
    @RequestMapping("/removeAll/{ids}")
    public boolean removeAll(@PathVariable Integer[] ids){
        Teachingresultnation teachingresultnation = new Teachingresultnation();
        teachingresultnation.setSign(1);
        UpdateWrapper<Teachingresultnation> teachingresultnationUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        teachingresultnationUpdateWrapper.in("id",ids);
        boolean update = teachingresultnationService.update(teachingresultnation,teachingresultnationUpdateWrapper);
        return update;
    }

    //修改教学成果国家级信息
    @RequestMapping("/update")
    public boolean update(Teachingresultnation teachingresultnation){
        return teachingresultnationService.updateById(teachingresultnation);
    }

  //国家级教学成果统计
    @RequestMapping("/list")
    public TableResult<Prize> list(Integer page, Integer limit){
        PageHelper.startPage(page,100);
        TableResult<Prize> prizeCountResult = new TableResult<>();
        List<Prize> dList = teachingresultnationService.NationPrizeCount();
        prizeCountResult.setCount(((Page)dList).getTotal());
        prizeCountResult.setData(dList);
        return prizeCountResult;
    }

    //根据年时间段搜索成果统计
    @RequestMapping("/queryByDate")
    public TableResult<Prize> queryByDate(Integer page, Integer limit,String startTime,String endTime){
        PageHelper.startPage(page,100);
        TableResult<Prize> prizeCountResult = new TableResult<>();
        if (startTime.equals("")&&endTime.equals("")){
            List<Prize> dList = teachingresultnationService.NationPrizeCount();
            prizeCountResult.setCount(((Page)dList).getTotal());
            prizeCountResult.setData(dList);
        }else {
            List<Prize> dList = teachingresultnationService.NationPrizeCountByDate(startTime,endTime);

            prizeCountResult.setCount(((Page) dList).getTotal());
            prizeCountResult.setData(dList);
        }
        return prizeCountResult;
    }

    //根据高校名称与获奖等级查询统计结果详细信息
    @RequestMapping("/CountDetail/{schoolname}/{trGrade}")
    public TableResult<Teachingresultnation> CountDetail(Integer page, Integer limit, @PathVariable String schoolname, @PathVariable String trGrade){
        PageHelper.startPage(page,limit);
        TableResult<Teachingresultnation> TeachingResult = new TableResult<>();
        QueryWrapper<Teachingresultnation> queryWrapper = new QueryWrapper<>();
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
        List<Teachingresultnation> dList = teachingresultnationService.list(queryWrapper);
        TeachingResult.setCount(((Page)dList).getTotal());
        TeachingResult.setData(dList);
        return TeachingResult;
    }

    //导出教学成果统计
    //导出教学成果统计
    @RequestMapping("/export/{startTime}/{endTime}")
    public TableResult<Prize> export(@PathVariable String startTime,@PathVariable String endTime){
        TableResult<Prize> prizeTableResult = new TableResult<>();
        long count = teachingresultnationService.NationPrizeCount().size();
        if (startTime.equals("null")&&endTime.equals("null")){
            List<Prize> dList = teachingresultnationService.NationPrizeCount();
            prizeTableResult.setCount(count);
            prizeTableResult.setData(dList);
        }else {
            List<Prize> dList = teachingresultnationService.NationPrizeCountByDate(startTime,endTime);
            prizeTableResult.setCount(count);
            prizeTableResult.setData(dList);
        }
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
        List<PrizeCount> dList = teachingresultnationService.NationPrizeCountCompare(startTime,endTime);
        CountToArray countToArray = new CountToArray();
        List<PrizeCountToArray> newList = countToArray.ToArray(dList,startTime,endTime);
        prizeTableResult.setCount((long) newList.size());
        prizeTableResult.setData(newList);
        return prizeTableResult;
    }
    //根据高校名称、获奖时间与获奖等级查询统计结果详细信息
    @RequestMapping("/CountCompareDetail/{schoolname}/{time}/{trGrade}")
    public TableResult<Teachingresultnation> CountCompareDetail(Integer page, Integer limit,@PathVariable String schoolname,@PathVariable String time, @PathVariable String trGrade){
        PageHelper.startPage(page,limit);
        TableResult<Teachingresultnation> TeachingResult = new TableResult<>();
        QueryWrapper<Teachingresultnation> queryWrapper = new QueryWrapper<>();
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
        List<Teachingresultnation> dList = teachingresultnationService.list(queryWrapper);
        TeachingResult.setCount(((Page)dList).getTotal());
        TeachingResult.setData(dList);
        return TeachingResult;
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
}
