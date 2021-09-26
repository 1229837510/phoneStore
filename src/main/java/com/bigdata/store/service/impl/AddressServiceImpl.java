package com.bigdata.store.service.impl;

import com.bigdata.store.entity.BuyerAddress;
import com.bigdata.store.form.AddressForm;
import com.bigdata.store.repository.BuyerAddressRepository;
import com.bigdata.store.service.AddressService;
import com.bigdata.store.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private BuyerAddressRepository buyerAddressRepository;

    @Override
    public List<AddressVO> findAll() {
        List<AddressVO> list = buyerAddressRepository.findAll().stream()
                .map(s -> new AddressVO(
                        s.getAddressId(),
                        s.getAreaCode(),
                        s.getBuyerName(),
                        s.getBuyerAddress(),
                        s.getBuyerPhone()
                )).collect(Collectors.toList());
        return list;
    }

    @Override
    public void saveOrUpdate(AddressForm addressForm) {
//        BuyerAddress buyerAddress;
//        if(addressForm.getId() == null){
//            buyerAddress = new BuyerAddress();
//        } else {
//            buyerAddress = buyerAddressRepository.findById(addressForm.getId()).get();
//        }
        BuyerAddress buyerAddress = new BuyerAddress();
        buyerAddress.setAddressId(addressForm.getId());
        buyerAddress.setBuyerName(addressForm.getName());
        buyerAddress.setBuyerPhone(addressForm.getTel());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(addressForm.getProvince())
                .append(addressForm.getCity())
                .append(addressForm.getCounty())
                .append(addressForm.getAddressDetail());
        buyerAddress.setBuyerAddress(stringBuffer.toString());
        buyerAddress.setAreaCode(addressForm.getAreaCode());

        buyerAddressRepository.save(buyerAddress);
    }
}
