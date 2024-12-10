package shopify.demo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import shopify.demo.dto.request.ProductRequestDto;
import shopify.demo.dto.request.ProductUpdateDto;
import shopify.demo.dto.response.ProductResponseDto;
import shopify.demo.model.entity.ProductEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDto toProductResponse(ProductEntity productEntity);
    List<ProductResponseDto> toListProductResponse(List<ProductEntity> productEntities);
    ProductEntity toProductEntity(ProductRequestDto productRequestDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductEntity toProductEntityFromProductUpdate(ProductUpdateDto productUpdateDto);


}
