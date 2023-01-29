package park.waiting.domain.store.dto;

import park.waiting.domain.store.status.OpenStatus;

public record StoreResponse(
        Long id,
        String name,
        String phoneNumber,
        OpenStatus openStatus,
        Integer openHours,
        String address,
        String description
) {
    public static StoreResponse of(
            Long id,
            String name,
            String phoneNumber,
            OpenStatus openStatus,
            Integer openHours,
            String address,
            String description
    ) {
        return new StoreResponse(id, name, phoneNumber, openStatus, openHours, address, description);
    }

    public static StoreResponse from(StoreDto storeDto) {
        if (storeDto == null) {
            return null;
        }
        return StoreResponse.of(
                storeDto.id(),
                storeDto.name(),
                storeDto.phoneNumber(),
                storeDto.openStatus(),
                storeDto.openHours(),
                storeDto.address(),
                storeDto.description()
        );
    }
}
