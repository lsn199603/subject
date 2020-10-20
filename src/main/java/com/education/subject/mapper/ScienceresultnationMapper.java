package com.education.subject.mapper;

import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.Scienceresultnation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lsn
 * @since 2019-11-03
 */
public interface ScienceresultnationMapper extends BaseMapper<Scienceresultnation> {
    List<Prize> NationPrizeCount();
    List<Prize> NationPrizeCountByDate(String startTime,String endTime);
    List<PrizeCount> NationPrizeCountCompare(String startTime, String endTime);
}
