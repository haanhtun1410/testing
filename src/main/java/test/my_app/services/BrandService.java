package test.my_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.my_app.domain.Brand;
import test.my_app.repos.BrandRepository;

import java.util.List;

@Service
public class BrandService{

    @Autowired
    private BrandRepository brandRepository;

    // Create a new brand
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    // Read a brand by id
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    // Update a brand
    public Brand updateBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    // Delete a brand
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    // Get all brands
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

}