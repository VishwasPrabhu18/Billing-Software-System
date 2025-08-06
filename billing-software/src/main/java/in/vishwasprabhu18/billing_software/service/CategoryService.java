package in.vishwasprabhu18.billing_software.service;

import in.vishwasprabhu18.billing_software.request.CategoryRequest;
import in.vishwasprabhu18.billing_software.response.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest request);

    CategoryResponse addCategoryWithAWSS3(CategoryRequest request, MultipartFile file);

    List<CategoryResponse> getAllCategories();

    void deleteCategory(String categoryId);

    void deleteCategoryWithAWSS3(String categoryId);
}
