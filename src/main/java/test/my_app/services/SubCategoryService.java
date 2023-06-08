package test.my_app.services;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.my_app.domain.SubCategory;
import test.my_app.repos.SubCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    @Autowired
   SubCategoryRepository subCategoryRepository;



    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    public Optional<SubCategory> getSubCategoryById(Long id) {
        return subCategoryRepository.findById(id);
    }

    public SubCategory saveSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    public void deleteSubCategoryById(Long id) {
        subCategoryRepository.deleteById(id);
    }
}
