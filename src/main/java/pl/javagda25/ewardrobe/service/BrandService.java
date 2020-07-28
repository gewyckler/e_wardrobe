package pl.javagda25.ewardrobe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javagda25.ewardrobe.model.Brand;
import pl.javagda25.ewardrobe.repository.BrandRepository;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    public Brand addBrand(Brand brand) {

        return brandRepository.save(brand);
    }
}
