import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017/9/13.
 */
public class Test {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Map<String,String> map = new ConcurrentHashMap<String,String>();
        String o1 = map.putIfAbsent("key1","v1");
        String o2 = map.putIfAbsent("key1","v2");
        System.out.println("o1"+o1);
        System.out.println("o2"+o2);
//        map.remove(o2);
        System.out.println(map.get("key1"));

        List<Token> tokens = Lists.newArrayList();
        Token t1 = new Token("a");
        Token t2 = new Token("b");
        tokens.add(t1);
        tokens.add(t2);
        tokens.add(Token.MULTI);
        tokens.add(new Token("f"));
        String top= topic(tokens);
//        Token







       /* InetAddress addr = InetAddress.getLocalHost();
        String ip=addr.getHostAddress().toString(); //获取本机ip
        String hostName=addr.getHostName().toString(); //获取本机计算机名称
        System.out.println("ip: "+ip);
        System.out.println("hostname: "+hostName);
        int [] a ={1,2};
//        int [] b =[1,2];
        test1();
        t2();*/

    }
    private static String topic(List<Token> tokens) {
        List<String> strTokens = tokens.stream().map(Token::toString).collect(Collectors.toList());
        String topic = String.join("/", strTokens);
        System.out.println(topic);
        return topic;
    }



    public static void test1()
    {
        String IP = null;
        String host = null;

        try
        {
            InetAddress ia = InetAddress.getLocalHost();
            host = ia.getHostName();//获取计算机主机名
            IP= ia.getHostAddress();//获取计算机IP
        }
        catch(UnknownHostException e)
        {
            e.printStackTrace();
        }

        System.out.println(host);
        System.out.println(IP);
    }

    private static void t2() {
        StringBuilder IFCONFIG = new StringBuilder();
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        IFCONFIG.append(inetAddress.getHostAddress().toString() + "\n");
                    }

                }
            }
        } catch (SocketException ex) {

            System.out.println(IFCONFIG);
        }finally {
            System.out.println(IFCONFIG);
        }
    }
}
