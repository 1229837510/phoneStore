package com.bigdata.store.service.impl;

import com.bigdata.store.service.PhoneService;
import com.bigdata.store.vo.DataVO;
import com.bigdata.store.vo.PhoneInfoVO;
import com.bigdata.store.vo.SpecsPackageVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class PhoneServiceImplTest {
    @Autowired
    private PhoneService phoneService;

    @Test
    void findDataVO() {
        DataVO dataVO = phoneService.findDataVO();
        int id = 0;
    }

    @Test
    void findByCategoryType() {
        List<PhoneInfoVO> byCategoryType = phoneService.findByCategoryType(1);
        int id = 0;
    }

    @Test
    void findBySpecsPackage(){
        SpecsPackageVO specsByPhoneId = phoneService.findSpecsByPhoneId(1);
        int id = 0;

    }
    @Test
    void subStock(){
        phoneService.subStock(1,2);
    }
}