package com.fullcrum.dao;

import com.fullcrum.model.sys.PaymentEntity;

public interface PaymentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PaymentEntity record);

    int insertSelective(PaymentEntity record);

    PaymentEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaymentEntity record);

    int updateByPrimaryKey(PaymentEntity record);
}