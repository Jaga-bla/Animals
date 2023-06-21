package pibd.jagoda.animals.model;

public enum TurtleType {
    LAND("Zółw Lądowy"),
    MARSH("Zółw Błotny"),
    SEA("Zółw Morski");

    private String value;

    TurtleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
