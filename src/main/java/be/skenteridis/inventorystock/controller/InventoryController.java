package be.skenteridis.inventorystock.controller;

import be.skenteridis.inventorystock.dto.ProductRequestDTO;
import be.skenteridis.inventorystock.dto.ProductResponseDTO;
import be.skenteridis.inventorystock.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class InventoryController {
    private final InventoryService service;
    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(service.getProducts());
    }

    @GetMapping("/value")
    public ResponseEntity<?> getValue() {
        return ResponseEntity.ok(service.getInventoryTotal());
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequestDTO product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO product) {
        ProductResponseDTO dto = service.updateProduct(id, product);
        return dto == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return service.deleteProduct(id) ? ResponseEntity.ok("Product deleted successfully!")
                : ResponseEntity.notFound().build();
    }
}
