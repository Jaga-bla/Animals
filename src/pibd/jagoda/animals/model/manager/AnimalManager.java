package pibd.jagoda.animals.model.manager;

import pibd.jagoda.animals.model.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AnimalManager {
    private static final int MAX_SIZE_ANIMALS = 3;
    private static final int MIN_OWNER_AGE_CAT = 10;
    private static final int MIN_OWNER_AGE_DOG = 15;
    private static final int MIN_OWNER_AGE_TURLE_LAND= 20;
    private static final int MIN_OWNER_AGE_TURLE_MARSH = 25;
    private static final int MIN_OWNER_AGE_TURLE_SEA = 35;
    private final Scanner scanner = new Scanner(System.in);
    private List<Animal> animals = new ArrayList<>();

    public void processAnimalCreation(Owner owner) {
        try{
            if (!isFull(owner)) {
                System.out.println(owner.getNameAndSurname() + " ma " + owner.getAgeString());
                System.out.println("Jaki rodzaj zwierzęcia chcesz dodac?");
                System.out.println("     1. Kot");
                System.out.println("     2. Pies");
                System.out.println("     3. Zółw");
                int type = scanner.nextInt();
                parseAnimalTypeInput(type, owner);
            } else {
                System.out.println("Nie możesz już dodać więcej zwierząt do właściciela");
            }
        }catch (InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
            scanner.nextLine();
        }
    }

    private void parseAnimalTypeInput(int type, Owner owner) {
        switch (type) {
            case 1:
                parseMammalTypeInput(owner, MammalType.CAT, MIN_OWNER_AGE_CAT);
                break;
            case 2:
                parseMammalTypeInput(owner, MammalType.DOG, MIN_OWNER_AGE_DOG);
                break;
            case 3:
                parseTurtleTypeInput(owner);
                break;
        }
    }

    private void parseTurtleTypeInput(Owner owner) {
        try{
            if (owner.getAgeint() >= 20) {
                System.out.println("Jaki typ żółwia chcesz dodac?");
                System.out.println("     1. Lądowy");
                System.out.println("     2. Błotny");
                System.out.println("     3. Morski");
                int typeTurtle = scanner.nextInt();
                validateOwnerToCreateTurtle(owner, typeTurtle);
            } else System.out.println("Wybrany właściciel jest za młody na posiadanie żółwia");
        }catch (InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
            scanner.nextLine();
        }
    }

    private void parseMammalTypeInput(Owner owner, MammalType mammalType, int ageRange) {
        if (owner.getAgeint() >= ageRange) {
            System.out.println("Tworzysz " + mammalType.getValueVariable() + " dla właścicela: " + owner.getNameAndSurname());
            addMammal(owner, mammalType);
        } else {
            System.out.println("Wybrany właściciel jest za młody na posiadanie " + mammalType.getValueVariable());
        }
    }

    private void validateOwnerToCreateTurtle(Owner owner, int typeTurtle) {
        if (typeTurtle == 1 && owner.getAgeint() >= MIN_OWNER_AGE_TURLE_LAND) {
            addTurtle(owner, typeTurtle);
        }
        if (typeTurtle == 2 && owner.getAgeint() >= MIN_OWNER_AGE_TURLE_MARSH) {
            addTurtle(owner, typeTurtle);
        }
        if (typeTurtle == 3 && owner.getAgeint() >= MIN_OWNER_AGE_TURLE_SEA) {
            if (Character.compare(owner.getSurname().charAt(0), 'M') == 0 || Character.compare(owner.getSurname().charAt(0), 'm') == 0) {
                addTurtle(owner, typeTurtle);
            } else
                System.out.println("Wybrano nieprawidłowego właściciela. Nazwisko powinno zaczynać się na literę 'M'");
        } else {
            System.out.println("Wybrany właściciel jest za młody na posiadanie tego typu zółwia.");
        }
    }

    private void addMammal(Owner owner, MammalType mammalType) {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj imie: ");
            String name = scanner.next();
            System.out.println("Podaj wiek: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Podaj rasę: ");
            String breed = scanner.nextLine();
            System.out.println("Podaj płeć. Samica(K) lub Samiec(M):");
            String sexUserIput = scanner.next();
            Sex sex = Sex.MALE;
            if (sexUserIput.equals("K")) {
                sex = Sex.FEMALE;
            }
            Animal newAnimal = new Mammal(owner, name, sex, age, breed, mammalType);
            animals.add(newAnimal);
            owner.addAnimalToOwner(newAnimal);
        }catch (InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
            scanner.nextLine();
        }
    }

    private void addTurtle(Owner owner, int type) {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj imie: ");
            String name = scanner.next();
            System.out.println("Podaj wiek: ");
            int age = scanner.nextInt();
            System.out.println("Podaj płeć. Samica(K) lub Samiec(M):");
            String sexUserIput = scanner.next();
            Sex sex = Sex.MALE;
            if (sexUserIput.equals("K")) {
                sex = Sex.FEMALE;
            }
            TurtleType typeTurle = TurtleType.LAND;
            if (type == 2) {
                typeTurle = TurtleType.MARSH;
            }
            if (type == 3) {
                typeTurle = TurtleType.SEA;
            }
            Animal newAnimal = new Turtle(owner, name, sex, age, typeTurle);
            animals.add(newAnimal);
            owner.addAnimalToOwner(newAnimal);
        }catch (InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
            scanner.nextLine();
        }
    }

    public void printAnimalList() {
        System.out.println("------------- lista zwierząt -------------");
        for (Animal animal : animals) {
            animal.info();
        }
    }

    public void findAnimal(OwnerManager ownerManager) {
        try {
            System.out.println("Wybierz sposób wyszukania zwierząt");
            System.out.println("     1. Imię");
            System.out.println("     2. Wiek większy od");
            System.out.println("     3. Nazwisko właściela");
            System.out.println("     4. Typ zwierzęcia");
            int answer = scanner.nextInt();
            if (answer == 1) {
                printAnimalGetByName();
            }
            if (answer == 2) {
                printAnimalGetByAge();
            }
            if (answer == 3) {
                System.out.println("Podaj nazwisko właściciela");
                String surname = scanner.next();
                Owner owner = ownerManager.getOwnerBySurname(surname);
                List<Animal> result = owner.getAnimalsOwned();
                for (Animal animal : result) {
                    animal.info();
                }
            }
            if (answer == 4){
                parseTypeToFilter();
            }
        } catch (InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
            scanner.nextLine();
        }
    }

    public void printAnimalGetByName() {
        System.out.println("Podaj imię zwierzęcia");
        String answer = scanner.next();
        for (Animal animal : animals) {
            if (animal.getName().equals(answer)) {
                animal.info();
            }
        }
    }

    public void printAnimalGetByAge() {
        System.out.println("Podaj wiek, żeby otrzymać starsze zwierzęta");
        try {
            int answer = scanner.nextInt();
            for (Animal animal : animals) {
                if (animal.getAgeint() > answer) {
                    animal.info();
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
            scanner.nextLine();
        }
    }

    private boolean isFull(Owner owner) {
        List<Animal> list = owner.getAnimalsOwned();
        if (list.size() >= MAX_SIZE_ANIMALS) {
            return true;
        }
        return false;
    }
    public Animal getAnimalByName(String name) {
        Animal result = null;
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                result = animal;
            }
        }
        return result;
    }
    private void parseTypeToFilter(){
        try{
            System.out.println("Podaj typ");
            System.out.println("     1. Pies");
            System.out.println("     2. Kot");
            System.out.println("     3. Żółw");
            int type = scanner.nextInt();
            switch (type){
                case 1:
                    printMammalGetByType(MammalType.DOG);
                    break;
                case 2:
                    printMammalGetByType(MammalType.CAT);
                case 3:
                    processTurtleTypeToFilter();
            }

        } catch (InputMismatchException ex) {
                System.out.println("Nie wprowadzono inta");
                scanner.nextLine();
        }
    }
    private void printMammalGetByType(MammalType type){
        List<Mammal> mammals = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof Mammal){
                mammals.add((Mammal) animal);
            }
        }
        for (Mammal mammal : mammals) {
            if(mammal.getMammalType().equals(type)){
                mammal.info();
            }
        }
    }
    private void processTurtleTypeToFilter(){
        try{
            System.out.println("Podaj typ");
            System.out.println("     1. Lądowy");
            System.out.println("     2. Błotny");
            System.out.println("     3. Morski");
            System.out.println("     4. Wszystkie");
            int type = scanner.nextInt();
            switch (type){
                case 1:
                    printTurtleGetByType(TurtleType.LAND);
                    break;
                case 2:
                    printTurtleGetByType(TurtleType.MARSH);
                    break;
                case 3:
                    printTurtleGetByType(TurtleType.SEA);
                    break;
                case 4:
                    printAllTurtles();
                    break;
            }
        } catch (InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
            scanner.nextLine();
        }
    }
    private void printTurtleGetByType(TurtleType type){
        List<Turtle> turtles = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof Turtle){
                turtles.add((Turtle) animal);
            }
        }
        for (Turtle turtle : turtles) {
            if(turtle.getType().equals(type)){
                turtle.info();
            }
        }
    }
    private void printAllTurtles(){
        List<Turtle> turtles = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof Turtle){
                ((Turtle) animal).info();
            }
        }
    }
    public void parseEditMethod(Animal animal){
        try{
            int option = scanner.nextInt();
            switch(option){
                case 1:
                    editName(animal);
                    break;
                case 2:
                    editAge(animal);
                    break;
                case 3:
                    editSex(animal);
                    break;
                case 4:
                    editType(animal);
                    break;
            }
        }catch (InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
            scanner.nextLine();
        }
    }
    private void editName(Animal animal){
        System.out.println("Podaj nowe imię");
        String firstName = scanner.next();
        animal.setName(firstName);
    }
    private void editType(Animal animal){
        if (animal instanceof Mammal){
            System.out.println("Podaj nową rasę");
            String type = scanner.next();
            ((Mammal) animal).setBreed(type);
        }else {
            System.out.println("Nie można zmienić tyu żółwia");
        }
    }
    private void editSex(Animal animal){
        System.out.println("Podaj płeć");
        String sex = scanner.next();
        if(sex=="M"){
            animal.setSex(Sex.MALE);
        }if(sex=="K"){
            animal.setSex(Sex.FEMALE);
        }
    }
    private void editAge(Animal animal){
        try{
            System.out.println("Podaj wiek");
            int age = scanner.nextInt();
            animal.setAge(age);
        }catch(InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
            scanner.nextLine();
        }
    }

    public void clearData() {
        animals.clear();
    }
}