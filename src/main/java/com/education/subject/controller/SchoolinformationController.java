package com.education.subject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.education.subject.Result.TableResult;
import com.education.subject.entity.Schoolinformation;
import com.education.subject.service.SchoolinformationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 高校信息表 前端控制器
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/schoolinformation")
public class SchoolinformationController {
    @Autowired
    private SchoolinformationService schoolinformationService;
    //查询全部高校名称与代码
    @RequestMapping("/querySchoolName")
    public List<Schoolinformation> querySchoolName(){
        QueryWrapper<Schoolinformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,si_id,si_name");
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("si_id,si_name");
        List<Schoolinformation> dList = schoolinformationService.list(queryWrapper);
        return dList;
    }

    //查询全部博士点信息
    @RequestMapping("/queryAll")
    public TableResult<Schoolinformation> queryAll(){
        TableResult<Schoolinformation> schoolinformationTableResult = new TableResult<>();
        QueryWrapper<Schoolinformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        List<Schoolinformation> dList = schoolinformationService.list(queryWrapper);
        int count = schoolinformationService.count(queryWrapper);
        schoolinformationTableResult.setCount((long)count);
        schoolinformationTableResult.setData(dList);
        return schoolinformationTableResult;
    }

    //分页显示全部信息
    @RequestMapping("/list2")
    public TableResult<Schoolinformation> list2(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);

        TableResult<Schoolinformation> schoolinformationTableResult = new TableResult<>();

        QueryWrapper<Schoolinformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);

        List<Schoolinformation> dList = schoolinformationService.list(queryWrapper);

        schoolinformationTableResult.setCount(((Page)dList).getTotal());
        schoolinformationTableResult.setData(dList);
        return schoolinformationTableResult;
    }

    //重载分页显示全部信息
    @RequestMapping("/list1")
    public TableResult<Schoolinformation> list1(Integer page,Integer limit,String siName) {
        PageHelper.startPage(page,limit);
        TableResult<Schoolinformation> schoolinformationTableResult = new TableResult<>();
        QueryWrapper<Schoolinformation> queryWrapper = new QueryWrapper<>();

        if (siName.equals("")){

        }else {
            queryWrapper.like("si_name",siName);
        }
        queryWrapper.eq("sign",0);
        List<Schoolinformation> dList = schoolinformationService.list(queryWrapper);
        schoolinformationTableResult.setCount(((Page)dList).getTotal());
        schoolinformationTableResult.setData(dList);
        return schoolinformationTableResult;
    }


    //批量添加博士点信息
    @RequestMapping("/addAllDoctor")
    @ResponseBody
    public boolean addAllDoctor(@RequestBody Schoolinformation schoolinformation, HttpServletRequest request) {
        List<Schoolinformation> list = schoolinformation.getSchoolinformationList();
        return schoolinformationService.saveBatch(list);
    }

    //查询一条
    @RequestMapping("/getText/{id}")
    public Schoolinformation getDoctorById(@PathVariable int id) {
        Schoolinformation schoolinformation = schoolinformationService.getById(id);
        return schoolinformation;
    }

    //伪删除一条博士点信息
    @RequestMapping("/remove/{id}")
    public boolean remove(@PathVariable int id){
        Schoolinformation schoolinformation = new Schoolinformation();
        schoolinformation.setSign(1);
        UpdateWrapper<Schoolinformation> schoolinformationUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        schoolinformationUpdateWrapper.eq("id",id);
        boolean update = schoolinformationService.update(schoolinformation,schoolinformationUpdateWrapper);
        return update;
    }

    //伪删除多条博士点信息
    @RequestMapping("/removeAll/{ids}")
    public boolean removeAll(@PathVariable Integer[] ids){
        Schoolinformation schoolinformation = new Schoolinformation();
        schoolinformation.setSign(1);
        UpdateWrapper<Schoolinformation> schoolinformationUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        schoolinformationUpdateWrapper.in("id",ids);
        boolean update = schoolinformationService.update(schoolinformation,schoolinformationUpdateWrapper);
        return update;
    }

    //修改博士点信息
    @RequestMapping("/update")
    public boolean update(Schoolinformation schoolinformation){
        return schoolinformationService.updateById(schoolinformation);
    }

}
