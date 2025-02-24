package br.com.rodrigofariasvieira.todolist.task;

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
@Entity(name = "tb_tasks")
public class TaskModel {
    
/* Coisas que task tem que ter
 * 
 * -ID
 * -Usuário(Id_Usuario)
 * -Descrição
 * -Titulo
 * -Data de criação
 * -Data de termino
 * -Prioridade
 * 
 */

//Atributos da tarefa
 @Id
 @GeneratedValue(generator = "UUID") //Gera um UUID da task
 private UUID id;
 private String description;
 @Column(length = 50)   
 private String title;
 private LocalDateTime startAt;
 private LocalDateTime endAt;
 private String priority;

//Atrelamento do usuário a tarefa
 private UUID idUser;
 //Atributo que determina quando a tarefa foi criada
 @CreationTimestamp
 private LocalDateTime createdAt;


 private void setTitle(String title) throws Exception {
    if (title.length() > 50) {
        throw new Exception("O título não pode ter mais de 50 caracteres");
        
 }
 
    this.title = title;
    
 







}

}
