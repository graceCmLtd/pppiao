package com.fullcrum.dao;

import org.apache.ibatis.annotations.Mapper;

import com.fullcrum.model.sys.PaymentEntity;

@Mapper
public interface PaymentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PaymentEntity record);

    int insertSelective(PaymentEntity record);
    

    PaymentEntity selectByPrimaryKey(Integer id);

    PaymentEntity selectByUniqueOrderId(Integer uniqueOrderId);
    
    int updateByPrimaryKeySelective(PaymentEntity record);

    int updateByPrimaryKey(PaymentEntity record);
}