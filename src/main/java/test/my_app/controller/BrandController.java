package test.my_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import test.my_app.domain.Brand;
import test.my_app.services.BrandService;

import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/show")
    public String showList(Model model) {
        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        return "brand-view"; //
    }

    @GetMapping("")
    @ResponseBody
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands(); // Return the view name (JSP or HTML) for displaying the list of brands
    }

    // Endpoint for creating a new brand
//    @PostMapping("/add")
//    public Brand createBrand() {
//         Brand brand = new Brand();
//         brand.setBrandName("oke");
//        return brandService.createBrand(brand);
//    }

    @GetMapping("/add")
    public String showAddBrandForm(Model model) {
        model.addAttribute("brand", new Brand());
        return "brand-add";
    }

    // POST request to submit the add brand form
//    @PostMapping("/add")
//    public String addBrandSubmit(@ModelAttribute("brand") Brand brand, Model model) {
//        // Save the new brand object to the database
//        brandService.createBrand(brand);
//        return "redirect:/brands"; // Redirect to the brand list page
//    }

    // Endpoint for retrieving a brand by id
    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    // Endpoint for updating a brand
    @PutMapping("/{id}/update")
    public Brand updateBrand(@PathVariable Long id, @RequestBody Brand brand) {
        brand.setId(id); // Set the id of the brand
        return brandService.updateBrand(brand);
    }

    // Endpoint for deleting a brand by id
    @DeleteMapping("/{id}/delete")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }

    // Endpoint for retrieving all brands

}