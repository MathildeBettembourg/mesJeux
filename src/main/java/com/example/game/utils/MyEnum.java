package com.example.game.utils;

import lombok.Data;

@Data
public class MyEnum {
    public enum Experience{DEBUTANT, MOYEN, AVANCE, EXPERT};
    public enum Type{COOPERATIF, COMPETITIF};
    public enum Univers{NEUTRE,FANTAISIE, PASSE, FUTUR};
}
