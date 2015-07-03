package com.wirecard.ezecom.mapper.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.wirecard.ezecom.dto.ETranxCodeDto;

@Component
public interface ETranxCodeDtoMapper {
	
	public ETranxCodeDto getTRANXCODEByDesc(String codeDesc) throws Exception;
	
    int deleteByPrimaryKey(String tranxcode);

    int insert(ETranxCodeDto record);

    int insertSelective(ETranxCodeDto record);

    ETranxCodeDto selectByPrimaryKey(String tranxcode);

    int updateByPrimaryKeySelective(ETranxCodeDto record);

    int updateByPrimaryKey(ETranxCodeDto record);
}