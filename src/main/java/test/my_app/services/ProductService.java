package test.my_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.my_app.domain.Product;
import test.my_app.repos.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    // Create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Read a product by id
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Update a product
    public Product  updateProduct(Product product) {
        return productRepository.save(product);
    }

    // Delete a product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }
    public List<Product> findByProductCate(Long id){
        return productRepository.findAllBySubcateId(id);
    }
}