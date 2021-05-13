package br.edu.ifsp.point.controllers;

import br.edu.ifsp.point.models.User;
import br.edu.ifsp.point.models.api.SuccessResponse;
import br.edu.ifsp.point.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<SuccessResponse> findAll(){
        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, "FindAll"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, "FindById => " + id));
    }

    @PostMapping("")
    public ResponseEntity<SuccessResponse> create(@RequestBody User user){
        return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED, "CreateUser"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestBody User user){
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "UpdateUser => " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id){
        return ResponseEntity.status(204).body(new SuccessResponse(HttpStatus.NO_CONTENT, "DeleteUser => " + id));
    }

}
