package com.example.comma_counter_;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class CommaCounterApplication {

    public static void main(String[] args) {SpringApplication.run(CommaCounterApplication.class, args);}

    @GetMapping("/")
    public String commaCount(@RequestParam(value = "x", defaultValue = "No Text Entered") String text) {
        String numOfCommas;
        if(text.equals("No Text Entered"))
        {
            numOfCommas = "N/A";
        }
        else
        {
            numOfCommas = comma_count.count_commas(text);
        }
        return String.format("String: '%s' | Commas: %s", text, numOfCommas);
    }

    @GetMapping("/error")
    public String error() {
        return String.format("Please Enter a valid string");
    }

}
