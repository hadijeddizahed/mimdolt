package ir.mimdolt.store.business;


import ir.mimdolt.store.business.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Hadi Jeddi Zahed on 11/3/2016.
 */


public class ServiceFaced {

    public static ServiceFaced serviceFaced = new ServiceFaced();


    private CategoryService categoryService;

//    @Autowired
//    private UserService userService;

    private ServiceFaced() {
    }

    public static ServiceFaced getServiceFaced() {
        return serviceFaced;
    }

    @Autowired
    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

}
