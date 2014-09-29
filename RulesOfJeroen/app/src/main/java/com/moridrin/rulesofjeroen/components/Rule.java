package com.moridrin.rulesofjeroen.components;

/**
 * Created by Jeroen on 14/09/28.
 */
public class Rule {

    private String name;
    private String description;
    private int rank;

    public Rule(String name, String description, int rank) {
        this.name = name;
        this.description = description;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRank() {
        return rank;
    }
}
