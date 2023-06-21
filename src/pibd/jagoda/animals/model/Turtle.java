package pibd.jagoda.animals.model;

public class Turtle extends Animal {



    private TurtleType type;

    public Turtle(Owner owner, String name, Sex sex, int age, TurtleType type) {
        super(owner, name, sex, age);
        this.type = type;
    }

    public void info() {
        System.out.println("Żółw. Zwierzę nr " + index);
        System.out.println("   właściciel:    " + owner.getNameAndSurname());
        System.out.println("   imie:          " + name);
        System.out.println("   płeć:          " + sex.getValueAnimal());
        System.out.println("   wiek:          " + age);
        System.out.println("   typ:           " + type.getValue());

    }
    public String getAsString(){
        return ("turtle "+ index+" "+name+" "+" "+sex+" "+age+" "+type);
    }
    public void setType(TurtleType type) {
        this.type = type;
    }
    public TurtleType getType() {
        return type;
    }
}
