package br.edu.ifsp.point.controllers;

import br.edu.ifsp.point.models.User;
import br.edu.ifsp.point.models.api.SuccessResponse;
import br.edu.ifsp.point.models.vo.UserVO;
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

        List<UserVO> usersVO = userService.findAll();

        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, usersVO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> findById(@PathVariable("id") Long id){

        UserVO userVO = userService.findById(id);

        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, userVO));
    }

    @PostMapping("")
    public ResponseEntity<SuccessResponse> save(@RequestBody UserVO userVO){

        UserVO userCreated = userService.save(userVO);

        return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED, userCreated));
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse> login(@RequestBody UserVO userVO){

        UserVO userLogged = userService.login(userVO);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, userLogged));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestBody UserVO userVO){

        UserVO userUpdated = userService.update(id, userVO);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, userUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteById(@PathVariable("id") Long id){

        userService.deleteById(id);

        return ResponseEntity.status(204).body(new SuccessResponse(HttpStatus.NO_CONTENT, "Usuário deletado com sucesso"));
    }

}
