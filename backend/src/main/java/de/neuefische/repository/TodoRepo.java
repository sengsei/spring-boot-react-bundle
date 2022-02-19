package de.neuefische.repository;

import de.neuefische.TodoElement;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepo {
    private final List<TodoElement> todoList = new ArrayList<>();

    public List<TodoElement> getTodoList(){
        return todoList;
    }

    public Optional<TodoElement> getTodoElementByTitle(String title){
        return todoList.stream().filter(e -> e.getTitle().equals(title)).findFirst();
    }

    public List<TodoElement> getAllCompletedTodoElements(){
        return getTodoList().stream().filter(TodoElement::isState).toList();
    }

    public List<TodoElement> getAllUncompletedTodoElements(){
        return getTodoList().stream().filter(e -> !e.isState()).toList();
    }

    public void addTodo(TodoElement todoElement) {
        todoList.add(todoElement);
    }

    public void  setStateCompleted(String id) {
        todoList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst().orElseThrow()
                .setState(true);
    }
}
