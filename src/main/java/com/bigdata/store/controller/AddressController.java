package com.bigdata.store.controller;

import com.bigdata.store.exception.PhoneException;
import com.bigdata.store.form.AddressForm;
import com.bigdata.store.service.AddressService;
import com.bigdata.store.util.ResultVOUtil;
import com.bigdata.store.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping("/list")
    public ResultVO list() {
        return ResultVOUtil.success(addressService.findAll());
    }

    @PostMapping("/create")
    public ResultVO create(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            log.error("参数有错误，adressForm={}",addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressForm);
        return ResultVOUtil.success(null);
    }
    @PutMapping("/update")
    public ResultVO update(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【修改】参数有错误，adressForm={}",addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressForm);
        return ResultVOUtil.success(null);
    }
}
