package com.example.comma_counter_;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommaCounterApplicationTests {

    @Test
    void commaCounter() {
        String text = "My grocery list: ham, cheese, fairy liquid, pizza and yogurt.";
        assert(comma_count.count_commas(text).equals("3"));
    }

}
