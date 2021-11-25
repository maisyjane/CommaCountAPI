package com.example.comma_counter_;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommaErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError() {
        //do something like logging - could do html here
        return "Please enter a valid text string";
    }
}
