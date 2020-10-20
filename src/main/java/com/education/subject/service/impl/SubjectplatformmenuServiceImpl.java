package com.education.subject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.subject.entity.Subjectplatformmenu;
import com.education.subject.entity.spmTable;
import com.education.subject.mapper.SubjectplatformmenuMapper;
import com.education.subject.service.SubjectplatformmenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学科平台目录表 服务实现类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@Service
public class SubjectplatformmenuServiceImpl extends ServiceImpl<SubjectplatformmenuMapper, Subjectplatformmenu> implements SubjectplatformmenuService {
    @Autowired
    public SubjectplatformmenuMapper subjectplatformmenuMapper;

    public List<spmTable> selectSpmTable(){
        return subjectplatformmenuMapper.selectSpmTable();
    }


    public List<spmTable> selectSpmTableById(String nodeId) {
        return subjectplatformmenuMapper.selectSpmTableById(nodeId);
    }


    public List<spmTable> selectSpmTableByName(String spmName) {
        return subjectplatformmenuMapper.selectSpmTableByName(spmName);
    }
}
