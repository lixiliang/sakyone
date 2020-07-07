package utill;

/**
 * 埋堆堆版本号标识由3段数字由.号分隔，如：3.8.7 ,3.9.0
 */
public class MddVersionUtils {
    /**
     * 版本号比较
     *
     * @param v1
     * @param v2
     * @return 0代表相等，1代表左边大，-1代表右边大
     */
    public static int compareVersion(String v1, String v2) {
        if (v1.equals(v2)) {
            return 0;
        }
        String[] version1Array = v1.split("[.]");
        String[] version2Array = v2.split("[.]");
        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        long diff = 0;

        while (index < minLen && (diff = Long.parseLong(version1Array[index]) - Long.parseLong(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            for (int i = index; i < version1Array.length; i++) {
                if (Long.parseLong(version1Array[i]) > 0) {
                    return 1;
                }
            }
            for (int i = index; i < version2Array.length; i++) {
                if (Long.parseLong(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    public static void main(String[] args) {
        String v1 = "1.8.0";
        String v2 = "1.10.8";
        System.out.println(compareVersion(v1,v2));
    }
}
