package com.example.comma_counter_;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
class CommaCounterApplicationTests {

    @Autowired
    private MockMvc mvc;
    comma_count c = new comma_count();

    @Test
    void commaCounterValidParameter() {
        String text = "My grocery list: ham, cheese, fairy liquid, pizza and yogurt.";
        assert(c.count_commas(text).equals("3"));
    }
    @Test
    void commaCounterEmptyString() {
        String text = "";
        assert(c.count_commas(text).equals("Please enter a valid string"));
    }
    @Test
    void commaCounterValidStringNoCommas() {
        String text = "There are no commas here";
        assert(c.count_commas(text).equals("0"));
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
    @Test
    public void TestStatusCodeAndResponseWithValidParameters()  throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/?x=test", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.answer").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("false"));

    }
    @Test
    public void TestStatusCodeAndResponseWithNoParameters()  throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.answer").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("true"));

    }
    @Test
    public void TestStatusCodeAndResponseNumbers()  throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/?x=12345", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.answer").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("Please enter words, not numbers"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("true"));

    }
    @Test
    public void TestStatusCodeAndResponseNumbersAndSpaces()  throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/?x=12345 12345", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.answer").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("Please enter words, not numbers"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("true"));

    }
    @Test
    public void TestStatusCodeAndResponseWithIncorrectRoute()  throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/wrongroute", 1)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());

    }
    @Test
    public void TestStatusCodeAndResponseWithEmptyString()  throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/?x=", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.answer").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("true"));

    }
    @Test
    public void TestStatusCodeAndResponseWithCorrectString()  throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/?x=,,,", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.answer").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("false"));

    }
    @Test
    public void TestStatusCodeAndResponseWithCorrectStringNoCommas()  throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/?x=no commas", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.answer").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("false"));

    }

}
