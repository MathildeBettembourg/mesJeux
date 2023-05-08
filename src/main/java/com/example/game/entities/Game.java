package com.example.game.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column
    private Date dateOfGame;

    @Column
    private Integer numberOfPlayers;

    @Column
    private Integer duration;


    /**
     *
     * Liste de joueurs gagnants de la partie many  to one- 3 Write ok
     */
    //gagnant Many to one gagnant -3
    @ManyToOne
    @JoinColumn(name = "players_id")
    private Players winner;

    //un jeu par partie Many to One -4

    @ManyToOne
    @JoinColumn(name = "board_game_id")
    private BoardGame boardGamePlayed;


    @ManyToMany( mappedBy ="games")
            private List<Players>playersOfThatGame=new ArrayList<Players>();
}
