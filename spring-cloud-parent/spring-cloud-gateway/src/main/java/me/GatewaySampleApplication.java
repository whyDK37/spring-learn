package me;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GatewaySampleApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GatewaySampleApplication.class);
        springApplication.run(args);

    }

    @GetMapping("/echo")
    public String proxy(String echo) throws Exception {
        return echo;
    }

//    @GetMapping("/test")
//    public ResponseEntity<?> proxy(ProxyExchange<byte[]> proxy) throws Exception {
//        return proxy.uri(home.toString() + "/image/png").get();
//    }

}