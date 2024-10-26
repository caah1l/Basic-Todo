package com.example.TodoAPI;

import org.springframework.stereotype.Service;

@Service("fakeTodoService1")
public class FakeTodoService1 implements TodoService{
    @TimeMonitor
    @Override
    //below method is our joinpoint
    public String doSomething() {
        for(long i = 0; i < 1000000000L; i++) {}
        return " another task";
    }
}
