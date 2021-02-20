package eu.retarded.internetstore.web_ui.controllers.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.GetProductInCartRequest;
import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.requests.user.AddProductToUserCartRequest;
import eu.retarded.internetstore.core.services.cart.GetProductInCartService;
import eu.retarded.internetstore.core.services.product.GetProductByIdService;
import eu.retarded.internetstore.core.services.user.AddProductToUserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private GetProductByIdService getProductByIdService;

    @Autowired
    private GetProductInCartService getProductInCartService;

    @Autowired
    private AddProductToUserCartService addProductToUserCartService;

    @GetMapping("/product/{id}")
    public String main(@PathVariable(name = "id") String id,
                       @AuthenticationPrincipal User activeUser,
                       ModelMap modelMap) {
        long idLong = Long.parseLong(id);
        boolean isLogged = activeUser != null;
        boolean isActiveUserAdmin = false;
        int productInCart = 0;
        if (isLogged) {
            isActiveUserAdmin = activeUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
            productInCart = getProductInCartService.execute(new GetProductInCartRequest(activeUser.getCart().getId())).getProducts().size();
        }
        Product product = getProductByIdService.execute(new GetProductByIdRequest(idLong)).getProduct();
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("active_user", activeUser);
        modelMap.addAttribute("is_logged", isLogged);
        modelMap.addAttribute("is_admin", isActiveUserAdmin);
        modelMap.addAttribute("product_in_cart_count", productInCart);
        return "product/index";
    }

    @PostMapping("/product/add")
    public String addToCart(@RequestParam(name = "product_id") long productId,
                            @RequestParam(name = "user_id") long userId,
                            @RequestParam(name = "count") int count) {
        addProductToUserCartService.execute(new AddProductToUserCartRequest(userId, productId, count));
        return "redirect:/product/" + productId;
    }
}