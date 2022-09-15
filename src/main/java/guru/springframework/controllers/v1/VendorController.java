package guru.springframework.controllers.v1;

import guru.springframework.Services.VendorService;
import guru.springframework.domain.Vendor;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.Flow;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;

    @GetMapping({"", "/"})
    public Flux<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{vendorId}")
    public Mono<Vendor> getVendorById(@PathVariable String vendorId) {
        return vendorService.getVendorById(vendorId);
    }

    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createNewVendor(@RequestBody Publisher<Vendor> vendorStream) {
        return vendorService.saveAll(vendorStream);
    }

    @PutMapping("/{vendorId}")
    public Mono<Vendor> updateVendor(@PathVariable String vendorId,
                                     @RequestBody Vendor vendor) {
        return vendorService.updateVendor(vendorId, vendor);
    }

    @PatchMapping("/{vendorId}")
    public Mono<Vendor> patchVendor(@PathVariable String vendorId,
                                    @RequestBody Vendor vendor) {
        return vendorService.patchVendor(vendorId, vendor);
    }
}
