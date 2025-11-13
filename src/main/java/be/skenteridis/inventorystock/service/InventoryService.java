package be.skenteridis.inventorystock.service;

import be.skenteridis.inventorystock.dto.ProductRequestDTO;
import be.skenteridis.inventorystock.dto.ProductResponseDTO;
import be.skenteridis.inventorystock.model.Product;
import be.skenteridis.inventorystock.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final ProductRepository repository;
    public InventoryService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponseDTO addProduct(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        Product result = repository.save(product);
        ProductResponseDTO res = new ProductResponseDTO();

        res.setId(result.getId());
        res.setName(result.getName());
        res.setPrice(result.getPrice());
        res.setQuantity(result.getQuantity());
        return res;
    }

    public List<ProductResponseDTO> getProducts() {
        return repository.findAll().stream().map(p -> {
            ProductResponseDTO dto = new ProductResponseDTO();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setPrice(p.getPrice());
            dto.setQuantity(p.getQuantity());
            return dto;
        }).toList();
    }

    public boolean deleteProduct(Long id) {
        Product product = repository.findById(id).orElse(null);
        if(product == null) return false;
        repository.delete(product);
        return true;
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product product = repository.findById(id).orElse(null);
        if (product == null) return null;
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        repository.save(product);
        ProductResponseDTO res = new ProductResponseDTO();
        res.setId(id);
        res.setName(product.getName());
        res.setPrice(product.getPrice());
        res.setQuantity(product.getQuantity());
        return res;
    }

    public Double getInventoryTotal() {
        return repository.findInventoryValue();
    }
}
