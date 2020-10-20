package com.education.subject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.education.subject.Result.TableResult;
import com.education.subject.entity.Subjectplatform;
import com.education.subject.entity.spCount;
import com.education.subject.service.SubjectplatformService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 学科平台信息表
 前端控制器
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/subjectplatform")
public class SubjectplatformController {
    @Autowired
    private SubjectplatformService subjectplatformService;
    //学科平台信息管理表
    @RequestMapping("/list1")
    public TableResult<Subjectplatform> list1(Integer page, Integer limit){
        PageHelper.startPage(page,limit);
        TableResult<Subjectplatform> subjectplatformTableResult = new TableResult<>();
        QueryWrapper<Subjectplatform> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        List<Subjectplatform> dList = subjectplatformService.list(queryWrapper);
        subjectplatformTableResult.setCount(((Page)dList).getTotal());
        subjectplatformTableResult.setData(dList);
        return subjectplatformTableResult;
    }
    //学科平台目录树重载学科学科平台信息管理表
    @RequestMapping("/list2")
    public TableResult<Subjectplatform> list2(Integer page,Integer limit,String nodeId){
        PageHelper.startPage(page,limit);
        TableResult<Subjectplatform> subjectplatformTableResult = new TableResult<>();
        QueryWrapper<Subjectplatform> queryWrapper = new QueryWrapper<>();
        if (nodeId.equals("10")){
            queryWrapper.eq("sign",0);
        }else {
            queryWrapper.eq("sign",0);
            queryWrapper.likeRight("spm_id",nodeId);
        }
        List<Subjectplatform> dList = subjectplatformService.list(queryWrapper);
        subjectplatformTableResult.setCount(((Page)dList).getTotal());
        subjectplatformTableResult.setData(dList);
        return subjectplatformTableResult;
    }
    //导出学科平台管理的所有信息
    @RequestMapping("/queryAll")
    public TableResult<Subjectplatform> queryAll(){
        TableResult<Subjectplatform> subjectplatformTableResult = new TableResult<>();
        QueryWrapper<Subjectplatform> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        List<Subjectplatform> dList = subjectplatformService.list(queryWrapper);
        int count = subjectplatformService.count(queryWrapper);
        subjectplatformTableResult.setCount((long) count);
        subjectplatformTableResult.setData(dList);
        return subjectplatformTableResult;
    }
    //查询一条学科平台管理所有信息
    @RequestMapping("/getText/{id}")
    public Subjectplatform SubjectplatformById(@PathVariable int id){
        Subjectplatform subjectplatform = subjectplatformService.getById(id);
        return subjectplatform;
    }
    //删除一条学科平台管理信息
    @RequestMapping("/remove/{id}")
    public boolean remove(@PathVariable int id){
        Subjectplatform subjectplatform = new Subjectplatform();
        subjectplatform.setSign(1);

        UpdateWrapper<Subjectplatform> subjectplatformUpdateWrapper = new UpdateWrapper<>();
        subjectplatformUpdateWrapper.eq("id",id);
        return subjectplatformService.update(subjectplatform,subjectplatformUpdateWrapper);
    }
    //根据条件查询学科平台管理信息
    @RequestMapping("/list3")
    public TableResult<Subjectplatform> list3(Integer page, Integer limit, String spmmenu, String school) {
        PageHelper.startPage(page,limit);
        TableResult<Subjectplatform> subjectplatformTableResult = new TableResult<>();
        QueryWrapper<Subjectplatform> queryWrapper = new QueryWrapper<>();
        if (spmmenu.equals("学科平台目录")){

        }else {
            queryWrapper.like("spm_name",spmmenu);
        }
        if (school.equals("高校目录")){

        }else {
            queryWrapper.like("si_name",school);
        }

        queryWrapper.eq("sign",0);
        List<Subjectplatform> dList = subjectplatformService.list(queryWrapper);
        subjectplatformTableResult.setCount(((Page)dList).getTotal());
        subjectplatformTableResult.setData(dList);
        return subjectplatformTableResult;
    }

    //伪删除多条博士点信息
    @RequestMapping("/removeAll/{ids}")
    public boolean removeAll(@PathVariable Integer[] ids){
        Subjectplatform subjectplatform = new Subjectplatform();
        subjectplatform.setSign(1);
        UpdateWrapper<Subjectplatform> subjectplatformUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        subjectplatformUpdateWrapper.in("id",ids);
        boolean update = subjectplatformService.update(subjectplatform,subjectplatformUpdateWrapper);
        return update;
    }

    //修改博士点信息
    @RequestMapping("/update")
    public boolean update(Subjectplatform subjectplatform){
        return subjectplatformService.updateById(subjectplatform);
    }

    //批量添加学科平台信息
    @RequestMapping("/addAllDoctor")
    @ResponseBody
    public boolean addAllDoctor(@RequestBody Subjectplatform subjectplatform, HttpServletRequest request) {
        List<Subjectplatform> list = subjectplatform.getSubjectplatformList();
        return subjectplatformService.saveBatch(list);
    }
    //学科平台信息统计
    @RequestMapping("/spCount")
    public TableResult<spCount> spCount(Integer page,Integer limit){
        PageHelper.startPage(page,100);
        TableResult<spCount> spCountTableResult = new TableResult<>();
        List<spCount> dList = subjectplatformService.selectSpCount();
        spCountTableResult.setCount(((Page)dList).getTotal());
        spCountTableResult.setData(dList);
        return spCountTableResult;
    }
    //学科平台信息统计按高校名称搜索
    @RequestMapping("/spCountBysiName")
    public TableResult<spCount> spCountBysiName(Integer page, Integer limit, String siName){
        PageHelper.startPage(page,limit);
        TableResult<spCount> spCountTableResult = new TableResult<>();
        List<spCount> dList = subjectplatformService.selectSpCountBySiName(siName);
        spCountTableResult.setCount(((Page)dList).getTotal());
        spCountTableResult.setData(dList);
        return spCountTableResult;
    }
    //学科平台信息统计详情
    @RequestMapping("/spCountDetail/{schoolname}/{spmId}")
    public TableResult<Subjectplatform> spCountDetail(Integer page,Integer limit,@PathVariable String schoolname,@PathVariable String  spmId){
        PageHelper.startPage(page,limit);
        TableResult<Subjectplatform> subjectplatformTableResult = new TableResult<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("si_name",schoolname);
        queryWrapper.eq("spm_id",spmId);
        queryWrapper.eq("sign",0);
        List<Subjectplatform> dList = subjectplatformService.list(queryWrapper);
        subjectplatformTableResult.setData(dList);
        subjectplatformTableResult.setCount(((Page)dList).getTotal());
        return subjectplatformTableResult;
    }

    //导出学科平台信息统计表
    @RequestMapping("/export")
    public TableResult<spCount> export(){
        TableResult<spCount> spCountTableResult = new TableResult<>();
        List<spCount> dList = subjectplatformService.selectSpCount();
        long count = subjectplatformService.selectSpCount().size();
        spCountTableResult.setCount(count);
        spCountTableResult.setData(dList);
        return spCountTableResult;
    }
}
