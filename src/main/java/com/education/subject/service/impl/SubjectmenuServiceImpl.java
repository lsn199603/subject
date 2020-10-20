package com.education.subject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.subject.entity.Subjectmenu;
import com.education.subject.entity.smTable;
import com.education.subject.mapper.SubjectmenuMapper;
import com.education.subject.service.SubjectmenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学科目录表 服务实现类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@Service
public class SubjectmenuServiceImpl extends ServiceImpl<SubjectmenuMapper, Subjectmenu> implements SubjectmenuService {
    @Autowired
    public SubjectmenuMapper subjectmenuMapper;
    public List<smTable> selectSmTable() {
        return subjectmenuMapper.selectSmTable();
    }

    public List<smTable> selectSmTableById(String nodeId) {
        return subjectmenuMapper.selectSmTableById(nodeId);
    }


    public List<smTable> selectSmTableByName(String smName) {
        return subjectmenuMapper.selectSmTableByName(smName);
    }

}
