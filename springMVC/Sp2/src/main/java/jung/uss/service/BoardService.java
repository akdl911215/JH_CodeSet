package jung.uss.service;

import java.util.List;

import jung.uss.domain.Board;

public interface BoardService {
	List<Board> listS();
	void insertS(Board board);
	void deleteS(long seq);
	void updateS(Board board);
	Board contentS(long seq);
}
