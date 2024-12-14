package shopify.demo.socket;

import shopify.demo.model.entity.SocketEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class SocketTestApi {
    public static SocketEntity makeSocketForSaving(final UUID socketId) {
        return SocketEntity.builder()
                .id(socketId)
                .name("NH-D15 Chromax")
                .build();
    }

    public static List<SocketEntity> createMockSocketList() {
        List<SocketEntity> sockets = new ArrayList<>();

        SocketEntity socketFirst = SocketEntity.builder()
                .id(UUID.fromString("404b7894-52c8-45e9-8683-e578ed658ac3"))
                .name("TRX4")
                .build();

        SocketEntity socketSecond = SocketEntity.builder()
                .id(UUID.fromString("a3f567d8-3c7b-42e7-a6b1-9b3f8d1b0e92"))
                .name("AM4")
                .build();

        SocketEntity socketThird = SocketEntity.builder()
                .id(UUID.fromString("8f3e2a91-6a35-45cb-b229-5a92768a4a45"))
                .name("LGA1700")
                .build();

        SocketEntity socketFourth = SocketEntity.builder()
                .id(UUID.fromString("c07d1e25-4d2a-4905-8dd3-9f3e3d6d028f"))
                .name("LGA1200")
                .build();

        sockets.add(socketFirst);
        sockets.add(socketSecond);
        sockets.add(socketThird);
        sockets.add(socketFourth);

        return sockets;

    }

}
