package shopify.demo.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopify.demo.controller.route.CommonRoute;

import shopify.demo.controller.route.SocketRoute;
import shopify.demo.dto.request.SocketRequestDto;
import shopify.demo.dto.request.SocketUpdateDto;
import shopify.demo.service.ISocketService;
import shopify.demo.shared.ResponseEntityBuilder;

import java.util.UUID;


@RestController
@RequestMapping(CommonRoute.BASE_API + CommonRoute.VERSION)
public class SocketController {
    private final ISocketService socketService;

    public SocketController(ISocketService socketService) {
        this.socketService = socketService;
    }
    
    @GetMapping(SocketRoute.BASE_URL)
    public ResponseEntity<?> getAllSockets (
            @RequestParam(name = "offset", defaultValue = "0") final Integer offset,
            @RequestParam(name = "limit", defaultValue = "5") final Integer limit) {
        var pageable = PageRequest.of(offset, limit);
        var socketList = socketService.getAllSocket(pageable);
        return ResponseEntityBuilder
                .getBuilder()
                .setDetails(socketList)
                .build();
    }

    @PostMapping(SocketRoute.BASE_URL)
    public ResponseEntity<?> createSocket(@RequestBody SocketRequestDto socketRequestDto) {
        var socketResponse = socketService.createSocket(socketRequestDto);
        return ResponseEntity.ok(socketResponse);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(SocketRoute.BASE_URL + "/{socketId}")
    public ResponseEntity<?> findOneSocket (@PathVariable final UUID socketId) {
        var socketResponse = socketService.findSocketById(socketId);
        return ResponseEntity.ok(socketResponse);
    }

    @DeleteMapping(SocketRoute.BASE_URL + "/{socketId}")
    public ResponseEntity<?> deleteOneSocket (@PathVariable final UUID socketId) { socketService.deleteSocket(socketId);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping(SocketRoute.BASE_URL + "/{socketId}")
    public ResponseEntity<?> updateOneSocket (@PathVariable final UUID socketId, @RequestBody SocketUpdateDto socketUpdateDto) {
        var socketResponse =  socketService.updateSocket(socketId, socketUpdateDto);
        return ResponseEntity.ok(socketResponse);
    }
}
