package com.example.comma_counter_;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
class CommaCounterApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void commaCounter() {
        String text = "My grocery list: ham, cheese, fairy liquid, pizza and yogurt.";
        assert(comma_count.count_commas(text).equals("3"));
    }
    @Test
    public void givenReturnTypeIsResponseEntity_thenDefaultContentTypeIsJSON()  throws Exception {

        // Given
        String expectedMimeType = "application/json";

        // Then
        String actualMimeType = mvc.perform(MockMvcRequestBuilders.get("/", 1))
                .andReturn().getResponse().getContentType();

        assertEquals(expectedMimeType, actualMimeType); //was Assert.assertEquals check maven import
    }

}
