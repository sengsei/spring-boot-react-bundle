package de.neuefische.service;

import de.neuefische.TodoElement;
import de.neuefische.repository.TodoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public List<TodoElement> getTodoList(){
        return todoRepo.getTodoList();
    }

    public void addTodo(TodoElement todoElement) {
        todoRepo.addTodo(todoElement);
    }

    public Optional<TodoElement> getTodoElementByTitle(String title) {
        return todoRepo.getTodoElementByTitle(title);
    }

    public List<TodoElement> getAllCompletedTodoElements() {
        return todoRepo.getAllCompletedTodoElements();
    }

    public List<TodoElement> getAllUncompletedTodoElements() {
        return todoRepo.getAllUncompletedTodoElements();
    }

    public void setState(String id){
        todoRepo.setStateCompleted(id);
    }
}
