package com.fullcrum.dao;

import org.apache.ibatis.annotations.Mapper;

import com.fullcrum.model.sys.PaymentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PaymentEntity record);

    int insertSelective(PaymentEntity record);

    PaymentEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaymentEntity record);

    int updateByPrimaryKey(PaymentEntity record);

    PaymentEntity selectByUniqueOrderId(Integer uniqueOrderNo);
    PaymentEntity selectByTxId(String txId);
}