package com.br.apigithub.impl;

import com.br.apigithub.interfaces.RetrofitServiceContract;
import com.br.apigithub.services.RetrofitService;

/**
 * Created by rlima on 30/05/18.
 */

public class RetrofitServiceImpl implements RetrofitServiceContract {

    @Override
    public RetrofitService getRetrofitService() {
        return RetrofitService.getInstance();
    }
}
