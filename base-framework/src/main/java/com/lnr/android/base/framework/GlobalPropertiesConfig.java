package com.lnr.android.base.framework;

import com.lnr.android.base.framework.uitl.NumberUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * author:lnr
 * date:2019/1/25
 */
public class GlobalPropertiesConfig {

    private static final HashMap<Object, Object> CONFIG;

    public static final String BAOLIAO_DETAILS_SHARE = "baoliao.details.share";








    static {
        CONFIG = new HashMap<>();
        try {
            Properties prop = new Properties();
            InputStream stream = Framework.getInstance().getApplication().getAssets().open("config.properties");
            prop.load(stream);
            for (Map.Entry<Object, Object> entry : prop.entrySet()) {
                CONFIG.put(entry.getKey(), entry.getValue());
            }
            prop.list(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String name) {
        return CONFIG.get(name);
    }

    public static int getInt(String name, int def) {
        Object o = CONFIG.get(name);
        if(o == null) {
            return def;
        }

        if(o instanceof Integer) {
            return (int) o;
        }

        return NumberUtil.parseInt(String.valueOf(o), def);
    }

    public static boolean getBoolean(String name, boolean def) {
        Object o = CONFIG.get(name);
        if(o == null) {
            return def;
        }

        if(o instanceof Boolean) {
            return (boolean) o;
        }

        return Boolean.parseBoolean(String.valueOf(o)) || def;
    }

    public static String getString(String name, String def) {
        Object o = CONFIG.get(name);
        if(o == null) {
            return def;
        }

        if(o instanceof String) {
            return (String) o;
        }

        return def;
    }
}
