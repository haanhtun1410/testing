package test.my_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import test.my_app.services.MyServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "test.my_app.services")
public class ServiceConfiguration {

    @Autowired
    private List<MyServiceInterface> services;

    @Bean
    public Map<String, MyServiceInterface> serviceMap() {
        Map<String, MyServiceInterface> serviceMap = new HashMap<>();
        for (MyServiceInterface service : services) {
            Class<?>[] interfaces = service.getClass().getInterfaces();
            if (interfaces.length > 0) {
                String entityName = interfaces[0].getSimpleName().replace("Service", "").toLowerCase();
                System.out.println("key"+entityName);
                serviceMap.put(entityName, service);
            }
        }
        return serviceMap;
    }
}