package board;

import java.sql.Connection;
import java.util.List;

public interface BoardDAO {
	String selectListSQL = "select * from board";
	
	//게시글 목록 출력
	public List<BoardDTO> selectList(Connection conn);
	
	//게시글 하나 출력
	public BoardDTO selectOne(Connection conn, int boardNo);
	
	//게시글 작성
	public boolean insert(Connection conn, BoardDTO dto);
	
	//게시글 수정
	public boolean update(Connection conn, BoardDTO dto);
	
	//게시글 삭제
	public boolean delete(Connection conn, int boardNo);
}