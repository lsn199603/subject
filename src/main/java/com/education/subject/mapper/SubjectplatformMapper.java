package com.education.subject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.subject.entity.Subjectplatform;
import com.education.subject.entity.spCount;

import java.util.List;

/**
 * <p>
 * 学科平台信息表
 Mapper 接口
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface SubjectplatformMapper extends BaseMapper<Subjectplatform> {

    List<spCount> selectSpCount();
    List<spCount> selectSpCountBySiName(String siName);
}
