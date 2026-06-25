package gianniScanner;


import java.util.Scanner;

public class AcquisizioneAbbonamentiBiglietti {

    Scanner scanner = new Scanner(System.in);

    public void scannerBigliettiEAbbonamenti() {
        while (true) {
            System.out.println("benvenuto");
            while (true) {
                System.out.println("Da dove stai acquistando?\n1)Distributore automatico\n2) Rivenditore autorizzato");
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    if (choice == 1 || choice == 2) {
                        switch (choice) {
                            case 1 -> System.out.println("distributore automatico");
                            case 2 -> System.out.println("rivenditore autorizzato");
                        }
                        scanner.nextLine();
                        break;
                    } else {
                        scanner.nextLine();
                        System.out.println("Numero non valido!");
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("Valore non valido!");
                }
            }
            while (true) {
            }
        }

    }
}
