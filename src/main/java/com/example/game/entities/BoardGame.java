package com.example.game.entities;


import com.example.game.utils.MyEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "boardgame")
public class BoardGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer nbOfPlayersMax;
    @Column
    private Integer nbOfPlayersMin;
    @Column
    private Integer time;
    @Column
    @Enumerated(EnumType.STRING)
    private MyEnum.Experience experience;
    @Column
    @Enumerated(EnumType.STRING)
    private MyEnum.Type type;
    @Column
    @Enumerated(EnumType.STRING)
    private MyEnum.Univers univers;

    public BoardGame(String name, Integer nbOfPlayersMax, Integer nbOfPlayersMin, Integer time, MyEnum.Experience experience, MyEnum.Type type, MyEnum.Univers univers, List<Players> playersList) {
        this.name = name;
        this.nbOfPlayersMax = nbOfPlayersMax;
        this.nbOfPlayersMin = nbOfPlayersMin;
        this.time = time;
        this.experience = experience;
        this.type = type;
        this.univers = univers;
        this.playersList = playersList;
    }

    public BoardGame(Long id, String name, Integer nbOfPlayersMax, Integer nbOfPlayersMin, Integer time, MyEnum.Experience experience, MyEnum.Type type, MyEnum.Univers univers, List<Players> playersList) {
        this.id = id;
        this.name = name;
        this.nbOfPlayersMax = nbOfPlayersMax;
        this.nbOfPlayersMin = nbOfPlayersMin;
        this.time = time;
        this.experience = experience;
        this.type = type;
        this.univers = univers;
        this.playersList = playersList;
    }

    public BoardGame(Long id) {
        this.id = id;
    }

    /**
     * A boardgames can have been played by many players - 1
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "boardgame_players",
            joinColumns = @JoinColumn(name = "boardgame_id"),
            inverseJoinColumns = @JoinColumn(name = "players_id"))
    @JsonIgnore
    private List<Players> playersList;
    /**
     * there is several players by game - 4
     */
    @OneToMany(mappedBy = "boardGamePlayed", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Game> listOfgamesOfThatBoardGamePlayed = new ArrayList<>();
}
