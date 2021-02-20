package eu.retarded.internetstore.web_ui.controllers.admin.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.GetCategoryByIdRequest;
import eu.retarded.internetstore.core.requests.category.UpdateCategoryRequest;
import eu.retarded.internetstore.core.services.category.GetCategoryByIdService;
import eu.retarded.internetstore.core.services.category.UpdateCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminEditCategoryController {
    @Autowired
    private GetCategoryByIdService getCategoryByIdService;

    @Autowired
    private UpdateCategoryService updateCategoryService;


    @GetMapping("/admin/category/edit/{id}")
    public String main(@PathVariable String id, ModelMap modelMap) {
        long idLong = Integer.parseInt(id);
        Category category = getCategoryByIdService.execute(new GetCategoryByIdRequest(idLong)).getCategory();
        modelMap.addAttribute("category", category);
        return "/admin/category/edit";
    }

    @PostMapping("/admin/category/save")
    public String editDelivery(@RequestParam(value = "id") long id,
                               @RequestParam(value = "name") String name) {
        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest(id, name);
        updateCategoryService.execute(updateCategoryRequest);
        return "redirect:/admin/category/edit/" + id;
    }
}