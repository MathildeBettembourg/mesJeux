package com.example.game.entities;

import com.example.game.utils.BoardGameDTOOut;
import com.example.game.utils.MyEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    @Enumerated(EnumType.STRING)
    private MyEnum.Experience experience;


    @Column
    private Integer numberOfGamePlayed;

    @Column
    private Integer numberOfGameWon;

    @Column
    private Date dateOfBirth;

    /**
     * Liste des jeux joués -1
     */

    @ManyToMany( mappedBy = "playersList", fetch = FetchType.LAZY)
@JsonIgnore
    private List<BoardGame> listBoardGame;


    /**todo check service
     * Lkst parties gagnées one to many 3
     * Write ok
     */

    @OneToMany(mappedBy = "winner", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Game> gameWon = new ArrayList<>();
    /**
     * liste des parties joués -2
     */

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "player_game",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> games;


}
//3 verifiee ok