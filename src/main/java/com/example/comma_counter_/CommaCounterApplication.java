package com.example.comma_counter_;

import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;


@SpringBootApplication
@RestController
public class CommaCounterApplication {

    JSONObject json = new JSONObject();

    public static void main(String[] args) {SpringApplication.run(CommaCounterApplication.class, args);}

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> commaCount(@RequestParam(value = "x", defaultValue = "No Text Entered") String text) throws JSONException {
        comma_count count = new comma_count();
        String numOfCommas;


        if(text.equals("No Text Entered"))
        {
            numOfCommas = "0";
            json.put("Text", text);
            json.put("Commas", numOfCommas);
            json.put("Error", true);
            return ResponseEntity.badRequest()
                    .header("Content-Type", "application/json")
                    .header("Access-Control-Allow-Origin", "*")
                    .body(json.toString());


        }
        else
        {
            numOfCommas = count.count_commas(text);
            json.put("Text", text);
            json.put("Commas", numOfCommas);
            json.put("Error", false);
            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .header("Access-Control-Allow-Origin", "*")
                    .body(json.toString());
        }



    }

    @GetMapping(value = "/error") //produce json?
    public ResponseEntity<String> error() throws JSONException {
       CommaErrorController error = new CommaErrorController();
       return error.handleError();

    }


}
