package guru.springframework.bootstrap;

import guru.springframework.Services.CategoryService;
import guru.springframework.Services.VendorService;
import guru.springframework.domain.Category;
import guru.springframework.domain.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BootStrap implements CommandLineRunner {

    private final VendorService vendorService;
    private final CategoryService categoryService;

    @Override
    public void run(String... args) {
        if(vendorService.count().block() == 0L) {
            loadVendors();
        }

        if(categoryService.count().block() == 0L) {
            loadCategories();
        }
    }

    private void loadCategories() {

        Category c1 = new Category();
        c1.setDescription("1TestCategory");

        Category c2 = new Category();
        c2.setDescription("2TestCategory");

        categoryService.save(c1).block();
        categoryService.save(c2).block();
    }

    private void loadVendors() {

        Vendor v1 = new Vendor();
        v1.setFirstName("1TestFirstName");
        v1.setLastName("1TestLastName");

        Vendor v2 = new Vendor();
        v2.setFirstName("2TestFirstName");
        v2.setLastName("2TestLastName");

        vendorService.save(v1).block();
        vendorService.save(v2).block();
    }
}
