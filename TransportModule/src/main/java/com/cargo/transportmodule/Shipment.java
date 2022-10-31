package com.cargo.transportmodule;

public class Shipment {
    private String appId;
    private String appSecurity;
    private String enterpriseSenderCode;
    private String environment;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecurity() {
        return appSecurity;
    }

    public void setAppSecurity(String appSecurity) {
        this.appSecurity = appSecurity;
    }

    public String getEnterpriseSenderCode() {
        return enterpriseSenderCode;
    }

    public void setEnterpriseSenderCode(String enterpriseSenderCode) {
        this.enterpriseSenderCode = enterpriseSenderCode;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
