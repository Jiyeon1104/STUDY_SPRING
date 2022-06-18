package com.example.ex03.controller;

import com.example.ex03.domain.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.FileHandler;

/*
문제점 및 해결 방안
1. 동일한 이름으로 파일이 업로드 되었을 때 기존 파일이 사라지는 문제
2. 이미지 파일의 경우 원본 파일의 용량이 클 때 썸네일 이미지로 생성해야 하는 문제
3. 이미지 파일과 일반 파일을 구분해서 다운로드 혹은 페이지에서 조회할 수 있도록 처리해야 하는 문제
4. 첨부파일 공격에 대비하기 위한 업로드 파일의 확장자 제한
 */

@Controller
@Slf4j
@RequestMapping("/upload/*")
public class UploadController {
    @GetMapping("/uploadForm")
    public void uploadForm() {
        log.info("upload form");
    }

    @PostMapping("/uploadFormAction")
    public void upload(MultipartFile[] uploadFile) throws IOException {
        String uploadFolder = "C:/upload";
        for (MultipartFile file : uploadFile) {
            log.info("---------------");
            log.info("Upload File Name : " + file.getOriginalFilename());
            log.info("Upload File Size : " + file.getSize());

            File saveFile = new File(uploadFolder, file.getOriginalFilename());
            file.transferTo(saveFile);
        }
    }

    @GetMapping("/uploadAjax")
    public void uploadAjax() {
        log.info("upload ajax");
    }

    @PostMapping("/uploadAjaxAction")
    @ResponseBody
    public List<FileVO> uploadAjaxAction(MultipartFile[] uploadFile) throws IOException {
        String uploadFolder = "C:/upload";
        ArrayList<FileVO> files = new ArrayList<>();

        // UUID
        // 네트워크 상에서 각각의 객체들을 식별하기 위하여 사용됨
        // 중복될 가능성이 거의 없다고 인정되기 때문에 많이 사용됨
        // UUID 개수 : 많음

        // YYYY/MM/DD 경로 만들기
        File uploadPath = new File(uploadFolder, getFolder());
        if(!uploadPath.exists()){uploadPath.mkdirs();}

        for (MultipartFile file : uploadFile) {
            FileVO fileVO = new FileVO(); // 이전에 남아 있는 데이터가 현재 데이터에 꽂힐까봐
            String uploadFileName = file.getOriginalFilename();

            UUID uuid = UUID.randomUUID();
            uploadFileName = uuid.toString() + "_" + uploadFileName;
            fileVO.setFileName(uploadFileName);
            fileVO.setUuid(uuid.toString());
            fileVO.setUploadPath(getFolder());

            log.info("---------------");
            log.info("Upload File Name : " + uploadFileName);
            log.info("Upload File Size : " + file.getSize());

            File saveFile = new File(uploadPath, uploadFileName);
            file.transferTo(saveFile);

            if(checkImageType(saveFile)){
                FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
                Thumbnailator.createThumbnail(file.getInputStream(), thumbnail, 100, 100);
                thumbnail.close();
                fileVO.setImage(true);
            }
            files.add(fileVO);
        }
        return files;
    }

    @GetMapping("/display") // display로 요청
    @ResponseBody
    public byte[] getFile(String fileName) throws IOException{
        File file = new File("C:/upload/", fileName);
        return FileCopyUtils.copyToByteArray(file);
    }

    private boolean checkImageType(File file) throws IOException{
        String contentType = Files.probeContentType(file.toPath());
        return contentType.startsWith("image");

    }

    // 해당 파일에서만 쓰기 위함
    private String getFolder(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return sdf.format(date);
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(String fileName) throws UnsupportedEncodingException {
        Resource resource = new FileSystemResource("C:/upload/" + fileName); // 업캐스팅
        HttpHeaders header = new HttpHeaders();
        String name = resource.getFilename(); // UUID는 빼고 원하는 이름으로 보내주기 위함
        name.substring(name.indexOf("_") + 1);
        header.add("Content-Disposition", "attatchment;filename=" + new String(name.getBytes("UTF-8"), "ISO-8859-1"));
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(String fileName){
        File file = new File("C:/upload/", fileName);
        if(file.exists()){file.delete();}
        file = new File(fileName.replace("s_", ""));
        if(file.exists()){file.delete();}
    }
}

