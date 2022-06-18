package com.example.board.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileVO {
    private String fileName;
    private String uploadPath;
    private String uuid;
    private boolean image;
    private Long fileSize;
    private Long boardBno;
}
