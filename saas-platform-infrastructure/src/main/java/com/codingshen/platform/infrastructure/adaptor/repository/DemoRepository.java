package com.codingshen.platform.infrastructure.adaptor.repository;

import com.codingshen.platform.domain.model.entity.Demo;
import com.codingshen.platform.domain.repositoy.IDemoRepository;
import com.codingshen.platform.infrastructure.converter.DemoConverter;
import com.codingshen.platform.infrastructure.dal.dao.DemoDao;
import com.codingshen.platform.infrastructure.dal.po.DemoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DemoRepository implements IDemoRepository {
    @Autowired
    private DemoDao demoDao;

    @Override
    public List<Demo> list() {
        List<DemoPO> list = demoDao.list();
        return DemoConverter.INSTANCE.pos2entities(list);
    }
}
