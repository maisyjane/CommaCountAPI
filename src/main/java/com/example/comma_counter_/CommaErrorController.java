package com.example.comma_counter_;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommaErrorController implements ErrorController {

    public CommaErrorController(){}

    @RequestMapping("/error")
    public ResponseEntity<String> handleError() throws JSONException {

        JSONObject json = new JSONObject();
        //do something like logging - could do html here
        json.put("text", "Invalid Text");
        json.put("answer", "0");
        json.put("error", true);

        return ResponseEntity.badRequest()
                .header("Content-Type", "application/json")
                .header("Access-Control-Allow-Origin", "*")
                .body(json.toString());
    }
}
