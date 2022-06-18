package com.example.board.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BoardVO {
    private Long boardBno;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private String boardRegisterDate;
    private String boardUpdateDate;

//    input태그의 name에
//    fileList[i].fileName
//    fileList[i].uploadPath
//    fileList[i].uuid
//    fileList[i].image
    private List<FileVO> fileList;
}















