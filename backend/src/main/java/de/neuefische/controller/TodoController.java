package de.neuefische.controller;

import de.neuefische.TodoElement;
import de.neuefische.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@CrossOrigin
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
    public Collection<TodoElement> addTodo(@RequestBody TodoElement todoElement) {
        todoService.addTodo(todoElement);
        return todoService.getTodoList();
    }

    @PutMapping("/{id}")
    public Collection<TodoElement> changeTodo(@PathVariable String id, @RequestBody TodoElement todo) {
        todoService.changeTodo(id, todo);
        return todoService.getTodoList();
    }

    @DeleteMapping("/{id}")
    public Collection<TodoElement> deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
        return todoService.getTodoList();
    }

}
