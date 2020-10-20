package com.education.subject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.subject.entity.DoctorCount;
import com.education.subject.entity.Doctorinformation;

import java.util.List;

/**
 * <p>
 * 博士点信息表 Mapper 接口
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface DoctorinformationMapper extends BaseMapper<Doctorinformation> {
    List<DoctorCount> DoctorCount();


}
