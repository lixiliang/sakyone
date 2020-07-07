package utill;

import org.junit.Test;

import static org.junit.Assert.*;

public class IdUtilsTest {

    @Test
    public void getIncreaseIdByNanoTime() {
        for (int i = 0; i < 10; i++) {
            System.out.println(IdUtils.getIncreaseIdByNanoTime());
        }
    }

    @Test
    public void getIncreaseIdByCurrentTimeMillis() {
        for (int i = 0; i < 10; i++) {
            System.out.println(IdUtils.getIncreaseIdByCurrentTimeMillis());
        }
    }

    @Test
    public void getRandomIdByUUID() {
        for (int i = 0; i < 10; i++) {
            System.out.println(IdUtils.getRandomIdByUUID());
        }
    }
}