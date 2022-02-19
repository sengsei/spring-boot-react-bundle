package de.neuefische.controller;

import de.neuefische.TodoElement;
import de.neuefische.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoElement> getTodoList(){
        return todoService.getTodoList();
    }

    @GetMapping("/{title}")
    public Optional<TodoElement> getTodoElementByTitle(@PathVariable String title){
        return todoService.getTodoElementByTitle(title);
    }

    @GetMapping("/completed")
    public List<TodoElement> getAllCompletedTodoElements() {
        return todoService.getAllCompletedTodoElements();
    }

    @GetMapping("/uncompleted")
    public List<TodoElement> getAllUncompletedTodoElements() {
        return todoService.getAllUncompletedTodoElements();
    }

    @PutMapping("setstate/{id}")
    public void setState(@PathVariable String id){
             todoService.setState(id);
    }

    @PostMapping
    public void addTodo(@RequestBody TodoElement todoElement) {
        todoService.addTodo(todoElement);
    }



}
