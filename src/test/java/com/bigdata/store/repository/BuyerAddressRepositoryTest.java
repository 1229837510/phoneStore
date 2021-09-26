package com.bigdata.store.repository;

import com.bigdata.store.entity.BuyerAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyerAddressRepositoryTest {
    @Autowired
    private BuyerAddressRepository buyerAddressRepository;
    @Test
    void findAll(){
        List<BuyerAddress> all = buyerAddressRepository.findAll();
        System.out.println(all);
    }
}