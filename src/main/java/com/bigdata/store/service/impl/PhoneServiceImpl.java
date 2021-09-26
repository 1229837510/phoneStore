package com.bigdata.store.service.impl;

import com.bigdata.store.entity.PhoneCategory;
import com.bigdata.store.entity.PhoneInfo;
import com.bigdata.store.entity.PhoneSpecs;
import com.bigdata.store.util.PhoneUtil;
import com.bigdata.store.enums.ResultEnum;
import com.bigdata.store.exception.PhoneException;
import com.bigdata.store.repository.PhoneCategoryRepository;
import com.bigdata.store.repository.PhoneInfoRepository;
import com.bigdata.store.repository.PhoneSpecsRepository;
import com.bigdata.store.service.PhoneService;
import com.bigdata.store.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneCategoryRepository repository;
    @Autowired
    private PhoneInfoRepository infoRepository;
    @Autowired
    private PhoneSpecsRepository specsRepository;

    @Override
    public DataVO findDataVO() {
        DataVO dataVO = new DataVO();
        //类型
        List<PhoneCategory> phoneCategoryList = repository.findAll();
        //常规写法
//        List<PhoneCategoryVO> phoneCategoryVOList = new ArrayList<>();
//        for (PhoneCategory phoneCategory : phoneCategoryList) {
//            PhoneCategoryVO phoneCategoryVO = new PhoneCategoryVO();
//            phoneCategoryVO.setCategoryName(phoneCategory.getCategoryName());
//            phoneCategoryVO.setCategoryType(phoneCategory.getCategoryType());
//            phoneCategoryVOList.add(phoneCategoryVO);
//        }
        //stream写法
        List<PhoneCategoryVO> phoneCategoryVOList = phoneCategoryList.stream()
                .map(e -> new PhoneCategoryVO(
                        e.getCategoryName(),
                        e.getCategoryType()
                )).collect(Collectors.toList());
        dataVO.setCategory(phoneCategoryVOList);
        //手机
        List<PhoneInfo> phoneInfoList = infoRepository.findPhoneInfoByCategoryType(phoneCategoryList.get(0).getCategoryType());
//        List<PhoneInfoVO> phoneInfoVOList = new ArrayList<>();
//        PhoneInfoVO phoneInfoVO;
//        for (PhoneInfo phoneInfo : phoneInfoList) {
//            phoneInfoVO = new PhoneInfoVO();
//            BeanUtils.copyProperties(phoneInfo,phoneInfoVO);
//            phoneInfoVO.setTag(PhoneUtil.createTag(phoneInfo.getPhoneTag()));
//            phoneInfoVOList.add(phoneInfoVO);
//        }

        List<PhoneInfoVO> phoneInfoVOList = phoneInfoList.stream()
                .map(s -> new PhoneInfoVO(
                        s.getPhoneId(),
                        s.getPhoneName(),
                        s.getPhonePrice() + ".00",
                        s.getPhoneDescription(),
                        PhoneUtil.createTag(s.getPhoneTag()),
                        s.getPhoneIcon()
                )).collect(Collectors.toList());
        dataVO.setPhones(phoneInfoVOList);
        return dataVO;
    }

    @Override
    public List<PhoneInfoVO> findByCategoryType(Integer category) {
        List<PhoneInfo> phoneInfoList = infoRepository.findPhoneInfoByCategoryType(category);
        List<PhoneInfoVO> phoneInfoVOList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice() + ".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());
        return phoneInfoVOList;
    }

    @Override
    public SpecsPackageVO findSpecsByPhoneId(Integer phoneId) {
        PhoneInfo phoneInfo = infoRepository.findById(phoneId).get();
        List<PhoneSpecs> phoneSpecsList = specsRepository.findAllByPhoneId(phoneId);
        //tree
        List<PhoneSpecsVO> phoneSpecsVOList = new ArrayList<>();
        List<PhoneSpecsCasVO> phoneSpecsCasVOList = new ArrayList<>();
        PhoneSpecsVO phoneSpecsVO;
        PhoneSpecsCasVO phoneSpecsCasVO;
        for (PhoneSpecs phoneSpecs : phoneSpecsList) {
            phoneSpecsVO = new PhoneSpecsVO();
            phoneSpecsCasVO = new PhoneSpecsCasVO();
            BeanUtils.copyProperties(phoneSpecs, phoneSpecsVO);
            BeanUtils.copyProperties(phoneSpecs, phoneSpecsCasVO);
            phoneSpecsVOList.add(phoneSpecsVO);
            phoneSpecsCasVOList.add(phoneSpecsCasVO);
        }

        TreeVO treeVO = new TreeVO();
        treeVO.setV(phoneSpecsVOList);
        List<TreeVO> treeVOSList = new ArrayList<>();
        treeVOSList.add(treeVO);
        //sku
        SkuVO skuVO = new SkuVO();
        skuVO.setList(phoneSpecsCasVOList);
        Integer price = phoneInfo.getPhonePrice().intValue();
        skuVO.setStock_num(phoneInfo.getPhoneStock());
        skuVO.setPrice(price + ".00");
        skuVO.setTree(treeVOSList);

        //specsPackage
        SpecsPackageVO specsPackageVO = new SpecsPackageVO();
        Map<String, String> goods = new HashMap<>();
        goods.put("picture", phoneInfo.getPhoneIcon());
        specsPackageVO.setSku(skuVO);
        specsPackageVO.setGoods(goods);
        return specsPackageVO;
    }

    @Override
    public void subStock(Integer specsId, Integer quantity) {
        PhoneSpecs phoneSpecs = specsRepository.findById(specsId).get();
        PhoneInfo phoneInfo = infoRepository.findById(phoneSpecs.getPhoneId()).get();
        Integer result = phoneSpecs.getSpecsStock() - quantity;
        if (result < 0){
            log.error("库存不足‍📱");
            throw new PhoneException(ResultEnum.PHONE_STOCK_EROOR);
        }
        phoneSpecs.setSpecsStock(result);
        specsRepository.save(phoneSpecs);
        result = phoneInfo.getPhoneStock() - quantity;
        if (result < 0){
            log.error("库存不足‍📱");
            throw new PhoneException(ResultEnum.PHONE_STOCK_EROOR);
        }
        phoneInfo.setPhoneStock(result);
        infoRepository.save(phoneInfo);
    }
}
