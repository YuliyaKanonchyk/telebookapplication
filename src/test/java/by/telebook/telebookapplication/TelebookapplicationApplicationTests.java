package by.telebook.telebookapplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TelebookapplicationApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void tts(){
        Assertions.assertEquals(4, sum(2, 2));
        Assertions.assertEquals(8, sum(4, 4));
        Assertions.assertEquals(8, sum(1, 7));
        Assertions.assertEquals(8, sum(0, 8));
        Assertions.assertEquals(-15, sum(-30, 15));
        Assertions.assertEquals(0, sum(0, 0));
    }


    public int sum(int a, int b){
        return a + b;
    }
}
