package pibd.jagoda.animals.model;

public abstract class Subject {
    protected String name;
    protected Sex sex;
    protected int age;

    public Subject(String name, Sex sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public abstract void info();

    /////////////////////////////getters and setters///////////////////////////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAgeint() {
        return age;
    }

    public String getAgeString() {
        String result = this.age + " lat";
        if (age == 1) {
            result = (this.age + " rok");
            return result;
        }
        int lastDigit = age % 10;
        if (lastDigit == 2 || lastDigit == 3 || lastDigit == 4) {
            result = (this.age + " lata");
            return result;
        }
        return result;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
