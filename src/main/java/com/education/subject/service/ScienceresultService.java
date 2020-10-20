package com.education.subject.service;

import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.Scienceresult;
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
public interface ScienceresultService extends IService<Scienceresult> {
    List<Prize> selectPrizrCpunt();
    List<Prize> queryByDate(String startTime,String endTime);
    List<PrizeCount> CountByDate(String startTime, String endTime);
}
