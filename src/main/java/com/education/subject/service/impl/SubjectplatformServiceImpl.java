package com.education.subject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.subject.entity.Subjectplatform;
import com.education.subject.entity.spCount;
import com.education.subject.mapper.SubjectplatformMapper;
import com.education.subject.service.SubjectplatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学科平台信息表
 服务实现类
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@Service
public class SubjectplatformServiceImpl extends ServiceImpl<SubjectplatformMapper, Subjectplatform> implements SubjectplatformService {
    @Autowired
    public SubjectplatformMapper subjectplatformMapper;

    public List<spCount> selectSpCount() {
        return subjectplatformMapper.selectSpCount();
    }


    public List<spCount> selectSpCountBySiName(String siName) {
        return subjectplatformMapper.selectSpCountBySiName(siName);
    }
}
