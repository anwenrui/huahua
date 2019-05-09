package com.an.qa.eureka;

import huahua.common.Result;
import huahua.common.StatusCode;

public class LabelEurekeImpl implements LabelEureke {

    @Override
    public Result queryById(String labelId) {

        return new Result(false, StatusCode.ERROR,"熔断器启动了");
    }
}