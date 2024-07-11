package com.chtrembl.petstore.product.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ProductStatusConverter implements AttributeConverter<Product.StatusEnum, String> {
    @Override
    public String convertToDatabaseColumn(Product.StatusEnum status) {
        return status.getValue();
    }

    @Override
    public Product.StatusEnum convertToEntityAttribute(String value) {
        return Product.StatusEnum.fromValue(value);
    }
}
