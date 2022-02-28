package de.neuefische.service;

import de.neuefische.TodoElement;
import de.neuefische.TodoState;
import de.neuefische.repository.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;


@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepo todoRepo;

    public void addTodo(TodoElement todoElement) {
        todoRepo.save(todoElement);
    }

    public TodoElement getTodoElementById(String id) {
        return todoRepo.getTodoElementByID(id);
    }

    public Collection<TodoElement>getTodoList() {
        return todoRepo.getTodoList();
    }

    public void deleteTodo(String id){
        todoRepo.delete(id);
    }

    public void changeTodo(String id, TodoElement changedTodo){
        TodoElement todoElement = todoRepo.getTodoElementByID(id);

        todoElement.setTitle(changedTodo.getTitle());
        todoElement.setState(changedTodo.getState());
        todoElement.setText(changedTodo.getText());
        todoRepo.save(todoElement);
    }


    public void deleteCheckedTodos() {
        todoRepo.getTodoList().stream().filter(todo -> todo.getState() == TodoState.Done)
                .forEach(todo -> todoRepo.delete(todo.getId()));
    }
}
