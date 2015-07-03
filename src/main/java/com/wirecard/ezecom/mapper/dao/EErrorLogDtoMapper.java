package com.wirecard.ezecom.mapper.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.wirecard.ezecom.dto.EErrorLogDto;

@Component
public interface EErrorLogDtoMapper {
    int insert(EErrorLogDto record) throws SQLException;

    int insertSelective(EErrorLogDto record);
}