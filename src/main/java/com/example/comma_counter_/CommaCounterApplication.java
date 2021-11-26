package com.example.comma_counter_;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class CommaCounterApplication {

    public static void main(String[] args) {SpringApplication.run(CommaCounterApplication.class, args);}

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> commaCount(@RequestParam(value = "x", defaultValue = "No Text Entered") String text) {
        String numOfCommas;
        if(text.equals("No Text Entered"))
        {
            numOfCommas = "N/A";
        }
        else
        {
            numOfCommas = comma_count.count_commas(text);
        }
        //see spring JSON for explanation in chrome bookmarks
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String> ("{\"String\": '" + text + "' | Commas: " + numOfCommas + " | Headers: " +  httpHeaders + " | Status: " + String.valueOf(HttpStatus.OK.value()) + "}", httpHeaders, HttpStatus.OK);

    }

    @GetMapping("/error")
    public String error() {
        return String.format("Please Enter a valid string");
    }


}
