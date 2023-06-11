package test.my_app.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.my_app.domain.*;
import test.my_app.repos.BrandRepository;
import test.my_app.repos.ProductRepository;
import test.my_app.repos.RegisterRepository;
import test.my_app.services.BrandService;
import test.my_app.services.ProductService;
import test.my_app.services.StatusService;
import test.my_app.services.SubCategoryService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/products")
public class ProdductAPIController {

    @Autowired
    ProductService productService;

    @Autowired
    BrandService brandService;
    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    SubCategoryService subCategoryService;

    @Autowired
     StatusService statusService;

    // ... Existing code ...
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @Transactional
    @GetMapping("/subcategories/{id}")
    public ResponseEntity<List<Product>> getAllProductsByCate(@PathVariable Long id) {
        List<Product> products = productService.findByProductCate(id);
        return ResponseEntity.ok(products);
    }
    @Transactional
    @GetMapping("/brands/{id}")
    public ResponseEntity<List<Product>> getAllProductsByBrandId(@PathVariable Long id) {
        Brand brand = brandRepository.findById(id).get();
        List<Product> products = productRepository.findAllByBrands(brand);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<Status>> getAllStatuses() {
        return ResponseEntity.ok(statusService.getAllStatuses());
    }

    @GetMapping("/subcategories")
    public ResponseEntity<List<SubCategory>> getAllSubs() {
        List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
        return ResponseEntity.ok(subCategories);
    }



    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        System.out.println(product);
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            product.setId(id);
            Product updatedProduct = productService.updateProduct(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/login/{email}/{pw}")
    public ResponseEntity<Register> getLogin(@PathVariable("email") String email , @PathVariable("pw") String pw) {
        Register register = registerRepository.findByPasswordAndEmail(pw,email);
        System.out.println(register);
        if (register != null) {
            return ResponseEntity.ok(register);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
