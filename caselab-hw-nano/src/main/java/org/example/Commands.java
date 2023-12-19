package org.example;

public enum Commands {

    CREATE ("-create"),
    DELETE ("-delete"),
    READ ("-read"),
    REWRITE ("-write"),
    WRITE ("-writeA");

    private final String value;

    Commands(String value) {
        this.value = value;
    }

    public static Commands getEnum(String value) {
        for (Commands command: Commands.values()) {
            if (command.value.equals(value)) {
                return command;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return value + ";\n";
    }
}