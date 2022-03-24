package de.neuefische.service;

import de.neuefische.model.TodoElement;
import de.neuefische.model.TodoState;
import de.neuefische.model.UserDocument;
import de.neuefische.repository.TodoRepo;
import de.neuefische.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class TodoServiceTest {


    @Test
    void shouldAddNewTodo() {
        TodoElement todo = new TodoElement();
        todo.setTitle("Sport");
        todo.setState(TodoState.Open);

        TodoElement savedTodo = new TodoElement();
        savedTodo.setTitle("Sport");
        savedTodo.setState(TodoState.Open);

        UserDocument user = new UserDocument("123", "user@mail.de", "user","user","User");

        Principal principal = () -> "user@mail.de";

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findByEmail("user@mail.de")).thenReturn(Optional.of(user));

        TodoRepo todoRepo = Mockito.mock(TodoRepo.class);
        when(todoRepo.save(todo)).thenReturn(savedTodo);

        TodoService todoService = new TodoService(todoRepo, userRepository);
        TodoElement actual = todoService.addTodo(todo, principal);

        assertThat(actual).isSameAs(savedTodo);
    }

    @Test
    void shouldRetrieveAllTodos() {
        TodoElement todo1 = new TodoElement();
        todo1.setTitle("Sport");
        todo1.setState(TodoState.Open);

        TodoElement todo2 = new TodoElement();
        todo2.setTitle("Java");
        todo2.setState(TodoState.Open);

        UserDocument user = new UserDocument("123", "user@mail.de", "user","user","User");

        Principal principal = () -> "user@mail.de";

        List<TodoElement> todoList = List.of(todo1, todo2);
        TodoRepo repo = Mockito.mock(TodoRepo.class);
        when(repo.findAll()).thenReturn(todoList);

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findByEmail("user@mail.de")).thenReturn(Optional.of(user));

        TodoRepo todoRepo = Mockito.mock(TodoRepo.class);
        when(todoRepo.findAllByUserId("123")).thenReturn(List.of(todo1, todo2));

        TodoService todoService = new TodoService(todoRepo, userRepository);
        Collection<TodoElement> actual = todoService.getTodoList(principal);

        assertThat(actual.size()).isEqualTo(2);


    }

    @Test
    void shouldRetrieveOneTodo() {
        TodoElement todo1 = new TodoElement();
        todo1.setTitle("Sport");
        todo1.setState(TodoState.Open);

        UserDocument user = new UserDocument("123", "user@mail.de", "user","user","User");

        Principal principal = () -> "user@mail.de";

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findByEmail("user@mail.de")).thenReturn(Optional.of(user));

        TodoRepo repo = Mockito.mock(TodoRepo.class);
        when(repo.findById(todo1.getId())).thenReturn(Optional.of(todo1));

        TodoService todoService = new TodoService(repo, userRepository);

        TodoElement actual = todoService.getTodoElementById(todo1.getId());

        assertThat(actual).isEqualTo(todo1);
    }

    @Test
    void shouldDeleteTodo() {
        TodoElement todo1 = new TodoElement();
       todo1.setId("777");

        TodoRepo repo = Mockito.mock(TodoRepo.class);
        when(repo.findById(todo1.getId())).thenReturn(Optional.of(todo1));

        UserDocument user = new UserDocument("123", "user@mail.de", "user","user","User");

        Principal principal = () -> "user@mail.de";

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findByEmail("user@mail.de")).thenReturn(Optional.of(user));

        TodoService todoService = new TodoService(repo, userRepository);

        //When
        todoService.deleteTodo(todo1.getId(), principal);

        //Then
        verify(repo).deleteTodoElementByIdAndUserId("777", "123");
    }

    @Test
    void shouldChangeTodo() {
        TodoElement todo1 = new TodoElement();
        todo1.setId("777");
        todo1.setTitle("Sport");
        todo1.setState(TodoState.Open);

        TodoElement savedTodo = new TodoElement();
        savedTodo.setId("777");
        savedTodo.setTitle("Sport");
        savedTodo.setState(TodoState.Done);

        TodoRepo repo = Mockito.mock(TodoRepo.class);
        when(repo.findById("777")).thenReturn(Optional.of(todo1));

        UserDocument user = new UserDocument("123", "user@mail.de", "user","user","User");

        Principal principal = () -> "user@mail.de";

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findByEmail("user@mail.de")).thenReturn(Optional.of(user));

        TodoService todoService = new TodoService(repo, userRepository);

        todoService.changeTodo("777", savedTodo);

        Mockito.verify(repo).save(savedTodo);
    }

}