package com.lnr.android.base.framework.uitl;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/8/31
 */
public class ListUtil {

    public static <T, E extends T> List<T> arrayList(E... ts) {
        List<T> arr = new ArrayList<>();
        for (T t : ts) {
            arr.add(t);
        }
        return arr;
    }
}
