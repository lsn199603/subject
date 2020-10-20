package com.education.subject.service;

import com.education.subject.entity.MasterCount;
import com.education.subject.entity.Masterinformation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 硕士点信息表 服务类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
public interface MasterinformationService extends IService<Masterinformation> {

    List<MasterCount> MasterCount();
}
