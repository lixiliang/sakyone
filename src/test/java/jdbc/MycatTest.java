package jdbc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 2017/10/26.
 */
public class MycatTest {
    Mycat mycat;
    @Before
    public void setUp() throws Exception {
        mycat = new Mycat();
    }

    @Test
    public void select() throws Exception {
        mycat.select();
    }

}