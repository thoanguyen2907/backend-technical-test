package shopify.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import shopify.demo.dto.request.SocketRequestDto;
import shopify.demo.dto.request.SocketUpdateDto;
import shopify.demo.dto.response.SocketResponseDto;
import shopify.demo.model.entity.SocketEntity;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SocketMapper {

    SocketResponseDto toSocketResponse(SocketEntity socketEntity);

    List<SocketResponseDto> toListSocketResponse(List<SocketEntity> socketEntities);

    SocketEntity toSocketEntity(SocketRequestDto socketRequestDto);

    void updateSocketEntityFromSocketUpdate(SocketUpdateDto socketUpdateDto, @MappingTarget SocketEntity socketEntity);

}
