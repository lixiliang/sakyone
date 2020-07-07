import org.springframework.beans.BeanUtils;

public class BeanUtil {


    public static void main(String[] args) {
        A1 a1 = new A1();
        a1.setB(new byte[11]);
        A1 b = new A1();
        BeanUtils.copyProperties(a1,b);
        System.out.println(a1);
    }
}
class A1{
    private byte[] b;

/*    public Object getB() {
        System.out.println("invoked");
        return new Object();
    }*/
    public byte[] getB() {
        System.out.println("invoked");
        return new byte[1];
    }

    public void setB(byte[] b) {
        this.b = b;
    }
}