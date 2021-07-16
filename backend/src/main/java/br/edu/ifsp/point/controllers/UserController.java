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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "/user")
@Api(value = "API - Usuários")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    @ApiOperation("Retorna uma lista com os usuários cadastrados")
    public ResponseEntity<SuccessResponse> findAll(){

        List<UserVO> usersVO = userService.findAll();
        usersVO
        	.stream()
        		.forEach(user -> user.add(
        				linkTo(methodOn(UserController.class).findById(user.getKey())).withSelfRel()
        		)
        	);
        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, usersVO));
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna um único usuário")
    public ResponseEntity<SuccessResponse> findById(@PathVariable("id") Long id){

        UserVO userVO = userService.findById(id);
        userVO.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, userVO));
    }

    @PostMapping("")
    @ApiOperation("Cadastra um usuário na base de dados")
    public ResponseEntity<SuccessResponse> save(@RequestBody UserVO userVO){

        UserVO userCreated = userService.save(userVO);
        userVO.add(linkTo(methodOn(UserController.class).findById(userVO.getKey())).withSelfRel());
        return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED, userCreated));
    }

    @PostMapping("/login")
    @ApiOperation("Retorna o usuário com o e-mail e senha especificados")
    public ResponseEntity<SuccessResponse> login(@RequestBody UserVO userVO){

        UserVO userLogged = userService.login(userVO);
        userVO.add(linkTo(methodOn(UserController.class).findById(userVO.getKey())).withSelfRel());
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, userLogged));
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza um usuário na base de dados")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestBody UserVO userVO){

        UserVO userUpdated = userService.update(id, userVO);
        userVO.add(linkTo(methodOn(UserController.class).findById(userVO.getKey())).withSelfRel());
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, userUpdated));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta um usuário na base de dados")
    public ResponseEntity<SuccessResponse> deleteById(@PathVariable("id") Long id){

        userService.deleteById(id);
        return ResponseEntity.status(204).body(new SuccessResponse(HttpStatus.NO_CONTENT, "Usuário deletado com sucesso"));
    }

}
