package com.compek;

public enum ConnectionType {
    /**
     * Соедиенение с текстовыми данными
     * */
    TEXT("text"),

    /**
     * Соединение с данными в виде объектов
     * */
    OBJECT("object")
    ;
    private final String type;

    ConnectionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ConnectionType fromString(String str) {
        switch (str) {
            case "object":
                return OBJECT;
            case "text":
            default:
                return TEXT;
        }
    }
}
