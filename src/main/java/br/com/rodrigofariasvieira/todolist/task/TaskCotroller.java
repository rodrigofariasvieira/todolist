package br.com.rodrigofariasvieira.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.rodrigofariasvieira.todolist.utils.Utils;


@RestController
@RequestMapping("/tasks")
public class TaskCotroller {



    @Autowired
    private ITaskRepository taskRepository;

    //Metodo publico para criar a task
    
    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) { //Recebe um objeto do tipo TaskModel  
    
    var idUser = request.getAttribute("idUser");
    taskModel.setIdUser((UUID) idUser);
    
    var currentDate = LocalDateTime.now(); //Pega a data atual
    if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inicio / Data de término não pode ser menor que a data atual"); //Retorna um erro


    }

    if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){ //Verifica se a data de inicio é maior que a data de termino
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de início deve ser menor que a data de término"); //Retorna um erro

    }
    var task = this.taskRepository.save(taskModel); //Salva o objeto no banco(TaskModel) de dados
    return ResponseEntity.status(HttpStatus.OK).body(task); //Retorna a task

    }
    
    
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID)idUser);
        return tasks;
    }
    
    @PutMapping ("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id ){
        
       var task = this.taskRepository.findById(id).orElse(null);

       if (task == null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
       }
       
       var idUser = request.getAttribute("idUser");
       if (!task.getIdUser().equals(idUser)) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Você não tem permissão para alterar essa tarefa");
       }
       
       Utils.copyNonNullProperties(taskModel, task);
       
       var taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.ok().body(taskUpdated);


    }

}
