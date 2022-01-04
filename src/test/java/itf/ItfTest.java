package itf;

import org.junit.Test;

/**
 * @author lixiliang
 * @describe
 * @date 2021/12/28
 */
public class ItfTest {
    @Test
    public void t1(){
        C1 c1 = new C1();
        C2 c2 = new C2();

        Command com1 = (Command)c1;
        ClusterCommand com2 = (ClusterCommand)c2;

    }


}
