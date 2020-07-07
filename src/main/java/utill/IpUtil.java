package utill;

import java.net.*;
import java.util.Enumeration;

public class IpUtil {
    private static String getLocalIp() {
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            while (networks.hasMoreElements()) {
                Enumeration<InetAddress> addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements()) {
                    InetAddress ip = addrs.nextElement();
                    if (ip instanceof Inet4Address && !ip.isSiteLocalAddress() && !ip.isLoopbackAddress()) {
                        // 从网卡获取外网IP
                        System.out.println(ip.getHostAddress()); ;
                    }
                }
            }
        } catch (SocketException e) {
        }
        try {
            // 外网IP不存在，那么获取内网IP
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(getLocalIp());
    }
}
