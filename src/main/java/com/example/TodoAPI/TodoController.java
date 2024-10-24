package com.example.TodoAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    //@Autowired  //dont need to do constructer baed injection manually, it automatically created object imlicitly
   // @Qualifier("fakeTodoervice1") //it will Consider FakeTodoService1 as we have mentioned "newFakeTodo" in @Service Annotation


    private static List<Todo> todoList;
    private TodoService todoService;   //for fakeTodoervice1
    private TodoService todoService1;  // for fakeTodo

    public TodoController(@Qualifier("fakeTodoService1") TodoService todoService,
                          @Qualifier("fakeTodo") TodoService todoService1) {
        this.todoService = todoService;
        this.todoService1 = todoService1;
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,01,"1st task",false));
        todoList.add(new Todo(2,01,"2st task",true));
    }

    @GetMapping("/todos")
    public List<Todo> getTodoList() {
        System.out.println("api is doing" + todoService.doSomething());
        System.out.println("api is doing" + todoService1.doSomething());
        return todoList;
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody Todo newTask) {
        todoList.add(newTask);
        return newTask;
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<?> getTaskbyId(@PathVariable long todoId) {
        for(Todo todo : todoList) {
            if(todo.getId() == todoId) {
                return ResponseEntity.ok(todo);
            }
        }
        //return ResponseEntity.notFound().build();
        /*return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body((Todo) Collections.singletonMap("message", "Todo not found")); */

        String jsonResponse = "Todo not found";

        return new ResponseEntity<>(jsonResponse, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/todos/{todoId}")
    public void removeTask(@PathVariable long todoId) {
        for(Todo todo : todoList){
            if(todo.getId() == todoId) {
                todoList.remove(todo);
                //return ResponseEntity.ok(todo);
            }
        }
    }

    @PatchMapping("/todos/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable long todoId, @RequestBody Todo update ) {
         for(Todo todo : todoList) {
             if(todo.getId() == todoId) {
                 todo.setTitle(update.getTitle());
                 todo.setCompleted(update.isCompleted());
                 return ResponseEntity.ok(todo);
             }
         }

         return ResponseEntity.notFound().build();
    }

    // Filteting on th basis of completion
    @GetMapping("/todos/filtered")
    public ResponseEntity<List<Todo>> getCompletedTodos(@RequestParam(required = false) Boolean isCompleted) {
        ArrayList<Todo> filteredTodo = new ArrayList<>();
        if (isCompleted == null) {
            return ResponseEntity.ok(todoList);
        }
        for(Todo todo : todoList) {
            if(todo.isCompleted() == isCompleted) {
                filteredTodo.add(todo);
            }
        }
        return ResponseEntity.ok(filteredTodo);
    }


}


