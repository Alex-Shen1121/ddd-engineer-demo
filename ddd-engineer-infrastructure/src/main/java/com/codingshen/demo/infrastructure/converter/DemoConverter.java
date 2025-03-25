package com.codingshen.demo.infrastructure.converter;

import com.codingshen.demo.domain.model.entity.Demo;
import com.codingshen.demo.infrastructure.dal.po.DemoPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DemoConverter {
    DemoConverter INSTANCE = Mappers.getMapper(DemoConverter.class);

    Demo po2entity(DemoPO po);
    List<Demo> pos2entities(List<DemoPO> pos);
}
