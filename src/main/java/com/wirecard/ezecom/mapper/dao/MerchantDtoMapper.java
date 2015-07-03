package com.wirecard.ezecom.mapper.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wirecard.ezecom.dto.MerchantDto;

@Component
public interface MerchantDtoMapper {
	public MerchantDto getMCCByMerchantId(String merchantNo) throws SQLException;
	
    int deleteByPrimaryKey(String merchantNo);

    int insert(MerchantDto record);

    int insertSelective(MerchantDto record);

    MerchantDto selectByPrimaryKey(String merchantNo);

    int updateByPrimaryKeySelective(MerchantDto record);

    int updateByPrimaryKey(MerchantDto record);
    
    public List<MerchantDto> getMerchant();
}