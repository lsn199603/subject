package com.education.subject.mapper;

import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.Teachingresult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 教学成果信息表 Mapper 接口
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface TeachingresultMapper extends BaseMapper<Teachingresult> {

    List<Prize> selectPrizrCpunt();
    List<Prize> queryByDate(String startTime,String endTime);
    List<PrizeCount> CountByDate(String startTime, String endTime);
}
