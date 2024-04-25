package nemo.baeldung.testcontainers.support;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class MiddleEarthCharactersController {
    private final MiddleEarthCharactersRepository repository;

    @GetMapping
    public List<MiddleEarthCharacter> findByRace(@RequestParam String race) {
        return repository.findAllByRace(race);
    }

    @PostMapping
    public MiddleEarthCharacter save(@RequestBody MiddleEarthCharacter character) {
        return repository.save(character);
    }
}
