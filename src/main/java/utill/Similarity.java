package utill;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Similarity {

    public static int calEditDistance(String A, String B) {
        if (A.equals(B)) {
            return 0;
        }
        //dp[i][j]表示源串A位置i到目标串B位置j处最低需要操作的次数
        int[][] dp = new int[A.length() + 1][B.length() + 1];
        for (int i = 1; i <= A.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= B.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1,
                            Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }
        return dp[A.length()][B.length()];
    }

    public static float calSimilarity(String a, String b) {
        int dis = calEditDistance(a, b);
        BigDecimal a1 = BigDecimal.valueOf(dis);
        int len = Math.max(a.length(), b.length());
        BigDecimal b1 = BigDecimal.valueOf(len);

        return 1 - a1.divide(b1, 4, RoundingMode.FLOOR).floatValue();
//      return 0;
    }

    public static void main(String[] args) {
//        Similarity test = new Similarity();
        long start = System.currentTimeMillis();
        String A = "SELECT id, device_id, terminal_type, expired_time, status, client_type, create_time, update_time FROM msg_client WHERE device_id = 'oHQU6wIN7LhKycZlq830meUaa89s20170429' LIMIT 1";
        String B = "SELECT id, device_id, terminal_type, expired_time, status, client_type, create_time, update_time FROM msg_client WHERE device_id = 'oqpFMwiEJ7ObC2B5V6J1pF3KANgo' LIMIT 1";
        System.out.println(Similarity.calSimilarity(A, B) + "cost:" + (System.currentTimeMillis() - start) + " ms");
    }
}
