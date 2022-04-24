package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.mybatis.Ch14BoardDao;
import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Pager;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class Ch14BoardService {
	@Resource
	private Ch14BoardDao boardDao;

	public int getTotalBoardNum() {
		return boardDao.count();
	}

	public List<Ch14Board> getBoards(Pager pager) {
		return boardDao.selectByPage(pager);
	}

	public void writeBoard(Ch14Board board) {
		boardDao.insert(board);
		log.info("저장된 게시물 번호: " + board.getBno());
	}

	public Ch14Board getBoard(int bno) {
		return boardDao.selectByBno(bno);
	}

	public void updateBoard(Ch14Board board) {
		boardDao.update(board);
	}

	public void removeBoard(int bno) {
		boardDao.deleteByBno(bno);
	}

}
