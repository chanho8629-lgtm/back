package com.app.ggshop.v1.controller.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/files/**")
@RequiredArgsConstructor
public class FileController {

    private final ResourceLoader resourceLoader;

    @GetMapping("display")
    public byte[] display(String filePath, String fileName) throws IOException {
        File file = new File("C:/file/" + filePath, fileName); // C:/file/ 이 경로가 있어야 합니다.
        if (!file.exists()) {
            return new byte[0];
        }
        return FileCopyUtils.copyToByteArray(file);
    }
}
