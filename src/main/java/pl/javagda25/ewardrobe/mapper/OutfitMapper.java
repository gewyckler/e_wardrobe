package pl.javagda25.ewardrobe.mapper;

import org.mapstruct.Mapper;
import pl.javagda25.ewardrobe.model.Outfit;
import pl.javagda25.ewardrobe.model.dto.CreateOutfitRequest;

@Mapper(componentModel = "spring")
public interface OutfitMapper {
    Outfit createOutfitFromDto(CreateOutfitRequest dto);
}
