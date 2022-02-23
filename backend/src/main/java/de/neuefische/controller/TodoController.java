package de.neuefische.controller;

import de.neuefische.TodoElement;
import de.neuefische.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public Collection<TodoElement> getTodoList(){
        return todoService.getTodoList();
    }

    @GetMapping("/{id}")
    public TodoElement getTodoElementById(@PathVariable String id){
        return todoService.getTodoElementById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTodo(@RequestBody TodoElement todoElement) {
        todoService.addTodo(todoElement);
    }



}
