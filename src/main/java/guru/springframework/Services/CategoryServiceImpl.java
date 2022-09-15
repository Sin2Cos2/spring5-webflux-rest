package guru.springframework.Services;

import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Mono<Long> count() {
        return categoryRepository.count();
    }

    @Override
    public Mono<Category> save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Flux<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Mono<Void> saveAll(Publisher<Category> categoryStream) {
        return categoryRepository.saveAll(categoryStream).then();
    }

    @Override
    public Mono<Category> updateCategory(String categoryId, Category category) {
        category.setId(categoryId);
        return categoryRepository.save(category);
    }

    @Override
    public Mono<Category> patchCategory(String categoryId, Category category) {
        Mono<Category> categoryMono = categoryRepository.findById(categoryId);

        return categoryMono
                .flatMap(ctg -> {
                    if (category.getDescription() == null)
                        category.setDescription(ctg.getDescription());

                    category.setId(categoryId);
                    return categoryRepository.save(category);
                })
                .switchIfEmpty(Mono.empty());
    }
}
