package com.yixin.js.common.service.impl;

import com.yixin.js.common.dao.SysLogRepository;
import com.yixin.js.common.model.SysLogEntity;
import com.yixin.js.common.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by apple on 16/3/25.
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogRepository sysLogRepository;

    @Override
    public void save(SysLogEntity sysLogEntity) {
        sysLogRepository.save(sysLogEntity);
    }
}
