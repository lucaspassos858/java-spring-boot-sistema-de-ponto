package br.edu.ifsp.point.controllers;

import br.edu.ifsp.point.models.User;
import br.edu.ifsp.point.models.api.SuccessResponse;
import br.edu.ifsp.point.models.vo.UserVO;
import br.edu.ifsp.point.services.UserService;
import br.edu.ifsp.point.utils.PasswordUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Api(value = "API - Usuários")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    @ApiOperation("Retorna uma lista com os usuários cadastrados")
    public ResponseEntity<SuccessResponse> findAll(){

        List<UserVO> usersVO = userService.findAll();

        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, usersVO));
    }

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    @ApiOperation("Retorna um único usuário")
    public ResponseEntity<SuccessResponse> findById(@PathVariable("id") Long id){

        UserVO userVO = userService.findById(id);

        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, userVO));
    }

    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    @ApiOperation("Cadastra um usuário na base de dados")
    public ResponseEntity<SuccessResponse> save(@RequestBody UserVO userVO){

        UserVO userCreated = userService.save(userVO);

        return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED, userCreated));
    }

    @PostMapping(value = "/login", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    @ApiOperation("Retorna o usuário com o e-mail e senha especificados")
    public ResponseEntity<SuccessResponse> login(@RequestBody UserVO userVO){

        UserVO userLogged = userService.login(userVO);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, userLogged));
    }

    @PutMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    @ApiOperation("Atualiza um usuário na base de dados")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestBody UserVO userVO){

        UserVO userUpdated = userService.update(id, userVO);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, userUpdated));
    }

    @DeleteMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    @ApiOperation("Deleta um usuário na base de dados")
    public ResponseEntity<SuccessResponse> deleteById(@PathVariable("id") Long id){

        userService.deleteById(id);

        return ResponseEntity.status(204).body(new SuccessResponse(HttpStatus.NO_CONTENT, "Usuário deletado com sucesso"));
    }

}
