package com.education.subject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.subject.entity.DoctorCount;
import com.education.subject.entity.Doctorinformation;
import com.education.subject.mapper.DoctorinformationMapper;
import com.education.subject.service.DoctorinformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 博士点信息表 服务实现类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@Service
public class DoctorinformationServiceImpl extends ServiceImpl<DoctorinformationMapper, Doctorinformation> implements DoctorinformationService {
    @Autowired
    private DoctorinformationMapper doctorInformationMapper;


    @Override
    public List<DoctorCount> DoctorCount() {
        return doctorInformationMapper.DoctorCount();
    }
}
