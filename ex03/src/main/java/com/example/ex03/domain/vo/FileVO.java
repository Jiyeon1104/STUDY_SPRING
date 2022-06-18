package com.example.ex03.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileVO {
    private String fileName;
    private String uploadPath;
    private String uuid;
    private boolean image;
}
