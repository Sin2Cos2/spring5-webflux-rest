package guru.springframework.Services;

import guru.springframework.domain.Category;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {
    Mono<Long> count();

    Mono<Category> save(Category category);

    Flux<Category> getAllCategories();

    Mono<Category> getCategoryById(String id);

    Mono<Void> saveAll(Publisher<Category> categoryStream);

    Mono<Category> updateCategory(String categoryId, Category category);

    Mono<Category> patchCategory(String categoryId, Category category);
}
