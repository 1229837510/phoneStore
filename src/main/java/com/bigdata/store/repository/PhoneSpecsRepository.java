package com.bigdata.store.repository;

import com.bigdata.store.entity.PhoneSpecs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneSpecsRepository extends JpaRepository<PhoneSpecs,Integer> {
     List<PhoneSpecs> findAllByPhoneId(Integer phoneId);
}
