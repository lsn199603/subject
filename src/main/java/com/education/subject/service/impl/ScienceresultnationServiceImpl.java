package com.education.subject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.Scienceresultnation;
import com.education.subject.mapper.ScienceresultnationMapper;
import com.education.subject.service.ScienceresultnationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lsn
 * @since 2019-11-03
 */
@Service
public class ScienceresultnationServiceImpl extends ServiceImpl<ScienceresultnationMapper, Scienceresultnation> implements ScienceresultnationService {
    @Autowired
    public ScienceresultnationMapper scienceresultnationMapper;
    @Override
    public List<Prize> NationPrizeCount() {
        return scienceresultnationMapper.NationPrizeCount();
    }

    @Override
    public List<Prize> NationPrizeCountByDate(String startTime, String endTime) {
        return scienceresultnationMapper.NationPrizeCountByDate(startTime,endTime);
    }

    @Override
    public List<PrizeCount> NationPrizeCountCompare(String startTime, String endTime) {
        return scienceresultnationMapper.NationPrizeCountCompare(startTime,endTime);
    }
}
