package com.chtrembl.petstore.pet.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PetStatusConverter implements AttributeConverter<Pet.StatusEnum, String> {
    @Override
    public String convertToDatabaseColumn(Pet.StatusEnum statusEnum) {
        return statusEnum.getValue();
    }

    @Override
    public Pet.StatusEnum convertToEntityAttribute(String value) {
        return Pet.StatusEnum.fromValue(value);
    }
}
