package park.waiting.domain.store.dto;

import park.waiting.domain.store.entity.Store;
import park.waiting.domain.store.status.OpenStatus;

public record StoreDto(
        Long id,
        String name,
        String phoneNumber,
        OpenStatus openStatus,
        Integer openHours,
        String address,
        String description
) {
    public static StoreDto of(
            Long id,
            String name,
            String phoneNumber,
            OpenStatus openStatus,
            Integer openHours,
            String address,
            String description
    ) {
        return new StoreDto(id, name, phoneNumber, openStatus, openHours, address, description);
    }

    public static StoreDto of(Store store) {
        return new StoreDto(
                store.getId(),
                store.getName(),
                store.getPhoneNumber(),
                store.getOpenStatus(),
                store.getOpenHours(),
                store.getAddress(),
                store.getDescription()
        );
    }

    public Store toEntity() {
        return Store.of(name, phoneNumber, openStatus, openHours, address, description);
    }

    public Store updateEntity(Store store) {
        if (name != null) { store.setName(name); }
        if (phoneNumber != null) { store.setPhoneNumber(phoneNumber);}
        if (openStatus != null) { store.setOpenStatus(openStatus);}
        if (openHours != null) { store.setOpenHours(openHours);}
        if (address != null) { store.setAddress(address);}
        if (description != null) { store.setDescription(description);}

        return store;
    }
}
