package br.com.rodrigofariasvieira.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface IUserRepository extends JpaRepository<UserModel, UUID>{ //Repositorio para utilizar o JPA em UserModel
    UserModel findByUsername(String username);
    
}
