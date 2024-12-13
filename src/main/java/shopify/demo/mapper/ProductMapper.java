package shopify.demo.mapper;

import org.mapstruct.*;

import shopify.demo.dto.request.ProductRequestDto;
import shopify.demo.dto.request.ProductUpdateDto;
import shopify.demo.dto.response.ProductResponseDto;
import shopify.demo.model.entity.ProductEntity;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    @Mapping(source = "socket.name", target = "socket")
    ProductResponseDto toProductResponse(ProductEntity productEntity);

    List<ProductResponseDto> toListProductResponse(List<ProductEntity> productEntities);

    @Mapping(source = "socketId", target = "socket.id")
    ProductEntity toProductEntity(ProductRequestDto productRequestDto);

    @Mapping(source = "socketId", target = "socket.id")
    void updateProductEntityFromProductUpdate(ProductUpdateDto productUpdateDto, @MappingTarget ProductEntity productEntity);

}
