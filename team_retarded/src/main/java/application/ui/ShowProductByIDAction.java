package application.ui;
import application.items.Product;
import application.requests.ShowProductByIDRequests;
import application.responses.ShowProductByIDResponse;
import application.services.ShowProductByIDService;

import java.util.Optional;
import java.util.Scanner;


public class ShowProductByIDAction implements UIAction {

    private final ShowProductByIDService service;

    public ShowProductByIDAction(ShowProductByIDService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter product ID: ");
        long productId = Long.parseLong(scanner.nextLine());
        ShowProductByIDRequests requests = new ShowProductByIDRequests(productId);
        ShowProductByIDResponse response = service.execute(requests);
        Optional<Product> product = response.getProduct();
        System.out.println("Your product was successfully found . ");
        product.ifPresent(System.out::println);

    }
}
