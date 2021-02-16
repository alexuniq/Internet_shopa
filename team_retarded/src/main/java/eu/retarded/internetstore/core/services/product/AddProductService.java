package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.AddProductRequest;
import eu.retarded.internetstore.core.responses.product.AddProductResponse;
import eu.retarded.internetstore.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class AddProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Validator validator;

    @Transactional
    public AddProductResponse execute(AddProductRequest request) {
        Set<ConstraintViolation<AddProductRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }
        Product product =new Product(request.getName(), request.getDescription(), request.getPrice(), request.getCount());
        return new AddProductResponse(productRepository.save(product));
    }
}
