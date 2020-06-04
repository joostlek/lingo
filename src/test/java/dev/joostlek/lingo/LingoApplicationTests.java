package dev.joostlek.lingo;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
class LingoApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }

}
