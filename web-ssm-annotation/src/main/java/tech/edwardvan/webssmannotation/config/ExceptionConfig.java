package tech.edwardvan.webssmannotation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionConfig.class);

    @ExceptionHandler()
    @ResponseBody
    String handleException(Exception e){
        logger.error(e.getMessage());
        return "Exception:" + e.getMessage();
    }
}
