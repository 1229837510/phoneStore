package com.bigdata.store.controller;

import com.bigdata.store.service.PhoneService;
import com.bigdata.store.util.ResultVOUtil;
import com.bigdata.store.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phone/")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @RequestMapping("index")
    public ResultVO index() {
        return ResultVOUtil.success(phoneService.findDataVO());
    }

    @RequestMapping("findByCategoryType/{categoryType}")
    public ResultVO findByCategoryType(
            @PathVariable("categoryType") Integer categoryType){
        return ResultVOUtil.success(phoneService.findByCategoryType(categoryType));
    }
    @RequestMapping("findSpecsByPhoneId/{phoneId}")
    public ResultVO findSpecsByPhoneId(
            @PathVariable("phoneId") Integer phoneId){
        return ResultVOUtil.success(phoneService.findSpecsByPhoneId(phoneId));
    }
}
