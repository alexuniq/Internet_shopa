package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.DeleteAllCategoryRequest;
import eu.retarded.internetstore.core.responses.category.DeleteAllCategoryResponse;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class DeleteAllCategoryService {

    @Autowired
    private CategoriesDatabase database;

    @Transactional
    public DeleteAllCategoryResponse execute(DeleteAllCategoryRequest request) {
        database.clear();
        return new DeleteAllCategoryResponse();
    }
}
