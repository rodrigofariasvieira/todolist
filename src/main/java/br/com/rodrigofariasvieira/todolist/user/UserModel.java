package br.com.rodrigofariasvieira.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Cria um construtor vazio
@Entity (name = "tb_users")

// 
public class UserModel {

 // Atributos públicos
@Id //Annotation do JPA selecionar o JAKARTA
@GeneratedValue (generator = "UUID")
private UUID id; // Chave primária da tabela Users



@Column (unique = true) //Restrição de criação de usuário duplicado
 private String username;
 private String name;
 private String password;



 @CreationTimestamp
 private LocalDateTime createdAt;







 
}
    


