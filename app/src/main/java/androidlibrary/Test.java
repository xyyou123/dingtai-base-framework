package androidlibrary;

import com.lnr.android.base.framework.uitl.Base64Util;

/**
 * author:lnr
 * date:2019/1/25
 */
public class Test {

    public static void main(String[] args) {
        String success = Base64Util.decodeBase64ToUTF8("Success");
        System.out.println(success);


    }


    public static boolean isNeedUpdate() {
        String old = "1.9.1".replaceAll("\\.", "");
        String latest = "1.9.11".replaceAll("\\.", "");
        int oldLength = old.length();
        int latestLength = latest.length();
        int compare = 0;
        for (int i = 0; i < latestLength; i++) {
            if (i < oldLength) {
                compare = latest.charAt(i) - old.charAt(i);
                if (compare < 0) {
                    return false;
                } else if (compare > 0) {
                    return true;
                }
            } else {
                return true;
            }
        }

        return false;
    }
}
