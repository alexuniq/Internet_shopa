package eu.retarded.internetstore.core.services.basket;


import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.basket.AddProductToBasketRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.basket.AddProductToBasketResponse;
import eu.retarded.internetstore.core.services.validators.basket.AddProductToBasketValidator;
import eu.retarded.internetstore.database.ProductDatabase;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddProductToBasketService {

    @Autowired
    private UsersDatabase usersDatabase;
    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private AddProductToBasketValidator validator;

    public AddProductToBasketResponse execute(AddProductToBasketRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductToBasketResponse(errors);
        }
        User user = usersDatabase.getUserById(request.getUserId()).get();
        Product product = productDatabase.getById(request.getProductId()).get();
        return new AddProductToBasketResponse(user.getUsersBasket().add(product, request.getQuantity()));
    }
}