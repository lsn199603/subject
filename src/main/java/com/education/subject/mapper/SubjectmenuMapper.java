package com.education.subject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.subject.entity.Subjectmenu;
import com.education.subject.entity.smTable;

import java.util.List;

/**
 * <p>
 * 学科目录表 Mapper 接口
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface SubjectmenuMapper extends BaseMapper<Subjectmenu> {

    List<smTable> selectSmTable();
    List<smTable> selectSmTableById(String nodeId);
    List<smTable> selectSmTableByName(String smName);
}
