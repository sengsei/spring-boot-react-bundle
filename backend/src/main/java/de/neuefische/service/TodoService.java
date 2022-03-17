package de.neuefische.service;

import de.neuefische.TodoElement;
import de.neuefische.TodoState;
import de.neuefische.repository.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepo todoRepo;

    public void addTodo(TodoElement todoElement) {
        todoRepo.save(todoElement);
    }

    public TodoElement getTodoElementById(String id) {
        Optional<TodoElement> todo = todoRepo.findById(id);
        if (todo.isPresent()){
           return todo.get();
        }
        return new TodoElement();
    }

    public Collection<TodoElement>getTodoList() {
        return todoRepo.findAll();
    }

    public void deleteTodo(String id){
       todoRepo.deleteById(id);
    }

    public void changeTodo(String id, TodoElement changedTodo){
        Optional<TodoElement> todo = todoRepo.findById(id);
        if (todo.isPresent()){
            TodoElement todoUnwrapped = todo.get();
            todoUnwrapped.setTitle(changedTodo.getTitle());
            todoUnwrapped.setState(changedTodo.getState());
            todoUnwrapped.setText(changedTodo.getText());
            todoRepo.save(todoUnwrapped);
        }
    }

    public void deleteCheckedTodos() {
        todoRepo.findAll().stream().filter(todo -> todo.getState() == TodoState.Done)
                .toList()
                .forEach(todo -> todoRepo.delete(todo));
    }
}
