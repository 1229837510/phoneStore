package com.bigdata.store.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SpecsPackageVO {
    private Map<String,String> goods;
    private SkuVO sku;
}
