package com.appstra.users.controller;

import com.appstra.users.dto.MassiveUsersDTO;
import com.appstra.users.entity.Person;
import com.appstra.users.entity.User;
import com.appstra.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveuser")
    @Operation(summary = "Guardar Usuario", description = "Guardar usuario")
    public ResponseEntity<User> saveUser (@Validated @RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PutMapping("/updateuser")
    @Operation(summary = "actualiza Usuario", description = "actualiza Usuario")
    public ResponseEntity<User> updateUser (@Validated @RequestBody User user){
        return ResponseEntity.ok(userService.upDateUser(user));
    }

    @DeleteMapping("/deleteuser/{userId}")
    @Operation(summary = "Elimina Usuario", description = "Elimina Usuario")
    public ResponseEntity<Boolean> deleteUser (@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @GetMapping("/listuser")
    @Operation(summary = "Lista Usuarios", description = "Lista Usuarios")
    public ResponseEntity<List<User>> listState (){
        return ResponseEntity.ok(userService.listUser());
    }

    @PostMapping(value = "/uploadMassiveUsers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Carga usuarios mesivos", description = "carga documento Ecxel con los usuarios y contraseñas")
    public ResponseEntity<List<MassiveUsersDTO>> uploadMassiveUsers(
            @RequestPart("document") MultipartFile document) {
        return ResponseEntity.ok(userService.uploadMassiveUsers(document));
    }

    @GetMapping("createUser/{personId}")
    @Operation(summary = "Crear Usuario con personId", description = "Crear Usuario con personId")
    public ResponseEntity<Person> createUserpersonId (@PathVariable("personId") Integer personId){
        return ResponseEntity.ok(userService.createUserpersonId(personId));
    }
}
