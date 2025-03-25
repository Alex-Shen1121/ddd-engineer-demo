package com.codingshen.demo.infrastructure.adaptor.repository;

import com.codingshen.demo.domain.model.entity.Demo;
import com.codingshen.demo.domain.repositoy.IDemoRepository;
import com.codingshen.demo.infrastructure.converter.DemoConverter;
import com.codingshen.demo.infrastructure.dal.dao.DemoDao;
import com.codingshen.demo.infrastructure.dal.po.DemoPO;
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
