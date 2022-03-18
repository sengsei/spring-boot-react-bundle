package de.neuefische.controller;

import de.neuefische.model.TodoElement;
import de.neuefische.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
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
    public Collection<TodoElement> getTodoList(Principal principal) {
        return todoService.getTodoList(principal);
    }

    @GetMapping("/{id}")
    public TodoElement getTodoElementById(@PathVariable String id){
        return todoService.getTodoElementById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<TodoElement> addTodo(@RequestBody TodoElement todoElement, Principal principal) {
        todoService.addTodo(todoElement, principal);
        return todoService.getTodoList(principal);
    }

    @PutMapping("/{id}")
    public Collection<TodoElement> changeTodo(@PathVariable String id, @RequestBody TodoElement todo, Principal principal) {
       todoService.changeTodo(id, todo);
       return todoService.getTodoList(principal);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id, Principal principal) {
        todoService.deleteTodo(id, principal);
    }

    @DeleteMapping()
    public Collection<TodoElement> deleteCheckedTodos(Principal principal) {
        todoService.deleteCheckedTodos();
        return todoService.getTodoList(principal);
    }

}
