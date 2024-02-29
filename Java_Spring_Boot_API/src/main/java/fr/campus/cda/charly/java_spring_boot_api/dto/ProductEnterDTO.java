package fr.campus.cda.charly.java_spring_boot_api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public final class ProductEnterDTO {
    @NotBlank(message = "You must give a name for your product ! ")
    private String name;
    @Min(value = 5, message = "Your price must be higher than 5")
    @Max(value = 100, message = "Your price be lower than 100 ")
    private int price;

    public ProductEnterDTO(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public int price() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ProductEnterDTO) obj;
        return Objects.equals(this.name, that.name) &&
                this.price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "ProductEnterDTO[" +
                "name=" + name + ", " +
                "price=" + price + ']';
    }

}
