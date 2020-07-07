package utill;

import java.net.NetworkInterface;
import java.util.Enumeration;

public class Mac {
    /*static {
        System.out.println("mac running...");
        try {
            while (true){
                getDataCenterId();
                TimeUnit.SECONDS.sleep(5L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
    protected static long getDataCenterId() {
        long id = 0L;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface net = interfaces.nextElement();
                byte[] mac = net.getHardwareAddress();
                if (net.isUp() && net != null && mac != null) {
                    id = ((0x000000FF & (long) mac[mac.length - 1])
                            | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                    return id;
                }
            }
        } catch (Exception e) {
        }
        return id;
    }

    public static String getMacAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface net = interfaces.nextElement();
                byte[] bytes = net.getHardwareAddress();
                if (net.isUp() && net != null && bytes != null) {
                    StringBuffer sb = new StringBuffer();
                    for (byte b : bytes) {
                        //与11110000作按位与运算以便读取当前字节高4位
                        sb.append(Integer.toHexString((b & 240) >> 4));
                        //与00001111作按位与运算以便读取当前字节低4位
                        sb.append(Integer.toHexString(b & 15));
                        sb.append("-");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    return sb.toString().toUpperCase();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getDataCenterId());
        System.out.println(getMacAddress());
    }
}
