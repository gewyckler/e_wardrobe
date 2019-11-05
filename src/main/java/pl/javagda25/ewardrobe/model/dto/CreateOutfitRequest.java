package pl.javagda25.ewardrobe.model.dto;

import pl.javagda25.ewardrobe.model.Cloth;

import java.util.HashSet;
import java.util.Set;

public class CreateOutfitRequest {
    private String name;

    private Set<Cloth> clothSet = new HashSet<>();
}
