package pibd.jagoda.animals.model;

public enum MammalType {
    CAT("Kot", "kota"),
    DOG("Pies", "psa");
    private String valuePL;
    private String valueVariable;

    MammalType(String valuePL, String valueVariable) {
        this.valuePL = valuePL;
        this.valueVariable = valueVariable;
    }

    public String getValuePL() {
        return valuePL;
    }

    public String getValueVariable() {
        return valueVariable;
    }
}

