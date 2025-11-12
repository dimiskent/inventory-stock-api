package be.skenteridis.inventorystock.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequestDTO {
    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @NotNull(message = "Quantity must have a value!")
    @Min(value = 0, message = "Quantity cannot be negative!")
    private Integer quantity;

    @NotNull(message = "Price must have a value!")
    @DecimalMin(value = "0.01", message = "Price cannot be free nor invalid!")
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
