package com.cargo.transportmodule;

import android.app.Activity;

import android.app.Application;
import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.location.AMapLocationClient;
import com.hdgq.locationlib.LocationOpenApi;
import com.hdgq.locationlib.entity.ShippingNoteInfo;

import com.hdgq.locationlib.listener.OnResultListener;
import com.hdgq.locationlib.listener.OnSendResultListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;


public class TransportModule extends UniModule {

    private static final String TAG = "开始起运1111";
    private String jsonTest;


    //初始化
    @UniJSMethod(uiThread = false)
    public void init(JSONObject object, final UniJSCallback callback) {
        LocationOpenApi.init((Application) mUniSDKInstance.getContext());
    }
     //授权接口
    @UniJSMethod(uiThread = false)
    public void auth(JSONObject options, final UniJSCallback callback) {
        Log.d(TAG, "auth: 121212");
        Shipment appJson = JSONObject.toJavaObject(options, Shipment.class);
        if (appJson == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入必要参数");
            callback.invoke(data);
        }
        if (appJson.getAppId() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入appId");
            callback.invoke(data);
        } else if (appJson.getAppSecurity() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入企业安全码");
            callback.invoke(data);
        } else if (appJson.getEnterpriseSenderCode() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入android安全码");
            callback.invoke(data);
        } else if (appJson.getEnvironment() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入接入的环境");
            callback.invoke(data);
        }
        LocationOpenApi.auth((Activity) mUniSDKInstance.getContext(), appJson.getAppId(), appJson.getAppSecurity(), appJson.getEnterpriseSenderCode(), appJson.getEnvironment(), new OnResultListener() {
            @Override
            public void onFailure(String s, String s1) {
                Log.e(s, "onFailure: ");
                Log.e(s1, "onFailure: ");

                JSONObject data = new JSONObject();
                data.put("code", "0");
                data.put("message1", s);
                data.put("message2", s1);
                callback.invoke(data);
            }

            @Override
            public void onSuccess(List<ShippingNoteInfo> list) {
                Log.d(String.valueOf(list), "onSuccessAuth: ");
                jsonTest = JSONObject.toJSONString(list);
                JSONObject data = new JSONObject();
                data.put("code", "1");
                data.put("message1", jsonTest);
                callback.invoke(data);
            }
        });

    }

    //开启定位
    @UniJSMethod(uiThread = false)
    public void start(JSONObject options, final UniJSCallback callback) {
        DriverModel driverModel = JSONObject.toJavaObject(options, DriverModel.class);
        if (driverModel == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入必要参数");
            callback.invoke(data);
        }
        if (driverModel.getDriverName() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入驾驶员姓名");
            callback.invoke(data);
        } else if (driverModel.getVehicleNumber() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入车牌号");
            callback.invoke(data);
        } else if (driverModel.getShippingNoteInfos() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入运单信息");
            callback.invoke(data);
        }
//        List<ShippingNoteInfo> list = new ArrayList<>();
        ShippingNoteInfo[] shippingNoteInfos = driverModel.getShippingNoteInfos();
//        ShippingNoteInfo shippingNoteInfo = new ShippingNoteInfo();
//        shippingNoteInfo.setDriverName(driverModel.getDriverName());
//
//        list.add(shippingNoteInfo);
//        ShippingNoteInfo[] arrs = list.toArray(new ShippingNoteInfo[1]);
        LocationOpenApi.start((Activity) mUniSDKInstance.getContext(), driverModel.getVehicleNumber(), driverModel.getDriverName(), driverModel.getRemark(), shippingNoteInfos, new OnResultListener() {
            @Override
            public void onFailure(String s, String s1) {
                JSONObject data = new JSONObject();
                data.put("code", "0");
                data.put("message1", s);
                data.put("message2", s1);
                callback.invoke(data);
            }

            @Override
            public void onSuccess(List<ShippingNoteInfo> list) {
                Log.e(String.valueOf(list), "onSuccessstart: ");
                jsonTest = JSONObject.toJSONString(list);
                JSONObject data = new JSONObject();
                data.put("code", "1");
                data.put("message1", jsonTest);
                callback.invoke(data);
            }
        });
    }

    //上报定位信息
    @UniJSMethod(uiThread = false)
    public void send(JSONObject options, final UniJSCallback callback) {
        Log.e(TAG, "send1: ");
        DriverModel driverModel = JSONObject.toJavaObject(options, DriverModel.class);
        if (driverModel == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入必要参数");
            callback.invoke(data);
        }
        if (driverModel.getDriverName() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入驾驶员姓名");
            callback.invoke(data);
        } else if (driverModel.getVehicleNumber() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入车牌号");
            callback.invoke(data);
        } else if (driverModel.getShippingNoteInfos() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入运单信息");
            callback.invoke(data);
        }
        ShippingNoteInfo[] shippingNoteInfos = driverModel.getShippingNoteInfos();
        LocationOpenApi.send((Activity) mUniSDKInstance.getContext(), driverModel.getVehicleNumber(), driverModel.getDriverName(), driverModel.getRemark(), shippingNoteInfos, new OnSendResultListener() {
            @Override
            public void onFailure(String s, String s1, List<ShippingNoteInfo> list) {
                Log.e(s, "onFailure: send");
                Log.e(s1, "onFailure: send");
                JSONObject data = new JSONObject();
                data.put("code", "0");
                data.put("message1", s);
                data.put("message2", s1);
                callback.invoke(data);
            }

            @Override
            public void onSuccess(List<ShippingNoteInfo> list) {
                Log.e(TAG, "onSuccess:send ");
                jsonTest = JSONObject.toJSONString(list);
                JSONObject data = new JSONObject();
                data.put("code", "1");
                data.put("message1", jsonTest);
                callback.invoke(data);
            }
        });
    }

    //暂停
    @UniJSMethod(uiThread = true)
    public void pause(JSONObject options, final UniJSCallback callback) {
        DriverModel driverModel = JSONObject.toJavaObject(options, DriverModel.class);
        if (driverModel == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入必要参数");
            callback.invoke(data);
        }
        if (driverModel.getDriverName() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入驾驶员姓名");
            callback.invoke(data);
        } else if (driverModel.getVehicleNumber() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入车牌号");
            callback.invoke(data);
        } else if (driverModel.getShippingNoteInfos() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入运单信息");
            callback.invoke(data);
        }
        ShippingNoteInfo[] shippingNoteInfos = driverModel.getShippingNoteInfos();
        LocationOpenApi.send((Activity) mUniSDKInstance.getContext(), driverModel.getVehicleNumber(), driverModel.getDriverName(), driverModel.getRemark(), shippingNoteInfos, new OnSendResultListener() {
            @Override
            public void onFailure(String s, String s1, List<ShippingNoteInfo> list) {
                JSONObject data = new JSONObject();
                data.put("code", "0");
                data.put("message1", s);
                data.put("message2", s1);
                callback.invoke(data);
            }

            @Override
            public void onSuccess(List<ShippingNoteInfo> list) {
                jsonTest = JSONObject.toJSONString(list);
                JSONObject data = new JSONObject();
                data.put("code", "1");
                data.put("message1", jsonTest);
                callback.invoke(data);
            }
        });
    }

    //重启定位
    @UniJSMethod(uiThread = true)
    public void restart(JSONObject options, final UniJSCallback callback) {
        DriverModel driverModel = JSONObject.toJavaObject(options, DriverModel.class);
        if (driverModel == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入必要参数");
            callback.invoke(data);
        }
        if (driverModel.getDriverName() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入驾驶员姓名");
            callback.invoke(data);
        } else if (driverModel.getVehicleNumber() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入车牌号");
            callback.invoke(data);
        } else if (driverModel.getShippingNoteInfos() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入运单信息");
            callback.invoke(data);
        }
        ShippingNoteInfo[] shippingNoteInfos = driverModel.getShippingNoteInfos();
        LocationOpenApi.restart((Activity) mUniSDKInstance.getContext(), driverModel.getVehicleNumber(), driverModel.getDriverName(), driverModel.getRemark(), shippingNoteInfos, new OnResultListener() {
            @Override
            public void onFailure(String s, String s1) {
                JSONObject data = new JSONObject();
                data.put("code", "0");
                data.put("message1", s);
                data.put("message2", s1);
                callback.invoke(data);
            }

            @Override
            public void onSuccess(List<ShippingNoteInfo> list) {
                jsonTest = JSONObject.toJSONString(list);
                JSONObject data = new JSONObject();
                data.put("code", "1");
                data.put("message1", jsonTest);
                callback.invoke(data);
            }
        });
    }

    //结束定位
    @UniJSMethod(uiThread = true)
    public void stop(JSONObject options, final UniJSCallback callback) {
        Log.e(TAG, "stop: ");
        DriverModel driverModel = JSONObject.toJavaObject(options, DriverModel.class);
        if (driverModel == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入必要参数");
            callback.invoke(data);
        }
        if (driverModel.getDriverName() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入驾驶员姓名");
            callback.invoke(data);
        } else if (driverModel.getVehicleNumber() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入车牌号");
            callback.invoke(data);
        } else if (driverModel.getShippingNoteInfos() == null) {
            JSONObject data = new JSONObject();
            data.put("code", "0");
            data.put("message", "请传入运单信息");
            callback.invoke(data);
        }
        ShippingNoteInfo[] shippingNoteInfos = driverModel.getShippingNoteInfos();
        LocationOpenApi.stop((Activity) mUniSDKInstance.getContext(), driverModel.getVehicleNumber(), driverModel.getDriverName(), driverModel.getRemark(), shippingNoteInfos, new OnResultListener() {
            @Override
            public void onFailure(String s, String s1) {
                Log.e(s, "onFailure: ");
                Log.e(s1, "onFailure: ");
                JSONObject data = new JSONObject();
                data.put("code", "0");
                data.put("message1", s);
                data.put("message2", s1);
                callback.invoke(data);
            }

            @Override
            public void onSuccess(List<ShippingNoteInfo> list) {
                Log.e(TAG, "onSuccessStop: ");
                jsonTest = JSONObject.toJSONString(list);
                JSONObject data = new JSONObject();
                data.put("code", "1");
                data.put("message1", jsonTest);
                callback.invoke(data);
            }
        });
    }



    //JSONArr转实体arr
    private List<ShippingNoteInfo> parseJSONOrgin(JSONArray json) {
        List<ShippingNoteInfo> list = new ArrayList<ShippingNoteInfo>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ShippingNoteInfo shippingNoteInfo = new ShippingNoteInfo();

                shippingNoteInfo.setShippingNoteNumber(jsonObject.getString("shippingNoteNumber"));
                shippingNoteInfo.setSerialNumber(jsonObject.getString("serialNumber"));
                shippingNoteInfo.setStartCountrySubdivisionCode(jsonObject.getString("startCountrySubdivisionCode"));
                shippingNoteInfo.setEndCountrySubdivisionCode(jsonObject.getString("endCountrySubdivisionCode"));
                list.add(shippingNoteInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 广播接收器
     * @author len
     *
     */
    public class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //拿到进度，传递值
            Toast.makeText(mUniSDKInstance.getContext(), "拿到进度", Toast.LENGTH_LONG).show();
            String returnData = intent.getStringExtra("returnData");
            Map<String,Object> params=new HashMap<>();
            params.put("locationData",returnData);
            mUniSDKInstance.fireGlobalEventCallback("locationData", params);

        }
    }

}
