package jung.uss.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariDataSource;


import jung.uss.domain.Board;
import lombok.extern.log4j.Log4j;

@Log4j
@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private HikariDataSource dataSource;

	@Override
	public List<Board> list() {
		List<Board> list = new ArrayList<Board>();
		Connection con = null;
		Statement stmt = null;
		String sql = "select * from BOARD order by SEQ desc";
		ResultSet rs = null;
		try {
			while(rs.next()) { // rs.next 질문하기  .next = 선택한 요소의 바로 다음에 위치한 형제 요소를 선택한다.
				long seq = rs.getLong(1);
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
//				String content = rs.getString(5);
				Date rdate = rs.getDate(6);
//				String fname = rs.getString(7);
//				String ofname = rs.getString(8);
//				String fsize = rs.getString(9);
//				list.add(new Board(seq, writer, email, subject, content, rdate, fname, ofname, fsize));
			}
		} catch(SQLException se) {
			log.info("BoardImpl list() se : " + se);
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
		
		return list;
	}

	@Override
	public void insert(Board board){
		Connection con = null;
		/*
		 PreparedStatement는 처음 한 번만 세 단계를 거친 후 캐시에 담아 재사용을 한다는 것이다. 
		 만약 동일한 쿼리를 반복적으로 수행한다면 PreparedStatment가 DB에 훨씬 적은 부하를 주며, 성능도 좋다.
		 */
		PreparedStatement pstmt = null;
		String sql = "insert into ADDRESS values(BOARD_SEQ.nextval, ?, ?, ?, SYSDATE)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getEmail());
			pstmt.setString(3, board.getSubject());
//			pstmt.setString(4, board.getContent());
			pstmt.executeUpdate();
//			pstmt.setString(5, board.getFname());
//			pstmt.setString(6, board.getOfname());
//			pstmt.setString(7, board.getFsize());
		} catch(SQLException se) {
			
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(SQLException se) {
				
			}
		}
		
	}

	@Override
	public void delete(long seq){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from BOARD where SEQ=?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, seq);
			pstmt.executeUpdate();
		} catch(SQLException se) {
			
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(SQLException se) {
				
			}
		}
		
	}

}
