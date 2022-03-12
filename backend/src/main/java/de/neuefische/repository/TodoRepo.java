package de.neuefische.repository;

import de.neuefische.TodoElement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends MongoRepository<TodoElement, String> {

}
