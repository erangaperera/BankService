package com.wso2.main;

import org.wso2.carbon.mss.MicroservicesRunner;

import com.wso2.services.BankService;

public class Application {
	
    public static void main(String[] args) {
        new MicroservicesRunner()
                .deploy(new BankService())
                .start();
    }
}
