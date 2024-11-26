package carlos.bonfatti.config.util;

import carlos.bonfatti.config.pojo.Post;

import java.util.UUID;

public class TestDataGenerator {
    public static Post generatePost() {
        return new Post(0,
                "Title " + UUID.randomUUID(),
                "Body " + UUID.randomUUID(),
                1
        );
    }
}
