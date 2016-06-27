package com.yixin.js.common.dao;

import com.yixin.js.common.model.SysLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 16/6/6.
 */
@Repository
public interface SysLogRepository extends JpaRepository<SysLogEntity,Long> {





}
