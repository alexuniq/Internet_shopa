package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.Ordering;
import eu.retarded.internetstore.core.requests.product.Paging;
import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ShowAllProductsValidator {
    public List<CoreError> validate(ShowAllProductsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }



    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageNumber(Paging paging) {
        if ((paging.getPageNumber() == null && paging.getPageSize() == null)) {
            return Optional.empty();
        } else if (paging.getPageNumber() == null && paging.getPageSize() != null) {
            return Optional.of(new CoreError("pageNumber", "Must not be empty!"));
        } else if (paging.getPageNumber() <= 0) {
            return Optional.of(new CoreError("pageNumber", "Must be greater then 0!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(Paging paging) {
        if ((paging.getPageNumber() == null && paging.getPageSize() == null)) {
            return Optional.empty();
        } else if (paging.getPageSize() == null && paging.getPageNumber() != null) {
            return Optional.of(new CoreError("pageSize", "Must not be empty!"));
        } else if (paging.getPageSize() <= 0) {
            return Optional.of(new CoreError("pageSize", "Must be greater then 0!"));
        }
        return Optional.empty();
    }
}
