package com.wirecard.ezecom.mapper.dao;

import java.sql.SQLException;

import com.wirecard.ezecom.dto.EMerchantDetailsDto;
import com.wirecard.ezecom.dto.EMerchantDetailsDtoExample;

import org.apache.ibatis.annotations.Param;

public interface EMerchantDetailsDtoMapper {
	public EMerchantDetailsDto getMerchantByMerchantId(String merchantNo) throws SQLException;
	
    int insert(EMerchantDetailsDto record);

    int insertSelective(EMerchantDetailsDto record);

    int updateByExampleSelective(@Param("record") EMerchantDetailsDto record, @Param("example") EMerchantDetailsDtoExample example);

    int updateByExample(@Param("record") EMerchantDetailsDto record, @Param("example") EMerchantDetailsDtoExample example);
}