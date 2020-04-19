package com.lnr.android.base.framework.data.db;

import com.alibaba.fastjson.JSON;
import com.dingtai.android.library.model.models.ADModel;
import com.lnr.android.base.framework.uitl.JsonUtil;

import org.greenrobot.greendao.converter.PropertyConverter;

public final class ADModelConverter implements PropertyConverter<ADModel, String> {
    @Override
    public ADModel convertToEntityProperty(String databaseValue) {
        return JsonUtil.parseObject(databaseValue, ADModel.class);
    }

    @Override
    public String convertToDatabaseValue(ADModel entityProperty) {
        return JSON.toJSONString(entityProperty);
    }
}
