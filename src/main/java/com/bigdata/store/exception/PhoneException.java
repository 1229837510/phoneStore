package com.bigdata.store.exception;

import com.bigdata.store.enums.ResultEnum;

public class PhoneException extends RuntimeException {
    public PhoneException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }
    public PhoneException(String error){
        super(error);
    }
}
