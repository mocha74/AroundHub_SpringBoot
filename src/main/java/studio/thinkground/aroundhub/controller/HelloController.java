package studio.thinkground.aroundhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("log-test")
    public void logTest() {
        LOGGER.trace("Trace Log");
        LOGGER.debug("Debug Log");
        LOGGER.info("Info Log");
        LOGGER.warn("Warn Log");
        LOGGER.error("Error Log");
    }
}
