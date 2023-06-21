package pibd.jagoda.animals.model.manager;

import java.io.File;
import java.util.Scanner;

public class ScannerManager {
    public static void read(OwnerManager ownerManager){
        try{
            File file = new File("output.txt");
            Scanner fileScan = new Scanner(file);
            while (fileScan.hasNextLine()){
                String subject = fileScan.next();
                if(subject.equals("owner")){
                    int index = fileScan.nextInt();
                    String name = fileScan.next();
                    String surname = fileScan.next();
                    String sex = fileScan.next();
                    int age = fileScan.nextInt();
                    ownerManager.readOwner(name, surname, sex, age);
                }
            }
            } catch(Exception e){
            e.printStackTrace();
        }
    }
}
