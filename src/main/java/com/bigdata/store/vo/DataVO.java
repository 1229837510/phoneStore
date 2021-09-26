package com.bigdata.store.vo;

import lombok.Data;

import java.util.List;

@Data
public class DataVO {
    private List<PhoneCategoryVO> category;
    private List<PhoneInfoVO> phones;
}
