package guru.springframework.Services;

import guru.springframework.domain.Vendor;
import guru.springframework.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public Mono<Long> count() {
        return vendorRepository.count();
    }

    @Override
    public Mono<Vendor> save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Flux<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Mono<Vendor> getVendorById(String vendorId) {
        return vendorRepository.findById(vendorId);
    }

    @Override
    public Mono<Void> saveAll(Publisher<Vendor> vendorStream) {
        return vendorRepository.saveAll(vendorStream).then();
    }

    @Override
    public Mono<Vendor> updateVendor(String vendorId, Vendor vendor) {
        vendor.setId(vendorId);
        return vendorRepository.save(vendor);
    }

    @Override
    public Mono<Vendor> patchVendor(String vendorId, Vendor vendor) {
        Mono<Vendor> vendorMono = vendorRepository.findById(vendorId);

        return vendorMono
                .flatMap(vend -> {
                    if (vendor.getFirstName() == null)
                        vendor.setFirstName(vend.getFirstName());

                    if (vendor.getLastName() == null)
                        vendor.setLastName(vend.getLastName());

                    vendor.setId(vendorId);

                    return vendorRepository.save(vendor);
                });
    }
}
