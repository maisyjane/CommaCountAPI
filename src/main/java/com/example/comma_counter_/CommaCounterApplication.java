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

    final HttpHeaders httpHeaders = new HttpHeaders();

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
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccessControlAllowOrigin("*");
        return new ResponseEntity<String> ("{\"String\": '" + text + "' | Commas: " + numOfCommas + " | Headers: " +  httpHeaders + " | Status:  }", HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/error")
    public String error() {
        return "{\"String\": 'Invalid String' | Commas: N/A | Headers: " +  httpHeaders + " | Status: " + String.valueOf(HttpStatus.NOT_FOUND.value()) + "}";
    }


}
