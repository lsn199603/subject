package com.education.subject.mapper;

import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.Scienceresult;
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
public interface ScienceresultMapper extends BaseMapper<Scienceresult> {

    List<Prize> selectPrizrCpunt();
    List<Prize> queryByDate(String startTime,String endTime);
    List<PrizeCount> CountByDate(String startTime, String endTime);
}
