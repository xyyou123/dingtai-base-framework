package com.lnr.android.base.framework.data.db;

import com.alibaba.fastjson.JSON;
import com.dingtai.android.library.model.models.ADModel;
import com.lnr.android.base.framework.uitl.JsonUtil;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

public final class ADModelListConverter implements PropertyConverter<List<ADModel>, String> {
        @Override
        public List<ADModel> convertToEntityProperty(String databaseValue) {
            return JsonUtil.parseArray(databaseValue, ADModel.class);
        }

        @Override
        public String convertToDatabaseValue(List<ADModel> entityProperty) {
            return JSON.toJSONString(entityProperty);
        }
    }