package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.ProductImage;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories.ImageRepository;
import org.springframework.stereotype.Service;

/**
 * Der ImageService implementiert die Business-Logic für die Bilder.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImage(ProductImage productImage){
        imageRepository.save(productImage);
    }

    public ProductImage getImagebyId(Long id){
        return imageRepository.getById(id);
    }

}
