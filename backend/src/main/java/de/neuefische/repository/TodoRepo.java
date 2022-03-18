package de.neuefische.repository;

import de.neuefische.model.TodoElement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends MongoRepository<TodoElement, String> {
        List<TodoElement> findAllByUserId(String userID);
        void deleteTodoElementByIdAndUserId(String id, String userID);
}
