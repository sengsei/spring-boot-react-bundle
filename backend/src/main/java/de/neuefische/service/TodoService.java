package de.neuefische.service;

import de.neuefische.model.TodoElement;
import de.neuefische.model.TodoState;
import de.neuefische.model.UserDocument;
import de.neuefische.repository.TodoRepo;
import de.neuefische.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepo todoRepo;
    private final UserRepository userRepository;

    public TodoElement addTodo(TodoElement todoElement, Principal principal) {
        todoElement.setUserId(getUserID(principal));
        return todoRepo.save(todoElement);
    }

    public TodoElement getTodoElementById(String id) {
        Optional<TodoElement> todo = todoRepo.findById(id);
        if (todo.isPresent()){
           return todo.get();
        }
        return new TodoElement();
    }

    public Collection<TodoElement>getTodoList(Principal principal) {
        return todoRepo.findAllByUserId(getUserID(principal));
    }

    public void deleteTodo(String id, Principal principal){
        todoRepo.deleteTodoElementByIdAndUserId(id, getUserID(principal));
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

    public List<TodoElement> findAllByUserId(String email){
        Optional<UserDocument> elem = userRepository.findByEmail(email);
        if (elem.isPresent()){
            return todoRepo.findAllByUserId(elem.get().getId());
        }
        throw new IllegalArgumentException("User doesnt exist!");
    }

    private String getUserID(Principal principal) {
        return userRepository.findByEmail(principal.getName()).get().getId();
    }
}
