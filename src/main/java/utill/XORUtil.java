package utill;

/**
 * Created by lixiliang on 2019/3/8.
 *  KEY 非常重要，不能对公众泄露KEY值
 *  发送端和接收端提前秘密约定好KEY值
 */
public class XORUtil {

    public static int encrypt(int origin,int key){
        return origin^key;
    }

    public static int decrypt(int cipher,int key){
        return cipher^key;
    }

    public static void main(String[] args) {
        int key =12345678;
        System.out.println(XORUtil.encrypt(1234567890,key));
        System.out.println(XORUtil.decrypt(12345653,key));
    }
}
