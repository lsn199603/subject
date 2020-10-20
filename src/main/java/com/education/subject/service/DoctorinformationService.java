package com.education.subject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.education.subject.entity.DoctorCount;
import com.education.subject.entity.Doctorinformation;

import java.util.List;

/**
 * <p>
 * 博士点信息表 服务类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface DoctorinformationService extends IService<Doctorinformation> {

    List<DoctorCount> DoctorCount();

}
