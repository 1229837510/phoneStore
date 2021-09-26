package com.bigdata.store.service;

import com.bigdata.store.form.AddressForm;
import com.bigdata.store.vo.AddressVO;

import java.util.List;


public interface AddressService {
    List<AddressVO> findAll();
    void saveOrUpdate(AddressForm addressForm);
}
