package de.neuefische.repository;

import de.neuefische.model.TodoElement;
import de.neuefische.model.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument>findByEmail(String email);
}
