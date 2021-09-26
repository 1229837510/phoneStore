package com.bigdata.store.repository;

import com.bigdata.store.entity.PhoneCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class PhoneStoreRepositoryTest {

    @Autowired
    private PhoneCategoryRepository repository;

    @Test
    void findAll() {
        List<PhoneCategory> list = repository.findAll();
        for (PhoneCategory phoneCategory : list) {
            System.out.println(phoneCategory);
        }
    }
}