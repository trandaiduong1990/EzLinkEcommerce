package com.wirecard.ezecom.mapper.dao;

import com.wirecard.ezecom.dto.EBatchLogDto;

public interface EBatchLogDtoMapper {
    int deleteByPrimaryKey(String sno);

    int insert(EBatchLogDto record);

    int insertSelective(EBatchLogDto record);

    EBatchLogDto selectByPrimaryKey(String sno);

    int updateByPrimaryKeySelective(EBatchLogDto record);

    int updateByPrimaryKey(EBatchLogDto record);
}