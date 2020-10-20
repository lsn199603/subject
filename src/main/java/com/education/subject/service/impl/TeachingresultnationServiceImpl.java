package com.education.subject.service.impl;

import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.Teachingresultnation;
import com.education.subject.mapper.TeachingresultnationMapper;
import com.education.subject.service.TeachingresultnationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 教学成果信息表 服务实现类
 * </p>
 *
 * @author lsn
 * @since 2019-11-03
 */
@Service
public class TeachingresultnationServiceImpl extends ServiceImpl<TeachingresultnationMapper, Teachingresultnation> implements TeachingresultnationService {

    @Autowired
    public TeachingresultnationMapper teachingresultnationMapper;

    public List<Prize> NationPrizeCount() {
        return teachingresultnationMapper.NationPrizeCount();
    }

    @Override
    public List<Prize> NationPrizeCountByDate(String startTime, String endTime) {
        return teachingresultnationMapper.NationPrizeCountByDate(startTime,endTime);
    }

    @Override
    public List<PrizeCount> NationPrizeCountCompare(String startTime, String endTime) {
        return teachingresultnationMapper.NationPrizeCountCompare(startTime,endTime);
    }
}
