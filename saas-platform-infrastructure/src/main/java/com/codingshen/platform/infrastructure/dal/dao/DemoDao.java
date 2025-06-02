package com.codingshen.platform.infrastructure.dal.dao;

import com.codingshen.platform.infrastructure.dal.po.DemoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoDao {
    List<DemoPO> list();
}
