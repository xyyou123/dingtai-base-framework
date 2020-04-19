package com.lnr.android.base.framework.common.xg;

import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.models.XGNotificationModel;
import com.lnr.android.base.framework.uitl.JsonUtil;

import java.util.HashMap;

/**
 * author:lnr
 * date:2018/12/26
 */
public class DefaultXGReceiveCallback implements XGReceiveCallback {
    public static String CLAZZ = "com.dingtai.android.library.news.ui.details.web.NewsDetailsActivity";
    public static HashMap<String, HashMap<String, String>> TYPE_LINK_CLASS = new HashMap<>();
    @Override
    public Intent getIntent(Context context, XGNotificationModel model) {
        JSONObject data = JsonUtil.parseObject(model.getCustomContent());
        if (data == null) return null;
        try {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (!data.containsKey("type")) {
                intent.setClass(context, Class.forName(CLAZZ));
                intent.putExtra("ID", data.getString("newsGuid"));
                return intent;
            } else {
                String type = data.getString("type");
                String id = data.getString("id");
                String link  = data.getString("link");
                
                for (String t : TYPE_LINK_CLASS.keySet()) {
                    if (t.equals(type)) {
                        HashMap<String, String> L_C = TYPE_LINK_CLASS.get(t);

                        if (L_C !=null) {
                            for (String l : L_C.keySet()) {
                                if (l.equals(link)) {
                                    intent.setClass(context, Class.forName(L_C.get(l)));
                                    intent.putExtra("ID", id);
                                    intent.putExtra("TYPE", type);
                                    intent.putExtra("LINK", link);
                                    return intent;
                                            
                                }
                            }
                        }
                    }
                  
                }
                
            }


            return null;
       
   
      
        } catch (ClassNotFoundException e) {
            return null;
        }
    }


    @Override
    public Intent getIntent(Context context, String str) {
        JSONObject data = JsonUtil.parseObject(str);
        if (data == null) return null;
        try {
            Intent intent = new Intent();
            intent.setClass(context, Class.forName("com.dingtai.android.library.news.ui.details.web.NewsDetailsActivity"));
            intent.putExtra("ID", data.getString("newsGuid"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return intent;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
