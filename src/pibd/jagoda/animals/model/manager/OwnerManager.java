package pibd.jagoda.animals.model.manager;

import pibd.jagoda.animals.model.Animal;
import pibd.jagoda.animals.model.Owner;
import pibd.jagoda.animals.model.Sex;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OwnerManager {
    private final int MAX_SIZE_OWNERS = 5;
    private List<Owner> owners = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void addOwner() {
        try{
            if (!isFull()) {
                System.out.println("Podaj imie: ");
                String firstName = scanner.next();
                System.out.println("Podaj nazwisko: ");
                String lastName = scanner.next();
                System.out.println("Podaj wiek: ");
                int age = scanner.nextInt();
                System.out.println("Podaj płeć. Kobieta(K) lub Mężczyzna(M):");
                String sexUserIput = scanner.next();
                Sex sex = Sex.MALE;
                if (sexUserIput.equals("K")) {
                    sex = Sex.FEMALE;
                }
                Owner owner = new Owner(firstName, lastName, sex, age);
                owners.add(owner);
                FileWriterManager.write(owner.getAsString());
            } else {
                System.out.println("Nie możesz już dodać właściciela");
            }
        }catch (InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
            scanner.nextLine();
        }
    }

    private boolean isFull() {
        if (owners.size() >= MAX_SIZE_OWNERS) {
            return true;
        }
        return false;
    }

    public void printOwnerList(AnimalManager animalManager) {
        System.out.println("------------- lista właścicieli -------------");
        for (Owner owner : owners) {
            owner.info();
            List<Animal> result = owner.getAnimalsOwned();
            for (Animal animal : result) {
                animal.info();
            }
        }
    }

    public Owner getOwnerBySurname(String surname) {
        Owner result = null;
        for (Owner owner : owners) {
            if (owner.getSurname().equals(surname)) {
                result = owner;
            }
        }
        return result;
    }

    public void sortByLastName() {
        owners.sort((o1, o2) -> {
            return o1.getSurname().compareTo(o2.getSurname());
        });
    }

    public void sortByLastNameRev() {
        owners.sort((o1, o2) -> {
            return -o1.getSurname().compareTo(o2.getSurname());
        });
    }

    public void sortByAge() {
        owners.sort((o1, o2) -> {
            return Integer.compare(o1.getAgeint(), o2.getAgeint());
        });
    }

    public void sortByAgeRev() {
        owners.sort((o1, o2) -> {
            return -Integer.compare(o1.getAgeint(), o2.getAgeint());
        });
    }

    public void sortByAnimalsOwned() {
        owners.sort((o1, o2) -> {
            return Integer.compare(o1.getAnimalsOwnedSize(), o2.getAnimalsOwnedSize());
        });
    }

    public void sortByAnimalsOwnedRev() {
        owners.sort((o1, o2) -> {
            return -Integer.compare(o1.getAnimalsOwnedSize(), o2.getAnimalsOwnedSize());
        });
    }
    public void parseEditMethod(Owner owner){
        try{
            int option = scanner.nextInt();
            switch(option){
                case 1:
                    editName(owner);
                    break;
                case 2:
                    editAge(owner);
                    break;
                case 3:
                    editSex(owner);
                    break;
                case 4:
                    editSurame(owner);
                    break;
            }
        }catch (InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
            scanner.nextLine();
        }
    }
    private void editName(Owner owner){
        System.out.println("Podaj nowe imię");
        String firstName = scanner.next();
        owner.setName(firstName);
    }
    private void editSurame(Owner owner){
        System.out.println("Podaj nowe nazwisko");
        if (owner.getAnimalsOwned().size()>0){
            System.out.println("Nie możesz zmienić już nazwiska");
        }else {
            String surname = scanner.next();
            owner.setSurname(surname);
        }
    }
    private void editSex(Owner owner){
        System.out.println("Podaj płeć");
        String sex = scanner.next();
        if(sex.equals("M")){
            owner.setSex(Sex.MALE);
        }if(sex.equals("K")){
            owner.setSex(Sex.FEMALE);
        }
    }
    private void editAge(Owner owner){
        try{
            if (owner.getAnimalsOwned().size()>0){
                System.out.println("Nie możesz zmienić już wieku");
            }else {
                System.out.println("Podaj wiek");
                int age = scanner.nextInt();
                owner.setAge(age);
            }
        }catch (InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
            scanner.nextLine();
        }
    }
    public void readOwner(String name, String surname, String sexstring, int age){
        Sex sex = Sex.MALE;
        if (sexstring.equals("FEMALE")) {
            sex = Sex.FEMALE;
        }
        Owner owner = new Owner(name, surname, sex, age);
        owners.add(owner);
        FileWriterManager.write(owner.getAsString());
    }
    public void clearData() {
        owners.clear();
    }
}