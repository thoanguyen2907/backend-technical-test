package shopify.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shopify.demo.dto.request.SocketRequestDto;
import shopify.demo.dto.request.SocketUpdateDto;
import shopify.demo.dto.response.SocketResponseDto;
import shopify.demo.exception.ErrorCode;
import shopify.demo.exception.ShopifyRuntimeException;

import shopify.demo.mapper.SocketMapper;
import shopify.demo.repository.SocketRepository;
import shopify.demo.service.ISocketService;
import shopify.demo.shared.PageList;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SocketService implements ISocketService {
    private static final Logger logger = LogManager.getLogger(SocketService.class);
    private final SocketRepository socketRepository;
    private final SocketMapper socketMapper;

    @Override
    public PageList<SocketResponseDto> getAllSocket(Pageable pageable) {
        var socketList = socketRepository.findAll(pageable);
        var socketResponseList = socketList.getContent()
                .stream()
                .map(socketMapper::toSocketResponse)
                .toList();
        logger.info("Get all products successfully");
        return buildPaginatingResponse(socketResponseList, pageable.getPageSize(), pageable.getPageNumber(), socketList.getTotalElements());
    }

    @Override
    public SocketResponseDto createSocket(SocketRequestDto socketRequestDto) {
        var socket = socketMapper.toSocketEntity(socketRequestDto);
        var createdSocket = socketRepository.save(socket);
        logger.info("Create socket successfully");
        return socketMapper.toSocketResponse(createdSocket);
    }

    @Override
    public SocketResponseDto findSocketById(UUID socketId) throws ShopifyRuntimeException {
        var foundSocket = socketRepository.findById(socketId).orElse(null);
            if(foundSocket == null) {
                throw new ShopifyRuntimeException(ErrorCode.ID_NOT_FOUND);
            }
            logger.info("Find product successfully");
        return socketMapper.toSocketResponse(foundSocket);
    }

    @Override
    public void deleteSocket(UUID socketId) throws ShopifyRuntimeException {
        var foundSocket = socketRepository.findById(socketId).orElse(null);
        if(foundSocket == null) {
            throw new ShopifyRuntimeException(ErrorCode.ID_NOT_FOUND);
        }
        socketRepository.delete(foundSocket);
        logger.info("Delete socket successfully");
    }

    @Override
    public SocketResponseDto updateSocket(UUID socketId, SocketUpdateDto socketUpdateDto) throws ShopifyRuntimeException {

        var foundSocket = socketRepository.findById(socketId).orElse(null);
        if(foundSocket == null) {
            throw new ShopifyRuntimeException(ErrorCode.ID_NOT_FOUND);
        }
        socketMapper.updateSocketEntityFromSocketUpdate(socketUpdateDto, foundSocket);

        var updatedSocket = socketRepository.save(foundSocket);
        return socketMapper.toSocketResponse(updatedSocket);
    }

    private PageList<SocketResponseDto> buildPaginatingResponse(final List<SocketResponseDto> responses,
                                                               final int pageSize,
                                                               final int currentPage,
                                                               final long total) {
        return PageList.<SocketResponseDto>builder()
                .records(responses)
                .limit(pageSize)
                .offset(currentPage)
                .totalRecords(total)
                .totalPage((int) Math.ceil(total * 1.0 / pageSize))
                .build();
    }
}
