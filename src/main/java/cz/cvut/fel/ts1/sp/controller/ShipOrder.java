package cz.cvut.fel.ts1.sp.controller;

public enum ShipOrder {
    ID("id"),
    SPEED("speed"),
    DATE("prodDate"),
    RATING("rating");

    private String fieldName;

    ShipOrder(String fieldName) {
        this.fieldName = fieldName;
    }

}