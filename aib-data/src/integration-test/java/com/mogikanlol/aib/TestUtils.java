package com.mogikanlol.aib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Files;

@Slf4j
public class TestUtils {

    private TestUtils() {
    }

    public static String readResourceAsString(String resourcePath) throws Exception {
        log.info("Loading resource {}", resourcePath);
        return new String(Files.readAllBytes(new ClassPathResource(resourcePath).getFile().toPath()));
    }
}
