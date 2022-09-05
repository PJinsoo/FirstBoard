package board;

import java.sql.Connection;
import java.util.List;

//biz : 컨트롤러에서 요청을 받아 넘겨주는 역할
public class BoardBizImpl implements BoardBiz{
	private BoardDAO dao = new BoardDAOImpl();

	//게시글 전체 출력
	@Override
	public List<BoardDTO> selectList() {
		
		//DB에서 데이터를 받아 res에 List로 저장
		Connection conn = JDBCTemplate.getConnection();
		List<BoardDTO> res = dao.selectList(conn);
		
		//res 반환
		return res;
	}

	//게시글 하나 보기
	@Override
	public BoardDTO selectOne(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		BoardDTO dto = dao.selectOne(conn, boardNo);
		JDBCTemplate.close(conn);
		
		return dto;
	}

	//새 글 쓰기
	@Override
	public boolean insert(BoardDTO dto) {
		Connection conn = JDBCTemplate.getConnection();
		boolean res = dao.insert(conn, dto);
		if(res) {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return res;
	}

	//글 수정
	@Override
	public boolean update(BoardDTO dto) {
		Connection conn = JDBCTemplate.getConnection();
		boolean res = dao.update(conn, dto);
		
		if(res) {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		
		return res;
	}

	//글 삭제
	@Override
	public boolean delete(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		boolean res = dao.delete(conn, boardNo);
		
		if(res) {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		
		return res;
	}

}