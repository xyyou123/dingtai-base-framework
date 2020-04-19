package com.lnr.android.base.framework.uitl;

import android.content.Context;
import android.content.SharedPreferences;

import com.lnr.android.base.framework.Framework;

public class SP {

    private static SharedPreferences DEFAULT;

    public static SharedPreferences getSharedPreferences(String name) {
        return Framework.getInstance().getApplication().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getDefault() {
        if(DEFAULT == null) {
            DEFAULT = getSharedPreferences(Framework.getInstance().getApplication().getPackageName().replaceAll("\\.", "_"));
        }
        return DEFAULT;
    }

    public SharedPreferences.Editor edit() {
        return edit(getDefault());
    }

    public SharedPreferences.Editor edit(SharedPreferences sharedPreferences) {
        return sharedPreferences.edit();
    }

    public void remove(String... keys) {
        remove(getDefault(), keys);
    }

    public void remove(SharedPreferences sharedPreferences, String... keys) {
        if(sharedPreferences == null || keys == null || keys.length == 0) return;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.apply();
    }

    public int get(String key, int def) {
        return get(getDefault(), key, def);
    }

    public int get(SharedPreferences sharedPreferences, String key, int def) {
        return sharedPreferences.getInt(key, def);
    }

    public float get(String key, float def) {
        return get(getDefault(), key, def);
    }

    public float get(SharedPreferences sharedPreferences, String key, float def) {
        return sharedPreferences.getFloat(key, def);
    }


    public long get(String key, long def) {
        return get(getDefault(), key, def);
    }

    public long get(SharedPreferences sharedPreferences, String key, long def) {
        return sharedPreferences.getLong(key, def);
    }

    public String get(String key, String def) {
        return get(getDefault(), key, def);
    }

    public String get(SharedPreferences sharedPreferences, String key, String def) {
        return sharedPreferences.getString(key, def);
    }

    public boolean get(String key, boolean def) {
        return get(getDefault(), key, def);
    }

    public boolean get(SharedPreferences sharedPreferences, String key, boolean def) {
        return sharedPreferences.getBoolean(key, def);
    }
}
