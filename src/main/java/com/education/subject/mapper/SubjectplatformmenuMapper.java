package com.education.subject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.subject.entity.Subjectplatformmenu;
import com.education.subject.entity.spmTable;

import java.util.List;

/**
 * <p>
 * 学科平台目录表 Mapper 接口
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface SubjectplatformmenuMapper extends BaseMapper<Subjectplatformmenu> {

    List<spmTable> selectSpmTable();
    List<spmTable> selectSpmTableById(String nodeId);
    List<spmTable> selectSpmTableByName(String spmName);
}
