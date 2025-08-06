package in.vishwasprabhu18.billing_software.impl;

import in.vishwasprabhu18.billing_software.entity.CategoryEntity;
import in.vishwasprabhu18.billing_software.repository.CategoryRepository;
import in.vishwasprabhu18.billing_software.request.CategoryRequest;
import in.vishwasprabhu18.billing_software.response.CategoryResponse;
import in.vishwasprabhu18.billing_software.service.CategoryService;
import in.vishwasprabhu18.billing_software.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FileUploadService fileUploadService;
    @Override
    public CategoryResponse addCategory(CategoryRequest request) {
        CategoryEntity newCategory = convertToEntity(request);
        newCategory = categoryRepository.save(newCategory);
        return convertToResponse(newCategory);
    }

    @Override
    public CategoryResponse addCategoryWithAWSS3(CategoryRequest request, MultipartFile file) {
        String imgUrl = fileUploadService.uploadFile(file);
        CategoryEntity newCategory = convertToEntity(request);
        newCategory.setImgUrl(imgUrl);
        newCategory = categoryRepository.save(newCategory);
        return convertToResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::convertToResponse).toList();
    }

    @Override
    public void deleteCategory(String categoryId) {
        CategoryEntity category = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }

    @Override
    public void deleteCategoryWithAWSS3(String categoryId) {
        CategoryEntity category = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        fileUploadService.deleteFile(category.getImgUrl());
        categoryRepository.delete(category);
    }

    private CategoryEntity convertToEntity(CategoryRequest request) {
        return CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .imgUrl(request.getBgColor())
                .build();
    }

    private CategoryResponse convertToResponse(CategoryEntity category) {
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .bgColor(category.getBgColor())
                .imgUrl(category.getImgUrl())
                .createdAt(category.getCreateAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
