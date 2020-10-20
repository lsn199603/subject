package com.education.subject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.education.subject.Result.TableResult;
import com.education.subject.entity.DoctorCount;
import com.education.subject.entity.Doctorinformation;
import com.education.subject.service.DoctorinformationService;
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
@RequestMapping("/doctorinformation")
public class DoctorinformationController {
    @Autowired
    private DoctorinformationService doctorinformationService;

    //查询全部博士点信息
    @RequestMapping("/queryAll")
    public TableResult<Doctorinformation> queryAll(){
        TableResult<Doctorinformation> doctorinformationTableResult = new TableResult<>();
        QueryWrapper<Doctorinformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("d_time");
        queryWrapper.orderByAsc("d_grade");
        List<Doctorinformation> dList = doctorinformationService.list(queryWrapper);
        int count = doctorinformationService.count(queryWrapper);
        doctorinformationTableResult.setCount((long)count);
        doctorinformationTableResult.setData(dList);
        return doctorinformationTableResult;
    }

   //分页显示全部信息
    @RequestMapping("/list2")
    public TableResult<Doctorinformation> list2(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        TableResult<Doctorinformation> doctorinformationTableResult = new TableResult<>();
        QueryWrapper<Doctorinformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("d_time");
        queryWrapper.orderByAsc("d_grade");
        List<Doctorinformation> dList = doctorinformationService.list(queryWrapper);
        doctorinformationTableResult.setCount(((Page)dList).getTotal());
        doctorinformationTableResult.setData(dList);
        return doctorinformationTableResult;
    }

    //重载分页显示全部信息
    @RequestMapping("/list1")
    public TableResult<Doctorinformation> list1(Integer page,Integer limit,String spmmenu,String school,String dGrade) {
        PageHelper.startPage(page,limit);
        TableResult<Doctorinformation> doctorinformationTableResult = new TableResult<>();
        QueryWrapper<Doctorinformation> queryWrapper = new QueryWrapper<>();
        if (spmmenu.equals("")){

        }else if (spmmenu.equals("学科平台目录")){

        }else {
            queryWrapper.like("d_class",spmmenu);
        }
        if (school.equals("")){
            queryWrapper.like("si_name",school);
        }else {
            queryWrapper.eq("si_name",school);
        }
        if (dGrade.equals("")){
            queryWrapper.like("d_grade",dGrade);
        }else {
            queryWrapper.eq("d_grade",dGrade);
        }
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("d_time");
        queryWrapper.orderByAsc("d_grade","si_id","si_name");
        List<Doctorinformation> dList = doctorinformationService.list(queryWrapper);
        doctorinformationTableResult.setCount(((Page)dList).getTotal());
        doctorinformationTableResult.setData(dList);
        return doctorinformationTableResult;
    }


    //批量添加博士点信息
    @RequestMapping("/addAllDoctor")
    @ResponseBody
    public boolean addAllDoctor(@RequestBody Doctorinformation doctorinformation, HttpServletRequest request) {
        List<Doctorinformation> list = doctorinformation.getDoctorinformationList();
          return doctorinformationService.saveBatch(list);
    }

    //查询一条
    @RequestMapping("/getText/{id}")
    public Doctorinformation getDoctorById(@PathVariable int id) {
        Doctorinformation doctorinformation = doctorinformationService.getById(id);
        return doctorinformation;
    }

    //伪删除一条博士点信息
    @RequestMapping("/remove/{id}")
    public boolean remove(@PathVariable int id){
        Doctorinformation doctorinformation = new Doctorinformation();
        doctorinformation.setSign(1);
        UpdateWrapper<Doctorinformation> doctorinformationUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        doctorinformationUpdateWrapper.eq("id",id);
        boolean update = doctorinformationService.update(doctorinformation,doctorinformationUpdateWrapper);
        return update;
    }

    //伪删除多条博士点信息
    @RequestMapping("/removeAll/{ids}")
    public boolean removeAll(@PathVariable Integer[] ids){
        Doctorinformation doctorinformation = new Doctorinformation();
        doctorinformation.setSign(1);
        UpdateWrapper<Doctorinformation> doctorinformationUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        doctorinformationUpdateWrapper.in("id",ids);
        boolean update = doctorinformationService.update(doctorinformation,doctorinformationUpdateWrapper);
        return update;
    }

    //修改博士点信息
    @RequestMapping("/update")
    public boolean update(Doctorinformation doctorinformation){
        return doctorinformationService.updateById(doctorinformation);
    }
    //博士点信息统计
    @RequestMapping("/doctorcount")
    public TableResult<DoctorCount> doctorcount(){
        TableResult<DoctorCount> doctorCountTableResult = new TableResult<>();
        List<DoctorCount> dList = doctorinformationService.DoctorCount();
        long count = doctorinformationService.DoctorCount().size();
        doctorCountTableResult.setCount(count);
        doctorCountTableResult.setData(dList);
        return doctorCountTableResult;
    }
    //根据学校名称和等级查询博士点信息统计详情
    @RequestMapping("/CountDetail/{schoolname}/{dGrade}")
    public TableResult<Doctorinformation> CountDetail(Integer page, Integer limit,@PathVariable String schoolname,@PathVariable String dGrade){
        PageHelper.startPage(page,limit);
        TableResult<Doctorinformation> doctorinformationTableResult = new TableResult<>();
        QueryWrapper<Doctorinformation> queryWrapper = new QueryWrapper<>();
        if (dGrade.equals("统计")){
            queryWrapper.eq("si_name",schoolname);
            queryWrapper.eq("sign",0);
            queryWrapper.orderByDesc("d_time");
        }else {
            queryWrapper.eq("si_name",schoolname);
            queryWrapper.eq("d_grade",dGrade);
            queryWrapper.eq("sign",0);
            queryWrapper.orderByDesc("d_time");
        }
        List<Doctorinformation> dList = doctorinformationService.list(queryWrapper);
        doctorinformationTableResult.setCount(((Page)dList).getTotal());
        doctorinformationTableResult.setData(dList);
        return doctorinformationTableResult;
    }

}
