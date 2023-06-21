package pibd.jagoda.animals.model;

public enum Sex {

    MALE("Samiec", "Mężczyzna"),
    FEMALE("Samica", "Kobieta");

    private String valueAnimal;
    private String valueHuman;

    Sex(String valueAnimal, String valueHuman) {
        this.valueAnimal = valueAnimal;
        this.valueHuman = valueHuman;
    }

    public String getValueAnimal() {
        return valueAnimal;
    }

    public String getValueHuman() {
        return valueHuman;
    }
}

