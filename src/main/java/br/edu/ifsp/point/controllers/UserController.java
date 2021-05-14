package br.edu.ifsp.point.controllers;

import br.edu.ifsp.point.models.User;
import br.edu.ifsp.point.models.api.SuccessResponse;
import br.edu.ifsp.point.services.UserService;
import br.edu.ifsp.point.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<SuccessResponse> findAll(){

        List<User> users = userService.findAll();

        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> findById(@PathVariable("id") Long id){

        User user = userService.findById(id);

        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, user));
    }

    @PostMapping("")
    public ResponseEntity<SuccessResponse> save(@RequestBody User user){

        User userCreated = userService.save(user);

        return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED, userCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestBody User user){

        User userUpdated = userService.update(id, user);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, userUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteById(@PathVariable("id") Long id){

        userService.deleteById(id);

        return ResponseEntity.status(204).body(new SuccessResponse(HttpStatus.NO_CONTENT, "Usuário deletado com sucesso"));
    }

}
