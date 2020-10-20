package com.education.subject.service;

import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.Scienceresultnation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lsn
 * @since 2019-11-03
 */
public interface ScienceresultnationService extends IService<Scienceresultnation> {
    List<Prize> NationPrizeCount();
    List<Prize> NationPrizeCountByDate(String startTime,String endTime);
    List<PrizeCount> NationPrizeCountCompare(String startTime, String endTime);
}
