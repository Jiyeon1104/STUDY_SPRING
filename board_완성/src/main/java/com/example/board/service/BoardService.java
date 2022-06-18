package com.example.board.service;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.FileVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    public List<FileVO> getList(Long boardBno);

    public void register(BoardVO boardVO);
    public BoardVO read(Long boardBno);
    public boolean modify(BoardVO boardVO);
    public boolean remove(Long boardBno);
    public List<BoardVO> getList(Criteria criteria);
    public int getTotal();
}
