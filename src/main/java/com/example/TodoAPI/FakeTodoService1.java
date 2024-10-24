package com.example.TodoAPI;

import org.springframework.stereotype.Service;

@Service("fakeTodoService1")
public class FakeTodoService1 implements TodoService{
    @Override
    public String doSomething() {
        return "Doing another task";
    }
}
