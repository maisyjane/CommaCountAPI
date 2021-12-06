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
   // @CrossOrigin(origins = "*")
    public ResponseEntity<String> commaCount(@RequestParam(value = "x", defaultValue = "No Text Entered") String text) {
        String numOfCommas;
        //see spring JSON for explanation in chrome bookmarks

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccessControlAllowOrigin("*");

        if(text.equals("No Text Entered"))
        {
            numOfCommas = "N/A";
            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .header("Access-Control-Allow-Origin", "*")
                    .body("{\"String\": " + text + "\"Commas\":" + numOfCommas + "\"");

        }
        else
        {
            numOfCommas = comma_count.count_commas(text);
            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .header("Access-Control-Allow-Origin", "*")
                    .body("{\"String\": " + text + "\"Commas\":" + numOfCommas + "\"");
        }



    }

    @GetMapping(value = "/error") //produce json?
    public ResponseEntity<String> error() {
        return ResponseEntity.badRequest()
                .header("Content-Type", "application/json")
                .header("Access-Control-Allow-Origin", "*")
                .body("{\"String\": Invalid Text" + "\"Commas\": N/A");

    }


}
