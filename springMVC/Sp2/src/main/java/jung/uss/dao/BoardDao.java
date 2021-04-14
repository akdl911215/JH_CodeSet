package jung.uss.dao;

import java.util.List;

import jung.uss.domain.Board;

public interface BoardDao {
	List<Board> list();
	void insert(Board board);
	void delete(long seq);
}
