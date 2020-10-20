package com.education.subject.mapper;

import com.education.subject.entity.MasterCount;
import com.education.subject.entity.Masterinformation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 硕士点信息表 Mapper 接口
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface MasterinformationMapper extends BaseMapper<Masterinformation> {
    List<MasterCount> MasterCount();
}
