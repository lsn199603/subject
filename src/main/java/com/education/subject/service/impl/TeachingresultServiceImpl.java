package com.education.subject.service.impl;

import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.Teachingresult;
import com.education.subject.mapper.TeachingresultMapper;
import com.education.subject.service.TeachingresultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 教学成果信息表 服务实现类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@Service
public class TeachingresultServiceImpl extends ServiceImpl<TeachingresultMapper, Teachingresult> implements TeachingresultService {

    @Autowired
    public  TeachingresultMapper teachingresultMapper;

    public List<Prize> selectPrizeCount(){
        return teachingresultMapper.selectPrizrCpunt();
    }

    public List<Prize> queryByDate(String startTime,String endTime){
        return teachingresultMapper.queryByDate(startTime,endTime);
    }

    @Override
    public List<PrizeCount> CountByDate(String startTime, String endTime) {
        return teachingresultMapper.CountByDate(startTime,endTime);
    }
}
