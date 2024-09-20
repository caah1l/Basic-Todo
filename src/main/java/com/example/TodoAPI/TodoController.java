package com.example.TodoAPI;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {
    private static List<Todo> todoList;

    public TodoController() {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,01,"1st task",false));
        todoList.add(new Todo(2,01,"2st task",true));
    }
}
