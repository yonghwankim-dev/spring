package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;


class GameDTOTest {

    ModelMapper mapper;
    GameRepository gameRepository;

    @BeforeEach
    public void setup() {
        this.mapper = new ModelMapper();
        this.gameRepository = new GameRepository();
    }

    @Test
    public void whenMapGameWithExactMatch_thenConvertsToDTO() {
        // when similar source object is provided
        Game game = Game.builder().id(1L).name("Game 1").build();
        GameDTO gameDTO = this.mapper.map(game, GameDTO.class);

        // then it maps by default
        assertEquals(game.getId(), gameDTO.getId());
        assertEquals(game.getName(), gameDTO.getName());
    }

    @Test
    public void whenMapGameWithBasicPropertyMapping_thenConvertsToDTO() {
        // setup
        TypeMap<Game, GameDTO> propertyMapper = this.mapper.createTypeMap(Game.class,
            GameDTO.class);
        propertyMapper.addMapping(Game::getTimestamp, GameDTO::setCreationTime);

        // when field names are different
        Game game = Game.builder().id(1L).name("Game 1").build();
        game.setTimestamp(Instant.now().getEpochSecond());
        GameDTO gameDTO = this.mapper.map(game, GameDTO.class);

        // then it maps via property mapper
        assertEquals(game.getId(), gameDTO.getId());
        assertEquals(game.getName(), gameDTO.getName());
        assertEquals(game.getTimestamp(), gameDTO.getCreationTime());
    }

    @Test
    public void whenMapGameWithDeepMapping_thenConvertsToDTO() {
        // setup
        TypeMap<Game, GameDTO> propertyMapper = this.mapper.createTypeMap(Game.class,
            GameDTO.class);
        // add deep mapping to flatten source's Player object into a single field in destination
        propertyMapper.addMappings(
            mapper -> mapper.map(src -> src.getCreator().getName(), GameDTO::setCreator)
        );

        // when map between different hierarchies
        Game game = Game.builder().id(1L).name("Game 1").build();
        game.setCreator(Player.builder().id(1L).name("Jhon").build());
        GameDTO gameDTO = this.mapper.map(game, GameDTO.class);

        // then
        assertEquals(game.getCreator().getName(), gameDTO.getCreator());
    }

    @Test
    public void whenMapGameWithSkipIdProperty_thenConvertsToDTO() {
        // setup
        TypeMap<Game, GameDTO> propertyMapper = this.mapper.createTypeMap(Game.class,
            GameDTO.class);
        propertyMapper.addMappings(mapper -> mapper.skip(GameDTO::setId));

        // when id is skipped
        Game game = Game.builder().id(1L).name("Game 1").build();
        GameDTO gameDTO = this.mapper.map(game, GameDTO.class);

        // then destination id is null
        assertNull(gameDTO.getId());
        assertEquals(game.getName(), gameDTO.getName());
    }

    @Test
    public void whenMapGameWithCustomConverter_thenConvertsToDTO() {
        // setup
        TypeMap<Game, GameDTO> propertyMapper = this.mapper.createTypeMap(Game.class,
            GameDTO.class);
        Converter<Collection, Integer> collectionToSize = c -> c.getSource().size();
        propertyMapper.addMappings(
            mapper -> mapper.using(collectionToSize).map(Game::getPlayers, GameDTO::setTotalPlayers)
        );

        // when collection to size converter is provided
        Game game = Game.builder().build();
        game.addPlayer(Player.builder().id(1L).name("john").build());
        game.addPlayer(Player.builder().id(2L).name("Bob").build());
        GameDTO gameDTO = this.mapper.map(game, GameDTO.class);

        // then it maps the size to a custom field
        assertEquals(2, gameDTO.getTotalPlayers());
    }

    @Test
    public void whenUsingProvider_thenMergesGameInstances() {
        // setup
        TypeMap<Game, Game> propertyMapper = this.mapper.createTypeMap(Game.class, Game.class);
        // a provider to fetch a Game instance from a repository
        Provider<Game> gameProvider = p -> this.gameRepository.findById(1L);
        propertyMapper.setProvider(gameProvider);

        // when a state for update is given
        Game update = Game.builder().id(1L).name("Game Updated!").build();
        update.setCreator(Player.builder().id(1L).name("John").build());
        Game updatedGame = this.mapper.map(update, Game.class);

        // then it merges the updates over on the provided instance
        assertEquals(1L, updatedGame.getId().longValue());
        assertEquals("Game Updated!", updatedGame.getName());
        assertEquals("John", updatedGame.getCreator().getName());
    }

    @Test
    public void whenUsingConditionalIsNull_thenMergesGameInstancesWithoutOverridingId() {
        // setup
        TypeMap<Game, Game> propertyMapper = this.mapper.createTypeMap(Game.class, Game.class);
        propertyMapper.setProvider(p -> this.gameRepository.findById(2L));
        propertyMapper.addMappings(
            mapper -> mapper.when(Conditions.isNull()).skip(Game::getId, Game::setId));

        // when game has no id
        Game update = Game.builder().id(null).name("Not Persisted Game!").build();
        Game updatedGame = this.mapper.map(update, Game.class);

        // then destination game id is not overwritten
        assertEquals(2L, updatedGame.getId().longValue());
        assertEquals("Not Persisted Game!", updatedGame.getName());
    }

    @Test
    public void whenUsingCustomConditional_thenConvertsDTOSkipsZeroTimestamp() {
        // setup
        TypeMap<Game, GameDTO> propertyMapper = this.mapper.createTypeMap(Game.class,
            GameDTO.class);
        Condition<Long, Long> hasTimestamp = ctx -> ctx.getSource() != null && ctx.getSource() > 0;
        propertyMapper.addMappings(
            mapper -> mapper.when(hasTimestamp).map(Game::getTimestamp, GameDTO::setCreationTime)
        );

        // when game has zero timestamp
        Game game = Game.builder().id(1L).name("Game 1").build();
        game.setTimestamp(0L);
        GameDTO gameDTO = this.mapper.map(game, GameDTO.class);

        // then timestamp field is not mapped
        assertEquals(game.getId(), gameDTO.getId());
        assertEquals(game.getName(), gameDTO.getName());
        assertNotEquals(0L, gameDTO.getCreationTime());

        // when game has timestamp greater than zero
        game.setTimestamp(Instant.now().getEpochSecond());
        gameDTO = this.mapper.map(game, GameDTO.class);

        // then timestamp field is mapped
        assertEquals(game.getId(), gameDTO.getId());
        assertEquals(game.getName(), gameDTO.getName());
        assertEquals(game.getTimestamp(), gameDTO.getCreationTime());
    }

    @Test
    public void whenUsingLooseMappingStrategy_thenConvertsToDomainAndDTO() {
        // setup
        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        // when dto has flat fields for GameSetting
        GameDTO gameDTO = new GameDTO();
        gameDTO.setMode(GameMode.TURBO);
        gameDTO.setMaxPlayers(8);
        Game game = this.mapper.map(gameDTO, Game.class);

        // then it converts to inner objects without property mapper
        assertEquals(gameDTO.getMode(), game.getSettings().getMode());
        assertEquals(gameDTO.getMaxPlayers(), game.getSettings().getMaxPlayers());

        // when the GameSetting's field names match
        game = new Game();
        game.setSettings(GameSettings.builder().mode(GameMode.NORMAL).maxPlayers(6).build());
        gameDTO = this.mapper.map(game, GameDTO.class);

        // then it flattens the fields on dto
        assertEquals(game.getSettings().getMode(), gameDTO.getMode());
        assertEquals(game.getSettings().getMaxPlayers(), gameDTO.getMaxPlayers());
    }

    @Test
    public void whenConfigurationSkipNullEnabled_thenConvertsToDTO() {
        // setup
        this.mapper.getConfiguration().setSkipNullEnabled(true);
        TypeMap<Game, Game> propertyMap = this.mapper.createTypeMap(Game.class, Game.class);
        propertyMap.setProvider(p -> this.gameRepository.findById(2L));

        // when game has no id
        Game update = Game.builder().id(null).name("Not Persisted Game!").build();
        Game updatedGame = this.mapper.map(update, Game.class);

        // then destination game id is not overwritten
        assertEquals(2L, updatedGame.getId().longValue());
        assertEquals("Not Persisted Game!", updatedGame.getName());
    }

}
