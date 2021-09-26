package com.bigdata.store.service;

import com.bigdata.store.vo.DataVO;
import com.bigdata.store.vo.PhoneInfoVO;
import com.bigdata.store.vo.SpecsPackageVO;

import java.util.List;

public interface PhoneService {
    DataVO findDataVO();
    List<PhoneInfoVO> findByCategoryType(Integer category);
    SpecsPackageVO findSpecsByPhoneId(Integer phoneId);
    void subStock(Integer specsId,Integer quantity);
}
