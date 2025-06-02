package com.codingshen.platform.infrastructure.converter;

import com.codingshen.platform.domain.model.entity.Demo;
import com.codingshen.platform.infrastructure.dal.po.DemoPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DemoConverter {
    DemoConverter INSTANCE = Mappers.getMapper(DemoConverter.class);

    Demo po2entity(DemoPO po);
    List<Demo> pos2entities(List<DemoPO> pos);
}
