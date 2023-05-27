package test.my_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import test.my_app.domain.Brand;
import test.my_app.domain.Status;
import test.my_app.domain.SubCategory;
import test.my_app.repos.CategoryRepository;
import test.my_app.repos.StatusRepository;
import test.my_app.repos.SubCategoryRepository;
import test.my_app.services.CategoryService;
import test.my_app.services.MyServiceInterface;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class tesst {

    @Autowired
    private Map<String, MyServiceInterface> serviceMap;

    @GetMapping("/{entityName}")
    @ResponseBody
    public String handleRequest(@PathVariable String entityName) {
        MyServiceInterface service = serviceMap.get(entityName);
        for (String key : serviceMap.keySet()) {
            System.out.println(key);
        }
        System.out.println(serviceMap.size());
        if (service == null) {
            return "Invalid entity name: " + entityName;
        }
        // Call the method on the service
        System.out.println(service.findAll());;

        // Handle the request and return the response
        return "Handling request for entity: " + entityName;
    }
}
