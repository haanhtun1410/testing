package test.my_app.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import test.my_app.domain.Brand;
import test.my_app.domain.Product;
import test.my_app.domain.Status;
import test.my_app.domain.SubCategory;
import test.my_app.repos.BrandRepository;
import test.my_app.repos.ProductRepository;
import test.my_app.repos.StatusRepository;
import test.my_app.repos.SubCategoryRepository;
import test.my_app.services.BrandService;
import test.my_app.services.ProductService;
import test.my_app.services.StatusService;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    HttpSession ss;
    @Autowired
    private SubCategoryRepository subcategoryRepository; // Replace with your subcategory repository class
    @Autowired
    private BrandService brandRepository; //
    @Autowired
    private BrandRepository brandRes; //
    @Autowired
    private StatusService statusRepository; //
    @Transactional
    @GetMapping("")
    public String showProductList(Model model) {
        List<Product> products = productService.findAllProducts();
        System.out.println("ok");
        List<SubCategory> subcategories = subcategoryRepository.findAll();
        List<Brand> brands = brandRepository.getAllBrands();
        model.addAttribute("subcategories", subcategories);
        model.addAttribute("brands", brands);
        model.addAttribute("products", products);

        return "product-view";
    }
    @GetMapping("/get-list")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @GetMapping("/create-view")
    public String showProductCreate(Model model) {
        model.addAttribute("product",new Product());
        List<Brand> brands = brandRepository.getAllBrands();
        List<SubCategory> subcategories = subcategoryRepository.findAll();
        model.addAttribute("subcategories", subcategories);
        model.addAttribute("brands", brands);
        System.out.println(brands);
        return "product-create";
    }

    @Transactional
    @PostMapping("/create")
    public String addProductSubmit(@ModelAttribute("product") Product product,
                                   @RequestParam("selectedBrands") List<Long> selectedBrandIds) {
        System.out.println("run");
        Set<Brand> selectedBrands = selectedBrandIds.stream().map(id -> brandRes.findById(id).orElse(null)).collect(Collectors.toSet());
        product.setBrands(selectedBrands);
        product.setStatus(statusRepository.getStatusById(Long.valueOf(1)));
        productRepository.save(product);
        return "redirect:/products";
    }

    ////updaate o day
    @Transactional
    @PostMapping("/update/{productId}")
    public String updateProductSubmit(@PathVariable("productId") Long productId,@ModelAttribute("product") Product product,
                                   @RequestParam("selectedBrands") List<Long> selectedBrandIds) {
           Set<Brand> selectedBrands = selectedBrandIds.stream().map(id -> brandRes.findById(id).orElse(null)).collect(Collectors.toSet());
           product.setBrands(selectedBrands);
        product.setId(productId);
        productRepository.save(product);
        return "redirect:/products";
    }



    @Transactional
    @GetMapping("/details/{productId}")
    public String showProductDetails(@PathVariable("productId") Long productId, Model model) {
        // Retrieve product details using the productId
        List<Brand> brands = brandRepository.getAllBrands();
        System.out.println(brands);
        model.addAttribute("brands", brands);
        List<SubCategory> subcategories = subcategoryRepository.findAll();
        model.addAttribute("subcategories", subcategories);
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "product-detail";
    }

    @Transactional
    @GetMapping("/update-view/{productId}")
    public String showProductUpdate(@PathVariable("productId") Long productId, Model model) {
        // Retrieve product details using the productId
        Product product = productService.getProductById(productId);
        List<SubCategory> subcategories = subcategoryRepository.findAll();
        List<Brand> brands = brandRepository.getAllBrands();
        List<Status> statuses = statusRepository.getAllStatuses();
        model.addAttribute("subcategories", subcategories);
        model.addAttribute("brands", brands);
        model.addAttribute("statuses",statuses);
        model.addAttribute("product", product);
        return "product-update";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}/update")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}