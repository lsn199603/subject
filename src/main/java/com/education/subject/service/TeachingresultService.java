package com.education.subject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.education.subject.entity.Prize;
import com.education.subject.entity.PrizeCount;
import com.education.subject.entity.Teachingresult;

import java.util.List;

/**
 * <p>
 * 教学成果信息表 服务类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface TeachingresultService extends IService<Teachingresult> {

    List<Prize> selectPrizeCount();
    List<Prize> queryByDate(String startTime,String endTime);
    List<PrizeCount> CountByDate(String startTime, String endTime);
}
