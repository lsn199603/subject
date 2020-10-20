package com.education.subject.service.impl;

import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.Scienceresult;
import com.education.subject.mapper.ScienceresultMapper;
import com.education.subject.service.ScienceresultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ScienceresultServiceImpl extends ServiceImpl<ScienceresultMapper, Scienceresult> implements ScienceresultService {
    @Autowired
    public ScienceresultMapper scienceresultMapper;
    @Override
    public List<Prize> selectPrizrCpunt() {
        return scienceresultMapper.selectPrizrCpunt();
    }

    @Override
    public List<Prize> queryByDate(String startTime, String endTime) {
        return scienceresultMapper.queryByDate(startTime,endTime);
    }

    @Override
    public List<PrizeCount> CountByDate(String startTime, String endTime) {
        return scienceresultMapper.CountByDate(startTime,endTime);
    }
}
