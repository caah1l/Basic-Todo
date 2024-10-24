package com.example.TodoAPI;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// @Component  // this will make sure that TodoService class is going to handle by SPring
@Service("fakeTodo")
public class FakeTodoService implements TodoService {

    public String doSomething() {
        return "Something";
    }
}
