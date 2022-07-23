package com.demo.demo.controller;

import com.demo.demo.file.FileUser;
import com.demo.demo.model.User;
import com.demo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    FileUser fileUser = new FileUser();

    @Autowired
    private UserService userService;

    /* Read file from mariadb to file tsv */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();

        for(User user : users) {
            fileUser.writeFile(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Get data from file tsv */
    @RequestMapping(value = "/file" , method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUserFromFile() {
        List<User> users;

        users = fileUser.readFile();
        return new ResponseEntity<>(users , HttpStatus.OK);
    }

    /* Create from database to file tsv */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<User> createUserFromMaria(@RequestBody User user) {
        User userCreated = userService.saveUser(user);

        /* Save to file */
        fileUser.writeFile(user);
        return new ResponseEntity<>(userCreated , HttpStatus.OK);
    }

    /* Get data from tsv from mariadb */
    @RequestMapping(value = "/create-to-maria" , method = RequestMethod.GET)
    public ResponseEntity<?> createUserFromTsv() {
        /* Read file from tsv*/
        List<User> usersTsv = fileUser.readFile();

        /* Get data current in maria db */
        List<User> usersDb = userService.getUsers();

        for(User user: usersTsv) {
            if(!usersDb.contains(user)) {
                userService.saveUser(user);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
