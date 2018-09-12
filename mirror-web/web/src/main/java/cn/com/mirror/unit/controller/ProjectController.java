package cn.com.mirror.unit.controller;

import cn.com.mirror.nas.service.NasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/unit/project")
public class ProjectController {

    @Autowired
    private NasService nasService;

    @PostMapping(value = "/upload")
    public String uploadZipFile(MultipartFile multipartFile) {
        log.debug("File name: {}, size: {}", multipartFile.getOriginalFilename(),
                multipartFile.getContentType(), multipartFile.getSize());


        try {
            nasService.uploadArchive(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
