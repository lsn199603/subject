package com.education.subject.service;

import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.Teachingresultnation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 教学成果信息表 服务类
 * </p>
 *
 * @author lsn
 * @since 2019-11-03
 */
public interface TeachingresultnationService extends IService<Teachingresultnation> {
    List<Prize> NationPrizeCount();
    List<Prize> NationPrizeCountByDate(String startTime,String endTime);
    List<PrizeCount> NationPrizeCountCompare(String startTime, String endTime);
}
