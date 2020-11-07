package application;

import application.bd.Database;
import application.bd.ProductListDatabase;
import application.services.AddProductService;
import application.ui.AddProductUIAction;
import application.ui.UIAction;

import java.util.Scanner;

public class Application {

        private static UIAction addProductUIAction;
        private static Database db;

        public static void main(String[] args) {
            initialization();
            while (true) {
                showMenu();
                switch (getChoice()) {
                    case 0:
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        addProductUIAction.execute();
                        break;
                    case 5:

                        break;
                }

            }

        }

        private static void initialization() {
            db = new ProductListDatabase();
            AddProductService addProductService = new AddProductService(db);
            addProductUIAction = new AddProductUIAction(addProductService);
        }

        private static void showMenu() {
            System.out.println("==========================");
            System.out.println("Internet Store MENU:");
            System.out.println("[1] - Show all products");
            System.out.println("[2] - Search by ID");
            System.out.println("[3] - Filter");
            System.out.println("[4] - Add product");
            System.out.println("[5] - Delete product");
            System.out.println("[0] - Exit");
            System.out.println("==========================");
        }

        private static int getChoice() {
            System.out.print("Please, enter menu item number: ");
            Scanner scanner = new Scanner(System.in);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Incorrect value, try again");
            }
            return -1;
        }
}
