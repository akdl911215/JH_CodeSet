package jung.uss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jung.uss.dao.BoardDao;
import jung.uss.domain.Board;
import jung.uss.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	//private BoardDao boardDao;
	private BoardMapper boardMapper;
	
	@Override
	public List<Board> listS() {
		
		//return boardDao.list();
		return boardMapper.list();
	}

	@Override
	public void insertS(Board board) {
		//boardDao.insert(board);
		boardMapper.insert(board);
	}

	@Override
	public void deleteS(long seq) {
		//boardDao.delete(seq);
		boardMapper.delete(seq);
	}

	@Override
	public void updateS(Board board) {
		 boardMapper.update(board);
	}

	@Override
	public Board contentS(long seq) {
		// TODO Auto-generated method stub
		return boardMapper.content(seq);
	}

}
