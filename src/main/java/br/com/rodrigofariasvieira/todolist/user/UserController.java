package br.com.rodrigofariasvieira.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;


@RestController
@RequestMapping ("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;


    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) { //Recebe um JSON e converte para um objeto UserModel
    
    var user = this.userRepository.findByUsername(userModel.getUsername()); //Verifica se o usuário já existe
    if (user != null) { 
        System.out.println("Usuário já existe");
        //Retornar um erro
        //Retornar Status Code 
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");

    }
    
    var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()); //Criptografa a senha

    userModel.setPassword(passwordHashred); //Substitui a senha pela senha criptografada

    var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.OK).body(userCreated); 
      
    
       

    }
    
}
