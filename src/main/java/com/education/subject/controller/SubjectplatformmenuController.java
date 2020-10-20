package com.education.subject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.education.subject.Result.TableResult;
import com.education.subject.Result.TreeResult;
import com.education.subject.entity.Subjectplatformmenu;
import com.education.subject.entity.spmTable;
import com.education.subject.mapper.SubjectplatformmenuMapper;
import com.education.subject.service.SubjectplatformmenuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 学科平台目录表 前端控制器
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/subjectplatformmenu")
public class SubjectplatformmenuController {
    @Autowired
    private SubjectplatformmenuService subjectplatformmenuService;

    private SubjectplatformmenuMapper subjectplatformmenuMapper;


    //查询学科平台目录树
    @RequestMapping("/queryAll")
    public TreeResult<Subjectplatformmenu> queryAll(){

        TreeResult<Subjectplatformmenu> subjectplatformmenuTreeResult = new TreeResult<>();
        QueryWrapper<Subjectplatformmenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        List<Subjectplatformmenu> dList = subjectplatformmenuService.list(queryWrapper);
        subjectplatformmenuTreeResult.setData(dList);
        subjectplatformmenuTreeResult.setMsg("操作成功");
        return subjectplatformmenuTreeResult;

    }

    //学科平台目录树添加
    @RequestMapping("/add")
    public boolean save(Subjectplatformmenu subjectplatformmenu){
        return subjectplatformmenuService.save(subjectplatformmenu);
    }

    //学科平台目录树删除
    @RequestMapping("/del/{id}")
    public Integer del(@PathVariable String id) {
        QueryWrapper<Subjectplatformmenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("spm_id",id).eq("sign",0);
        int count = subjectplatformmenuService.count(queryWrapper);
        if (id.equals("10")){
            return 1;//根节点不能删除
        }
        if (count > 1){
            return 2;//此节点有子节点不能删除
        }
        Subjectplatformmenu subjectplatformmenu = new Subjectplatformmenu();
        subjectplatformmenu.setSign(1);
        UpdateWrapper<Subjectplatformmenu> subjectplatformmenuUpdateWrapper = new UpdateWrapper<>();
        subjectplatformmenuUpdateWrapper.eq("spm_id", id);
        boolean update = subjectplatformmenuService.update(subjectplatformmenu, subjectplatformmenuUpdateWrapper);
        if (update){
            return 3;//删除成功
        }
        else{
            return 0;//删除失败
        }
    }


    //查询一条
    @RequestMapping("/getText/{id}")
    public Subjectplatformmenu getSubjectPlatfromById(@PathVariable int id){
        QueryWrapper<Subjectplatformmenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("spm_id",id);
        return subjectplatformmenuService.getOne(queryWrapper);

    }
    //学科平台目录table
    @RequestMapping("/list1")
    public TableResult<spmTable> list1(Integer page, Integer limit){
        PageHelper.startPage(page,limit);
        TableResult<spmTable> subjectplatformmenuTableResult = new TableResult<>();
        List<spmTable> dList = subjectplatformmenuService.selectSpmTable();
        subjectplatformmenuTableResult.setCount(((Page)dList).getTotal());
        subjectplatformmenuTableResult.setData(dList);
        return subjectplatformmenuTableResult;
    }

    //重载学科平台目录信息table
    @RequestMapping("/list2")
    public TableResult<spmTable> list2(Integer page,Integer limit,String nodeId){
        PageHelper.startPage(page,limit);
        TableResult<spmTable> subjectplatformmenuTableResult = new TableResult<>();
        List<spmTable> dList;
        if (nodeId.equals("10")){
            dList = subjectplatformmenuService.selectSpmTable();
        }else {
            dList = subjectplatformmenuService.selectSpmTableById(nodeId);
        }
        subjectplatformmenuTableResult.setCount(((Page)dList).getTotal());
        subjectplatformmenuTableResult.setData(dList);
        return subjectplatformmenuTableResult;
    }
    //重载学科平台目录table
    @RequestMapping("/list3")
    public TableResult<spmTable> list3(Integer page, Integer limit,String spmName){
        PageHelper.startPage(page,limit);
        TableResult<spmTable> subjectplatformmenuTableResult = new TableResult<>();
        List<spmTable> dList = subjectplatformmenuService.selectSpmTableByName(spmName);
        subjectplatformmenuTableResult.setCount(((Page)dList).getTotal());
        subjectplatformmenuTableResult.setData(dList);
        return subjectplatformmenuTableResult;
    }

    //修改学科平台目录信息
    @RequestMapping("/update")
    public boolean update(String spmId,String spmName,String spmRemark){
        UpdateWrapper<Subjectplatformmenu> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("spm_id",spmId);
        updateWrapper.set("spm_name",spmName);
        updateWrapper.set("spm_remark",spmRemark);
        return subjectplatformmenuService.update(updateWrapper);
    }

}
