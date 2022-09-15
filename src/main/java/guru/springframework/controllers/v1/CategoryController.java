package guru.springframework.controllers.v1;

import guru.springframework.Services.CategoryService;
import guru.springframework.domain.Category;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping({"", "/"})
    public Flux<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public Mono<Category> getCategoryById(@PathVariable String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createNewCategory(@RequestBody Publisher<Category> categoryStream) {
        return categoryService.saveAll(categoryStream);
    }

    @PutMapping("/{categoryId}")
    public Mono<Category> updateCategory(@PathVariable String categoryId,
                                         @RequestBody Category category) {
        return categoryService.updateCategory(categoryId, category);
    }

    @PatchMapping("/{categoryId}")
    public Mono<Category> patchCategory(@PathVariable String categoryId,
                                        @RequestBody Category category) {
        return categoryService.patchCategory(categoryId, category);
    }

}
