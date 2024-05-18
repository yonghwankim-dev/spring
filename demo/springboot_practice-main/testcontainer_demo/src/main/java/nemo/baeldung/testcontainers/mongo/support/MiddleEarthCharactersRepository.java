package nemo.baeldung.testcontainers.mongo.support;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiddleEarthCharactersRepository extends MongoRepository<MiddleEarthCharacter, String> {
	List<MiddleEarthCharacter> findAllByRace(String race);
}
