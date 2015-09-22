package be.urpi.software.xsi.command.rest.controller.user;

import be.urpi.software.xsi.command.user.message.CreateUserMessage;
import be.urpi.software.xsi.command.user.message.EnableUserMessage;
import be.urpi.software.xsi.command.user.metadata.CreateUser;
import be.urpi.software.xsi.command.user.metadata.EnableUser;
import be.urpi.software.xsi.command.user.producer.CreateUserProducer;
import be.urpi.software.xsi.command.user.producer.EnableUserProducer;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/user")
public class UserRestInterface {
    @Resource
    private CreateUserProducer createUserProducer;
    @Resource
    private EnableUserProducer enableUserProducer;

    @RequestMapping(value = "/create", method = POST)
    ResponseEntity createUser(@RequestBody CreateUser createUser) {
        createUserProducer.send(new CreateUserMessage(createUser));
        return new ResponseEntity(OK);
    }

    @RequestMapping(value = "/enable/{uuid}", method = PUT)
    ResponseEntity enableUser(@PathVariable(value = "uuid") String uuid) {
        final EnableUserMessage enableUserMessage = new EnableUserMessage(new EnableUser(uuid, true));
        enableUserProducer.send(enableUserMessage);
        return new ResponseEntity(OK);
    }

    @RequestMapping(value = "/enable/{uuid}", method = DELETE)
    ResponseEntity deleteUser(@PathVariable(value = "uuid") String uuid) {
        final EnableUserMessage enableUserMessage = new EnableUserMessage(new EnableUser(uuid, false));
        enableUserProducer.send(enableUserMessage);
        return new ResponseEntity(OK);
    }
}
