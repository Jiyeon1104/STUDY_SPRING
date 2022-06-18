package com.example.board.controller;

/*
* 문제점 및 해결방안
* 1. 동일한 이름으로 파일이 업로드 되었을 때 기존 파일이 사라지는 문제
* 2. 이미지 파일의 경우 원본 파일의 용량이 클 때 썸네일 이미지로 생성해야 하는 문제
* 3. 이미지 파일과 일반 파일을 구분해서 다운로드 혹은 페이지에서 조회할 수 있도록 처리해야 하는 문제
* 4. 첨부파일 공격에 대비하ㅏ기 위한 업로드 파일의 확장자 제한
*
* */

import com.example.board.domain.vo.FileVO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
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

@Controller
@Slf4j
@RequestMapping("/upload/*")
@RequiredArgsConstructor
public class FileController {
    private final BoardService boardService;

    @PostMapping("/upload")
    @ResponseBody
    public List<FileVO> upload(MultipartFile[] uploadFiles) throws IOException {
        String uploadFolder = "C:/upload";
        ArrayList<FileVO> files = new ArrayList<>();

//        yyyy/MM/dd 경로 만들기
        File uploadPath = new File(uploadFolder, getFolder());
        if(!uploadPath.exists()){uploadPath.mkdirs();}

        for(MultipartFile file : uploadFiles){
            FileVO fileVO = new FileVO();
            String uploadFileName = file.getOriginalFilename();

            UUID uuid = UUID.randomUUID();
            fileVO.setFileName(uploadFileName);
            fileVO.setUuid(uuid.toString());
            fileVO.setUploadPath(getFolder());

            uploadFileName = uuid.toString() + "_" + uploadFileName;

            log.info("--------------------------------");
            log.info("Upload File Name : " + uploadFileName);
            log.info("Upload File Size : " + file.getSize());

            fileVO.setFileSize(file.getSize());

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

    @GetMapping("/display")
    @ResponseBody
    public byte[] getFile(String fileName) throws IOException{
        File file = new File("C:/upload/", fileName);
        return FileCopyUtils.copyToByteArray(file);
    }

    private boolean checkImageType(File file) throws IOException{
        String contentType = Files.probeContentType(file.toPath());
        return contentType.startsWith("image");
    }

    private String getFolder(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return sdf.format(date);
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(String fileName) throws UnsupportedEncodingException {
        Resource resource = new FileSystemResource("C:/upload/" + fileName);
        HttpHeaders header = new HttpHeaders();
        String name = resource.getFilename();
        name = name.substring(name.indexOf("_") + 1);
        header.add("Content-Disposition", "attachment;filename="+ new String(name.getBytes("UTF-8"), "ISO-8859-1"));
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(String fileName){
        File file = new File("C:/upload/", fileName);
        if(file.exists()){ file.delete(); }

        file = new File("C:/upload/", fileName.replace("s_", ""));
        if(file.exists()){ file.delete(); }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<FileVO> getList(Long boardBno){
        log.info("get file list....... : " + boardBno);
        return boardService.getList(boardBno);
    }
}














