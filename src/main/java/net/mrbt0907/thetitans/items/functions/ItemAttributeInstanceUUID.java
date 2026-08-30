package net.mrbt0907.thetitans.items.functions;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemAttributeInstanceUUID {
    public static final Map<String, UUID> UUID_MAP = new HashMap<>();

    public static UUID createUUID(String uuidName) {
        if (uuidName == null || uuidName.trim().isEmpty()) {
            throw new IllegalArgumentException("uuidName cannot be null or empty");
        }

        return UUID_MAP.computeIfAbsent(
                uuidName,
                key -> UUID.nameUUIDFromBytes(key.getBytes(StandardCharsets.UTF_8))
        );
    }
}
