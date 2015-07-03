package com.wirecard.ezecom.mapper.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.wirecard.ezecom.dto.ETranxLogDto;
@Component
public interface ETranxLogDtoMapper {
	
	List<ETranxLogDto> isUniqueTransaction(@Param("merchantNo") String merchantNo,@Param("merchantTranxRefNo") String merchantTranxRefNo,@Param("orderNo") String orderNo) throws SQLException;
	
	ETranxLogDto isUniqueMerchantRefNUmber(@Param("merchantNo") String merchantNo,@Param("merchantTranxRefNo") String merchantTranxRefNo) throws SQLException;

	
    int insert(ETranxLogDto record);

    int insertSelective(ETranxLogDto record);
    
    ETranxLogDto selectTransaction(@Param("merchantNo") String merchantNo,@Param("merchantTranxRefNo")String merchantTranxRefNo,@Param("orderNo") String orderNo,@Param("amount") double amount,@Param("tranxStatus") String tranxStatus) throws SQLException;
    ETranxLogDto checkTransactionStatus(@Param("merchantNo") String merchantNo,@Param("merchantTranxRefNo")String merchantTranxRefNo,@Param("orderNo") String orderNo,@Param("amount") double amount) throws SQLException;
    int updateTranxStatus(ETranxLogDto record);
}