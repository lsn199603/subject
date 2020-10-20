package com.education.subject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.education.subject.Result.TableResult;
import com.education.subject.entity.MasterCount;
import com.education.subject.entity.Masterinformation;
import com.education.subject.service.MasterinformationService;
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
@RequestMapping("/masterinformation")
public class MasterinformationController {
    @Autowired
    private MasterinformationService masterinformationService;

    //查询全部博士点信息
    @RequestMapping("/queryAll")
    public TableResult<Masterinformation> queryAll(){
        TableResult<Masterinformation> masterinformationTableResult = new TableResult<>();
        QueryWrapper<Masterinformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("m_time");
        queryWrapper.orderByAsc("m_grade");
        List<Masterinformation> dList = masterinformationService.list(queryWrapper);
        int count = masterinformationService.count(queryWrapper);
        masterinformationTableResult.setCount((long)count);
        masterinformationTableResult.setData(dList);
        return masterinformationTableResult;
    }

    //分页显示全部信息
    @RequestMapping("/list2")
    public TableResult<Masterinformation> list2(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        TableResult<Masterinformation> masterinformationTableResult = new TableResult<>();
        QueryWrapper<Masterinformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("m_time");
        queryWrapper.orderByAsc("m_grade");
        List<Masterinformation> dList = masterinformationService.list(queryWrapper);
        masterinformationTableResult.setCount(((Page)dList).getTotal());
        masterinformationTableResult.setData(dList);
        return masterinformationTableResult;
    }

    //重载分页显示全部信息
    @RequestMapping("/list1")
    public TableResult<Masterinformation> list1(Integer page,Integer limit,String spmmenu,String school,String mGrade) {
        PageHelper.startPage(page,limit);
        TableResult<Masterinformation> masterinformationTableResult = new TableResult<>();
        QueryWrapper<Masterinformation> queryWrapper = new QueryWrapper<>();
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
        if (mGrade.equals("")){
            queryWrapper.like("m_grade",mGrade);
        }else {
            queryWrapper.eq("m_grade",mGrade);
        }
        queryWrapper.eq("sign",0);
        queryWrapper.orderByDesc("m_time");
        queryWrapper.orderByAsc("m_grade","si_id","si_name");
        List<Masterinformation> dList = masterinformationService.list(queryWrapper);
        masterinformationTableResult.setCount(((Page)dList).getTotal());
        masterinformationTableResult.setData(dList);
        return masterinformationTableResult;
    }


    //批量添加博士点信息
    @RequestMapping("/addAllDoctor")
    @ResponseBody
    public boolean addAllDoctor(@RequestBody Masterinformation masterinformation, HttpServletRequest request) {
        List<Masterinformation> list = masterinformation.getMasterinformationList();
        return masterinformationService.saveBatch(list);
    }

    //查询一条
    @RequestMapping("/getText/{id}")
    public Masterinformation getMasterById(@PathVariable int id) {
        Masterinformation masterinformation = masterinformationService.getById(id);
        return masterinformation;
    }

    //伪删除一条博士点信息
    @RequestMapping("/remove/{id}")
    public boolean remove(@PathVariable int id){
        Masterinformation masterinformation = new Masterinformation();
        masterinformation.setSign(1);
        UpdateWrapper<Masterinformation> masterinformationUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        masterinformationUpdateWrapper.eq("id",id);
        boolean update = masterinformationService.update(masterinformation,masterinformationUpdateWrapper);
        return update;
    }

    //伪删除多条博士点信息
    @RequestMapping("/removeAll/{ids}")
    public boolean removeAll(@PathVariable Integer[] ids){
        Masterinformation masterinformation = new Masterinformation();
        masterinformation.setSign(1);
        UpdateWrapper<Masterinformation> masterinformationUpdateWrapper =new UpdateWrapper<>();
        //注意是表中属性名字
        masterinformationUpdateWrapper.in("id",ids);
        boolean update = masterinformationService.update(masterinformation,masterinformationUpdateWrapper);
        return update;
    }

    //修改博士点信息
    @RequestMapping("/update")
    public boolean update(Masterinformation masterinformation){
        return masterinformationService.updateById(masterinformation);
    }
    //硕士点信息统计
    @RequestMapping("/mastercount")
    public TableResult<MasterCount> mastercount(){
        TableResult<MasterCount> masterCountTableResult = new TableResult<>();
        List<MasterCount> dList = masterinformationService.MasterCount();
        long count = masterinformationService.MasterCount().size();
        masterCountTableResult.setCount(count);
        masterCountTableResult.setData(dList);
        return masterCountTableResult;
    }
    //根据学校名称和等级查询硕士点信息统计详情
    @RequestMapping("/CountDetail/{schoolname}/{mGrade}")
    public TableResult<Masterinformation> CountDetail(Integer page, Integer limit,@PathVariable String schoolname,@PathVariable String mGrade){
        PageHelper.startPage(page,limit);
        TableResult<Masterinformation> doctorinformationTableResult = new TableResult<>();
        QueryWrapper<Masterinformation> queryWrapper = new QueryWrapper<>();
        if (mGrade.equals("统计")){
            queryWrapper.eq("si_name",schoolname);
            queryWrapper.eq("sign",0);
            queryWrapper.orderByDesc("m_time");
        }else {
            queryWrapper.eq("si_name",schoolname);
            queryWrapper.eq("m_grade",mGrade);
            queryWrapper.eq("sign",0);
            queryWrapper.orderByDesc("m_time");
        }
        List<Masterinformation> dList = masterinformationService.list(queryWrapper);
        doctorinformationTableResult.setCount(((Page)dList).getTotal());
        doctorinformationTableResult.setData(dList);
        return doctorinformationTableResult;
    }
}
