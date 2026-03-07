package ua.vlad.hg.converter;

import ua.vlad.hg.entity.Address;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class AddressTypeConverter implements AttributeConverter<Address.Type, String> {

    @Override
    public String convertToDatabaseColumn(Address.Type type) {
        return type == null ? null : type.getCode();
    }

    @Override
    public Address.Type convertToEntityAttribute(String code) {
        return code == null ? null : Stream.of(Address.Type.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
