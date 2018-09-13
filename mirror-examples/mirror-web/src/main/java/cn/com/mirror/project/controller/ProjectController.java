package cn.com.mirror.project.controller;

import cn.com.mirror.nas.service.AsyncNasService;
import cn.com.mirror.nas.service.NasService;
import cn.com.mirror.project.service.ProjectInitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Api("Project interface")
@Slf4j
@RestController
@RequestMapping(value = "/unit/project")
public class ProjectController {

    @Autowired
    private NasService nasService;
    @Autowired
    private AsyncNasService asyncNasService;

    @Autowired
    private ProjectInitService projectInitService;

    @ApiOperation("Upload the project archive")
    @PostMapping(value = "/upload")
    public String uploadZipFile(MultipartFile multipartFile) {
        log.debug("File name: {}, size: {}", multipartFile.getOriginalFilename(),
                multipartFile.getContentType(), multipartFile.getSize());


        String filePath = null;
        try {
            filePath = nasService.uploadArchive(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        asyncNasService.unzipArchive(filePath);


        return null;
    }

    @ApiOperation("Get access code")
    @GetMapping(value = "/accessCode")
    public String getAccessCode() {
        String userId = "TEST";
        return projectInitService.getAccessCode(userId);
    }


    @ApiOperation("test get access code")
    @GetMapping(value = "/testAccessCode")
    public String getAccessCode(String userId) {
        return projectInitService.getAccessCode(userId);
    }


}
