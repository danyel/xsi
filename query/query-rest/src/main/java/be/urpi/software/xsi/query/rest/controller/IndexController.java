package be.urpi.software.xsi.query.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @Autowired
    private Environment environment;

    @RequestMapping(value = "/environment", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity env() {
        return new ResponseEntity<>(StringUtils.join(environment.getActiveProfiles(), ","), HttpStatus.OK);
    }

    @RequestMapping(value = "/index", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity test() {
        return new ResponseEntity<>("rest", HttpStatus.OK);
    }
}
