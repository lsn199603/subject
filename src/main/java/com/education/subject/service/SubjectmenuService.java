package com.education.subject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.education.subject.entity.Subjectmenu;
import com.education.subject.entity.smTable;

import java.util.List;

/**
 * <p>
 * 学科目录表 服务类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface SubjectmenuService extends IService<Subjectmenu> {

    List<smTable> selectSmTable();
    List<smTable> selectSmTableById(String nodeId);
    List<smTable> selectSmTableByName(String smName);
}
