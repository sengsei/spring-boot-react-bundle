package de.neuefische.controller;

import de.neuefische.model.TodoElement;
import de.neuefische.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@CrossOrigin
public class TodoController {
    private final TodoService todoService;


    @GetMapping
    public Collection<TodoElement> getTodoList() {
        return todoService.getTodoList();
    }

    @GetMapping("/{id}")
    public TodoElement getTodoElementById(@PathVariable String id){
        return todoService.getTodoElementById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<TodoElement> addTodo(@RequestBody TodoElement todoElement, Principal principal) {
        String email = principal.getName();
        todoService.addTodo(todoElement, email);
        return todoService.getTodoList();
    }

    @PutMapping("/{id}")
    public Collection<TodoElement> changeTodo(@PathVariable String id, @RequestBody TodoElement todo) {
       todoService.changeTodo(id, todo);
       return todoService.getTodoList();
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
    }

    @DeleteMapping()
    public Collection<TodoElement> deleteCheckedTodos() {
        todoService.deleteCheckedTodos();
        return todoService.getTodoList();
    }

}
