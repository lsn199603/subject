package com.education.subject.service.impl;

import com.education.subject.entity.MasterCount;
import com.education.subject.entity.Masterinformation;
import com.education.subject.mapper.MasterinformationMapper;
import com.education.subject.service.MasterinformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 硕士点信息表 服务实现类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@Service
public class MasterinformationServiceImpl extends ServiceImpl<MasterinformationMapper, Masterinformation> implements MasterinformationService {
    @Autowired
    private MasterinformationMapper masterinformationMapper;
    @Override
    public List<MasterCount> MasterCount() {
        return masterinformationMapper.MasterCount();
    }
}
