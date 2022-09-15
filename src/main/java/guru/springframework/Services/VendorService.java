package guru.springframework.Services;

import guru.springframework.domain.Vendor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VendorService {
    Mono<Long> count();

    Mono<Vendor> save(Vendor vendor);

    Flux<Vendor> getAllVendors();

    Mono<Vendor> getVendorById(String vendorId);

    Mono<Void> saveAll(Publisher<Vendor> vendorStream);

    Mono<Vendor> updateVendor(String vendorId, Vendor vendor);

    Mono<Vendor> patchVendor(String vendorId, Vendor vendor);
}
