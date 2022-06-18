package com.example.board.mapper;

import com.example.board.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    public void insert(FileVO fileVO);
    public void delete(Long boardBno);
    public List<FileVO> findByBoardBno(Long boardBno);
    public List<FileVO> getOldFiles();
}
