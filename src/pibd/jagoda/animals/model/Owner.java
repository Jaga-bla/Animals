package pibd.jagoda.animals.model;

import java.util.ArrayList;
import java.util.List;

public class Owner extends Subject {
    private int indexCount = 0;


    private String surname;
    private int index;
    private List<Animal> animalsOwned = new ArrayList<Animal>();

    public Owner(String name, String surname, Sex sex, int age) {
        super(name, sex, age);
        this.surname = surname;
        this.index = ++indexCount;
    }

    public void info() {
        System.out.println("Właściciel nr " + index);
        System.out.println("   imie:          " + name);
        System.out.println("   nazwisko:      " + surname);
        System.out.println("   płeć:          " + sex.getValueHuman());
        System.out.println("   wiek:          " + getAgeString());
    }
    public String getAsString(){
        return ("owner "+index+" "+name+" "+surname+" "+sex +" "+age);
    }
    public void addAnimalToOwner(Animal animal) {
        animalsOwned.add(animal);
    }

    public List<Animal> getAnimalsOwned() {
        return animalsOwned;
    }

    public int getAnimalsOwnedSize() {
        return animalsOwned.size();
    }

    public String getNameAndSurname() {
        return (name + " " + surname);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}