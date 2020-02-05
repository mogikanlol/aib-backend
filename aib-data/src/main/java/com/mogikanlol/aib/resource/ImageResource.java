package com.mogikanlol.aib.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@RestController
public class ImageResource {

    @GetMapping("/images/{title}")
    public byte[] getImage(@PathVariable("title") String title) throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("images/" + title);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            outputStream.write(data, 0, nRead);
        }
        outputStream.flush();
        return outputStream.toByteArray();
    }
}
