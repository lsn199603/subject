package com.education.subject.mapper;

import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.Teachingresultnation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 教学成果信息表 Mapper 接口
 * </p>
 *
 * @author lsn
 * @since 2019-11-03
 */
public interface TeachingresultnationMapper extends BaseMapper<Teachingresultnation> {
    List<Prize> NationPrizeCount();
    List<Prize> NationPrizeCountByDate(String startTime,String endTime);
    List<PrizeCount> NationPrizeCountCompare(String startTime, String endTime);
}
