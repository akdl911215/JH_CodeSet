package kr.junghyun.api.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HomeController {

    @GetMapping("/")
    public String hmoe() {
        // 시간 출력
        return new SimpleDateFormat("MM:NN HH:mm").format(new Date());
    }

    @GetMapping("/time")
    public void timeCount() {
        Date date_now = new Date(System.currentTimeMillis());
        System.out.println("현재시간: " + date_now);
    }
}
