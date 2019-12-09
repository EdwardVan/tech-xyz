package tech.edwardvan.rbacspringsecuritybrowser.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author EdwardVan
 */
@RestController
@Slf4j
public class BrowserSecurityController {

    public void requireAuthentication(HttpServletRequest request, HttpServletResponse response){

    }
}
