package com.education.subject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.education.subject.entity.Subjectplatform;
import com.education.subject.entity.spCount;

import java.util.List;

/**
 * <p>
 * 学科平台信息表
 服务类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface SubjectplatformService extends IService<Subjectplatform> {


    List<spCount> selectSpCount();
    List<spCount> selectSpCountBySiName(String siName);
}
