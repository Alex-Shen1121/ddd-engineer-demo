package com.codingshen.demo.trigger.converter;

import com.codingshen.platform.domain.model.entity.Demo;
import com.codingshen.demo.trigger.model.dto.DemoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DemoApiConverter {
    DemoApiConverter INSTANCE = Mappers.getMapper(DemoApiConverter.class);

    DemoDTO entity2DTO(Demo po);
    List<DemoDTO> entities2DTOS(List<Demo> pos);
}
