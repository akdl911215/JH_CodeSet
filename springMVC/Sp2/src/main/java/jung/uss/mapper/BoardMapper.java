package jung.uss.mapper;

import java.util.List;

import jung.uss.domain.Board;

public interface BoardMapper {
	List<Board> list();
	void insert(Board board);
	void delete(long seq);
	void update(Board board);
	Board content(long seq);
}
