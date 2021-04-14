package kr.hyun.work.cmm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {

        return new SimpleDateFormat("yyYY:MM:NN HH:mm").format(new Date());
    }
}
