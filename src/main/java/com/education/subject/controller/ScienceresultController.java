package com.education.subject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.education.subject.Result.TableResult;
import com.education.subject.Tool.CountToArray;
import com.education.subject.Tool.Time;
import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.PrizeCountToArray;
import com.education.subject.entity.Scienceresult;
import com.education.subject.service.ScienceresultService;
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
@RequestMapping("/scienceresult")
public class ScienceresultController {
    @Autowired
    private ScienceresultService scienceresultService;
    private Time time = new Time();
    //查询全部科技奖信息
    @RequestMapping("/queryAll")
    public TableResult<Scienceresult> queryAll(){
        TableResult<Scienceresult> scienceresultTableResult = new TableResult<>();
        QueryWrapper<Scienceresult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("st_time");
        queryWrapper.orderByAsc("st_grade");
       /* queryWrapper.orderByAsc("si_id");*/
        List<Scienceresult> dList = scienceresultService.list(queryWrapper);
        int count = scienceresultService.count(queryWrapper);
        scienceresultTableResult.setCount((long)count);
        scienceresultTableResult.setData(dList);
        return scienceresultTableResult;
    }

    //分页显示全部信息
    @RequestMapping("/list2")
    public TableResult<Scienceresult> list2(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        TableResult<Scienceresult> scienceresultTableResult = new TableResult<>();
        QueryWrapper<Scienceresult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("st_time");
        queryWrapper.orderByAsc("st_grade");
        //queryWrapper.orderByAsc("si_id");
        List<Scienceresult> dList = scienceresultService.list(queryWrapper);
        scienceresultTableResult.setCount(((Page)dList).getTotal());
        scienceresultTableResult.setData(dList);
        return scienceresultTableResult;
    }

    //重载分页显示全部信息
    @RequestMapping("/list1")
    public TableResult<Scienceresult> list1(Integer page,Integer limit,String startTime,String endTime,String school,String dGrade) {
        PageHelper.startPage(page,limit);
        TableResult<Scienceresult> scienceresultTableResult = new TableResult<>();
        QueryWrapper<Scienceresult> queryWrapper = new QueryWrapper<>();
        if (startTime.equals("")&&endTime.equals("")){

        }else {
            queryWrapper.ge("st_time", startTime).le("st_time", endTime);
        }

        queryWrapper.like("si_name",school);
        queryWrapper.like("st_grade",dGrade);
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("st_time");
        queryWrapper.orderByAsc("st_grade");
        List<Scienceresult> dList = scienceresultService.list(queryWrapper);
        scienceresultTableResult.setCount(((Page)dList).getTotal());
        scienceresultTableResult.setData(dList);
        return scienceresultTableResult;
    }


    //批量添加科技奖信息
    @RequestMapping("/addAllDoctor")
    @ResponseBody
    public boolean addAllDoctor(@RequestBody Scienceresult scienceresult, HttpServletRequest request) {
        List<Scienceresult> list = scienceresult.getScienceresultList();
        return scienceresultService.saveBatch(list);
    }

    //查询一条
    @RequestMapping("/getText/{id}")
    public Scienceresult getDoctorById(@PathVariable int id) {
        Scienceresult scienceresult = scienceresultService.getById(id);
        return scienceresult;
    }

    //伪删除一条科技奖信息
    @RequestMapping("/remove/{id}")
    public boolean remove(@PathVariable int id){
        Scienceresult scienceresult = new Scienceresult();
        scienceresult.setSign(1);
        UpdateWrapper<Scienceresult> scienceresultUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        scienceresultUpdateWrapper.eq("id",id);
        boolean update = scienceresultService.update(scienceresult,scienceresultUpdateWrapper);
        return update;
    }

    //伪删除多条科技奖信息
    @RequestMapping("/removeAll/{ids}")
    public boolean removeAll(@PathVariable Integer[] ids){
        Scienceresult scienceresult = new Scienceresult();
        scienceresult.setSign(1);
        UpdateWrapper<Scienceresult> scienceresultUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        scienceresultUpdateWrapper.in("id",ids);
        boolean update = scienceresultService.update(scienceresult,scienceresultUpdateWrapper);
        return update;
    }

    //修改科技奖信息
    @RequestMapping("/update")
    public boolean update(Scienceresult scienceresult){
        return scienceresultService.updateById(scienceresult);
    }

    //教学成果统计
    @RequestMapping("/list")
    public TableResult<Prize> list(Integer page, Integer limit){
        PageHelper.startPage(page,100);
        TableResult<Prize> prizeCountResult = new TableResult<>();
        List<Prize> dList = scienceresultService.selectPrizrCpunt();
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
            List<Prize> dList = scienceresultService.selectPrizrCpunt();
            prizeCountResult.setCount(((Page)dList).getTotal());
            prizeCountResult.setData(dList);
        }else {
            List<Prize> dList = scienceresultService.queryByDate(startTime,endTime);

            prizeCountResult.setCount(((Page) dList).getTotal());
            prizeCountResult.setData(dList);
        }
        return prizeCountResult;
    }

   //根据高校名称与获奖等级查询统计结果详细信息
    @RequestMapping("/CountDetail/{schoolname}/{trGrade}")
    public TableResult<Scienceresult> CountDetail(Integer page, Integer limit,@PathVariable String schoolname,@PathVariable String trGrade){
        PageHelper.startPage(page,limit);
        TableResult<Scienceresult> scienceresultTableResult = new TableResult<>();
        QueryWrapper<Scienceresult> queryWrapper = new QueryWrapper<>();
        if (trGrade.equals("统计")){
            queryWrapper.eq("si_name",schoolname);
            queryWrapper.eq("sign",0);
            queryWrapper.orderByDesc("st_time");
        }else {
            queryWrapper.eq("si_name",schoolname);
            queryWrapper.eq("st_grade",trGrade);
            queryWrapper.eq("sign",0);
            queryWrapper.orderByDesc("st_time");
        }
        List<Scienceresult> dList = scienceresultService.list(queryWrapper);
        scienceresultTableResult.setCount(((Page)dList).getTotal());
        scienceresultTableResult.setData(dList);
        return scienceresultTableResult;
    }

    //导出教学成果统计
    @RequestMapping("/export/{startTime}/{endTime}")
    public TableResult<Prize> export(@PathVariable String startTime,@PathVariable String endTime){
        TableResult<Prize> prizeTableResult = new TableResult<>();
        long count = scienceresultService.selectPrizrCpunt().size();
        if (startTime.equals("null")&&endTime.equals("null")){
            List<Prize> dList = scienceresultService.selectPrizrCpunt();
            prizeTableResult.setCount(count);
            prizeTableResult.setData(dList);
        }else {
            List<Prize> dList = scienceresultService.queryByDate(startTime,endTime);
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
        List<PrizeCount> dList = scienceresultService.CountByDate(startTime,endTime);
        CountToArray countToArray = new CountToArray();
        List<PrizeCountToArray> newList = countToArray.ToArray(dList,startTime,endTime);
        prizeTableResult.setCount((long) newList.size());
        prizeTableResult.setData(newList);
        return prizeTableResult;
    }
    //根据高校名称、获奖时间与获奖等级查询统计结果详细信息
    @RequestMapping("/CountCompareDetail/{schoolname}/{time}/{trGrade}")
    public TableResult<Scienceresult> CountCompareDetail(Integer page, Integer limit,@PathVariable String schoolname,@PathVariable String time, @PathVariable String trGrade){
        PageHelper.startPage(page,limit);
        TableResult<Scienceresult> scienceresultnationTableResult = new TableResult<>();
        QueryWrapper<Scienceresult> queryWrapper = new QueryWrapper<>();
        if (trGrade.equals("统计")){
            String [] date= time.split(",");
            queryWrapper.between("st_time",date[0],date[2]);
            queryWrapper.eq("si_name",schoolname);
            queryWrapper.eq("sign",0);
            queryWrapper.orderByDesc("st_time");
        }else {
            queryWrapper.eq("si_name",schoolname);
            queryWrapper.eq("st_time",time);
            queryWrapper.eq("st_grade",trGrade);
            queryWrapper.eq("sign",0);
        }
        List<Scienceresult> dList = scienceresultService.list(queryWrapper);
        scienceresultnationTableResult.setCount(((Page)dList).getTotal());
        scienceresultnationTableResult.setData(dList);
        return scienceresultnationTableResult;
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
