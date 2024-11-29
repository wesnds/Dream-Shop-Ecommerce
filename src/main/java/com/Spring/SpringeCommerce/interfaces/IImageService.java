package com.Spring.SpringeCommerce.interfaces;

import com.Spring.SpringeCommerce.dto.ImageDTO;
import com.Spring.SpringeCommerce.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    List<ImageDTO> saveImage(List<MultipartFile> file, Long productId);
    void updateImage(MultipartFile file, Long imageId);
    void deleteImageById(Long id);
}
