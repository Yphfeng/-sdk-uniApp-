package com.cargo.transportmodule;

import com.hdgq.locationlib.entity.ShippingNoteInfo;


public class DriverModel {
    private String vehicleNumber; //车牌号
    private String driverName; //司机姓名
    private String remark; //备注

    public ShippingNoteInfo[] getShippingNoteInfos() {
        return shippingNoteInfos;
    }

    public void setShippingNoteInfos(ShippingNoteInfo[] shippingNoteInfos) {
        this.shippingNoteInfos = shippingNoteInfos;
    }

    private ShippingNoteInfo[] shippingNoteInfos; //运单信息列表


    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
