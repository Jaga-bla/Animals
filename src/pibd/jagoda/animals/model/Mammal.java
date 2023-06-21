package pibd.jagoda.animals.model;

public class Mammal extends Animal {


    private String breed;
    private MammalType mammalType;


    public Mammal(Owner owner, String name, Sex sex, int age, String breed, MammalType mammalType) {
        super(owner, name, sex, age);
        this.breed = breed;
        this.mammalType = mammalType;
    }

    public void info() {
        System.out.println(mammalType.getValuePL() + ". Zwierzę nr " + index);
        System.out.println("   właściciel:    " + owner.getNameAndSurname());
        System.out.println("   imie:          " + name);
        System.out.println("   płeć:          " + sex.getValueAnimal());
        System.out.println("   wiek:          " + age);
        System.out.println("   rasa:          " + breed);
    }
    public MammalType getMammalType() {
        return mammalType;
    }
    public String getAsString(){
        return ("mammal "+ index+" "+name+" "+" "+sex+" "+age+" "+breed);
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
}