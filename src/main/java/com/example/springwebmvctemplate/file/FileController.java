package com.example.springwebmvctemplate.file;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @GetMapping(value = "",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] findFile(@RequestParam String filePath) throws IOException {
        return new FileSystemResource(filePath).getContentAsByteArray();
    }


}
