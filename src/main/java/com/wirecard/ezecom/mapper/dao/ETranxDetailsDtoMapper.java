package com.wirecard.ezecom.mapper.dao;

import com.wirecard.ezecom.dto.ETranxDetailsDto;


public interface ETranxDetailsDtoMapper {
    int insert(ETranxDetailsDto record);

    int insertSelective(ETranxDetailsDto record);
}