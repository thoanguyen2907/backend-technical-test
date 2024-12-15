package shopify.demo.service;

import org.springframework.data.domain.Pageable;

import shopify.demo.dto.request.SocketRequestDto;
import shopify.demo.dto.request.SocketUpdateDto;
import shopify.demo.dto.response.SocketResponseDto;
import shopify.demo.shared.PageList;

import java.util.UUID;

public interface ISocketService {
    PageList<SocketResponseDto> getAllSocket(Pageable pageable);
    SocketResponseDto createSocket(SocketRequestDto socketRequestDto);
    SocketResponseDto findSocketById(UUID socketId);
    void deleteSocket(UUID socketId);
    SocketResponseDto updateSocket(UUID socketId, SocketUpdateDto socketUpdateDto);
}
