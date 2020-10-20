package com.education.subject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.education.subject.Result.TableResult;
import com.education.subject.Result.TreeResult;
import com.education.subject.entity.Subjectmenu;
import com.education.subject.entity.smTable;
import com.education.subject.service.SubjectmenuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 学科目录表 前端控制器
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/subjectmenu")
public class SubjectmenuController {

    @Autowired
    private SubjectmenuService subjectmenuService;


    //学科目录树
    @RequestMapping("/queryAll")
    public TreeResult<Subjectmenu> queryAll(){
        TreeResult<Subjectmenu> subjectplatformmenuTreeResult = new TreeResult<>();
        QueryWrapper<Subjectmenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("sm_id","sm_parent_id","sm_name","sign");
        List<Subjectmenu> dList = subjectmenuService.list(queryWrapper);
        subjectplatformmenuTreeResult.setData(dList);
        subjectplatformmenuTreeResult.setMsg("操作成功");
        return subjectplatformmenuTreeResult;
    }

    //学科目录table
    @RequestMapping("/list1")
    public TableResult<smTable> list1(Integer page, Integer limit){
        PageHelper.startPage(page,limit);
        TableResult<smTable> subjectmenuTableResult = new TableResult<>();
        List<smTable> dList = subjectmenuService.selectSmTable();
        subjectmenuTableResult.setCount(((Page)dList).getTotal());
        subjectmenuTableResult.setData(dList);
        return subjectmenuTableResult;
    }

    //重载学科目录信息table
    @RequestMapping("/list2")
    public TableResult<smTable> list2(Integer page,Integer limit,String nodeId){
        PageHelper.startPage(page,limit);
        TableResult<smTable> subjectmenuTableResult = new TableResult<>();
        List<smTable> dList = subjectmenuService.selectSmTableById(nodeId);
        subjectmenuTableResult.setCount(((Page)dList).getTotal());
        subjectmenuTableResult.setData(dList);
        return subjectmenuTableResult;
    }
    //重载学科目录信息table
    @RequestMapping("/list3")
    public TableResult<smTable> list3(Integer page,Integer limit,String smName){
        PageHelper.startPage(page,limit);
        TableResult<smTable> subjectmenuTableResult = new TableResult<>();
        List<smTable> dList = subjectmenuService.selectSmTableByName(smName);
        subjectmenuTableResult.setCount(((Page)dList).getTotal());
        subjectmenuTableResult.setData(dList);
        return subjectmenuTableResult;
    }

    //添加学科目录信息
    @RequestMapping("/add")
    public boolean save(Subjectmenu subjectplatformmenu){
        return subjectmenuService.save(subjectplatformmenu);
    }
    //删除一条学科信息
    @RequestMapping("/del/{id}")
    public Integer del(@PathVariable String id){
        QueryWrapper<Subjectmenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("sm_id",id).eq("sign",0);
        int count = subjectmenuService.count(queryWrapper);
        if (count > 1){
            return 1;//此节点有子节点不能删除
        }
        Subjectmenu subjectmenu = new Subjectmenu();
        subjectmenu.setSign(1);
        UpdateWrapper<Subjectmenu> subjectmenuUpdateWrapper = new UpdateWrapper<>();
        subjectmenuUpdateWrapper.eq("sm_id",id);
        boolean update = subjectmenuService.update(subjectmenu,subjectmenuUpdateWrapper);
        if (update){
            return 2;//删除成功
        }
        else{
            return 0;//删除失败
        }
    }
    //查询一条学科信息
    @RequestMapping("/getText/{id}")
    public Subjectmenu getSubjectById(@PathVariable int id){
        QueryWrapper<Subjectmenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sm_id",id);
        return subjectmenuService.getOne(queryWrapper);
    }

    //修改学科目录信息
    @RequestMapping("/update")
    public boolean update(String smId,String smName,String smRemark){
        UpdateWrapper<Subjectmenu> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("sm_id",smId);
        updateWrapper.set("sm_name",smName);
        updateWrapper.set("sm_remark",smRemark);
        return subjectmenuService.update(updateWrapper);
    }

}
