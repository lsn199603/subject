package com.education.subject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.education.subject.entity.Subjectplatformmenu;
import com.education.subject.entity.spmTable;

import java.util.List;

/**
 * <p>
 * 学科平台目录表 服务类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface SubjectplatformmenuService extends IService<Subjectplatformmenu> {



    List<spmTable> selectSpmTable();
    List<spmTable> selectSpmTableById(String nodeId);
    List<spmTable> selectSpmTableByName(String spmName);
}
