package com.bigdata.store.repository;

import com.bigdata.store.entity.PhoneInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PhoneInfoRepository extends JpaRepository<PhoneInfo,Integer> {
    List<PhoneInfo> findPhoneInfoByCategoryType(Integer category);
}
