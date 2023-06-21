package pibd.jagoda.animals;

import pibd.jagoda.animals.model.*;
import pibd.jagoda.animals.model.manager.AnimalManager;
import pibd.jagoda.animals.model.manager.OwnerManager;
import pibd.jagoda.animals.model.manager.ScannerManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final OwnerManager ownerManager = new OwnerManager();
    private final AnimalManager animalManager = new AnimalManager();

    public void start() {
        ScannerManager.read(ownerManager);
        System.out.println("Witaj w ewidencji zwierząt i właścicieli!");
        System.out.println("Co chciałbyś zrobić?");
        parseInput();
    }

    private void parseInput() {
        int option;
        boolean runProgram = true;
        while (runProgram) {
            printOptions();
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Nie podano cyfry.");
                scanner.next();
                option = -1;
            }
            switch (option) {
                case 1:
                    optionCreateOwner();
                    parseEndline();
                    break;
                case 2:
                    optionPrintOwnerList();
                    parseEndline();
                    break;
                case 3:
                    optionCreateAnimal();
                    parseEndline();
                    break;
                case 4:
                    optionPrintAnimalList();
                    parseEndline();
                    break;
                case 5:
                    optionFilterOwners();
                    parseEndline();
                    break;
                case 6:
                    optionFindAnimal();
                    parseEndline();
                    break;
                case 7:
                    optionEditOwner();
                    parseEndline();
                    break;
                case 8:
                    optionEditAnimal();
                    parseEndline();
                    break;
                case 9:
                    optionResetData();
                    parseEndline();
                    break;
                case 0:
                    runProgram = false;
            }
        }
    }

    private void printOptions() {
        System.out.println("     1. Dodaj właściciela");
        System.out.println("     2. Lista właścicieli");
        System.out.println("     3. Dodaj zwierzę");
        System.out.println("     4. Lista zwierząt");
        System.out.println("     5. Filtruj listę właścicieli");
        System.out.println("     6. Wyszukaj zwierzę");
        System.out.println("     7. Edytuj właściciela");
        System.out.println("     8. Edytuj zwierzę");
        System.out.println("     9. Resetuj program");
        System.out.println("     0. Zakończ");
    }

    private void optionCreateOwner() {
        System.out.println("Wybrałeś opcję 1");
        try{
            ownerManager.addOwner();
        }catch (InputMismatchException ex) {
            System.out.println("Wiek musi być intem. Nie stworzono właściciela");
        }
    }

    private void optionPrintOwnerList() {
        System.out.println("Wybrałeś opcję 2");
        ownerManager.printOwnerList(animalManager);
    }

    private void optionCreateAnimal() {
        System.out.println("Wybrałeś opcję 3. Wybierz właściciela nowego zwierzęcia. Podaj nazwisko.");
        String surname = scanner.next();
        try {
            Owner resultOwner = ownerManager.getOwnerBySurname(surname);
            if (resultOwner.getAgeint() > 9) {
                animalManager.processAnimalCreation(resultOwner);
            } else {
                System.out.println("Ten właściciel jest za młody na posiadanie zwierząt.");
            }
        } catch (java.lang.NullPointerException e) {
            System.out.println("Nie ma właściciela o takim nazwisku.");
        }
    }

    private void optionPrintAnimalList() {
        System.out.println("Wybrałeś opcję 4.");
        animalManager.printAnimalList();
    }

    private void optionFilterOwners() {
        System.out.println("Wybrałeś opcję 5.");
        chooseFilterMethod();
    }

    public void chooseFilterMethod() {
        System.out.println("Jaką metodę chcesz zastosować?");
        System.out.println("     1. Sortuj po nazwisku rosnąco");
        System.out.println("     2. Sortuj po nazwisku malejąco");
        System.out.println("     3. Sortuj po wieku rosnąco");
        System.out.println("     4. Sortuj po wieku malejąco");
        System.out.println("     5. Sortuj po liczbie posiadanych zwierząt rosnąco");
        System.out.println("     6. Sortuj po liczbie posiadanych zwierząt malejąco");
        try {
            int type = scanner.nextInt();
            parseFilterTypeInput(type);
        } catch (InputMismatchException ex) {
            System.out.println("Nie wprowadzono inta");
        }
    }

    private void parseFilterTypeInput(int type) {
        switch (type) {
            case 1:
                ownerManager.sortByLastName();
                break;
            case 2:
                ownerManager.sortByLastNameRev();
                break;
            case 3:
                ownerManager.sortByAge();
                break;
            case 4:
                ownerManager.sortByAgeRev();
                break;
            case 5:
                ownerManager.sortByAnimalsOwned();
                break;
            case 6:
                ownerManager.sortByAnimalsOwnedRev();
                break;
        }
    }

    private void optionFindAnimal() {
        System.out.println("Wybrałeś opcję 6.");
        animalManager.findAnimal(ownerManager);
    }

    private void optionEditOwner() {
        System.out.println("Wybrałeś opcję 7. Podaj nazwisko właściciela do edycji: ");
        String surname = scanner.next();
        try{
            Owner resultOwner = ownerManager.getOwnerBySurname(surname);
            printOptionsToEditSubject(resultOwner);
            ownerManager.parseEditMethod(resultOwner);
        }catch (java.lang.NullPointerException e) {
            System.out.println("Nie ma właściciela o takim nazwisku.");
        }
    }
    private void printOptionsToEditSubject(Subject subject){
        System.out.println("Wybrany rekord do edycji: ");
        subject.info();
        System.out.println("Co chcesz zmienić?");
        System.out.println("     1. Imię");
        System.out.println("     2. Wiek");
        System.out.println("     3. Płeć");
        if (subject instanceof Owner){
            System.out.println("     4. Nazwisko");
        }if (subject instanceof Mammal){
            System.out.println("     4. Rasę");
        }if (subject instanceof Turtle){
            System.out.println("     4. Typ");
        }
    }
    private void optionEditAnimal() {
        System.out.println("Wybrałeś opcję 8. Podaj imię zwierzęcia do edycji: ");
        String name = scanner.next();
        try{
            Animal animal = animalManager.getAnimalByName(name);
            printOptionsToEditSubject(animal);
            animalManager.parseEditMethod(animal);
        }catch (java.lang.NullPointerException e) {
            System.out.println("Nie ma zwierzęcia o takim imieniu.");
        }
    }

    private void optionResetData() {
        ownerManager.clearData();
        animalManager.clearData();
    }

    private void parseEndline() {
        System.out.println("Wciśnij ENTER aby kontynuowac");
        scanner.nextLine();
        scanner.nextLine();
    }
}