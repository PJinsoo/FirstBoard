package board;

import java.sql.Connection;
import java.util.List;

/*
 * Business Component
 * biz는 컨트롤러로부터 요청과 요청에 필요한 매게변수들을 받고
 * DAO는 biz에게 데이터를 받아 DB에서 실제 CRUD 작업을 수행
 */

public class BoardBizImpl implements BoardBiz{
	
	//DAO에게 요청하기 위해 DAO 객체 생성
	private BoardDAO dao = new BoardDAOImpl();
	
	//게시글 전체 출력
	@Override
	public List<BoardDTO> selectList() {
		//BoardDTO는 데이터가 담길 그릇
		
		//DB에서 데이터를 받아 res에 List로 저장
		Connection conn = JDBCTemplate.getConnection();
		List<BoardDTO> res = dao.selectList(conn);
		
		return res;
	}

	//게시글 하나 보기
	@Override
	//컨트롤러(BoardServlet)로부터 boardNo를 매게변수로 받음
	public BoardDTO selectOne(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		//매개변수 boardNo를 PK로 하여 DAO를 통해 DB에서 데이터 Select 
		BoardDTO dto = dao.selectOne(conn, boardNo);
		JDBCTemplate.close(conn);
		
		return dto;
	}
	
	//조회수 카운팅, selectOne()과 함께 실행됨
	@Override
	public boolean countingView(BoardDTO dto) {
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO에서 조회수를 증가시킴, 조회수에 대한 update를 수행
		boolean res = dao.countingView(conn, dto);
		
		//DAO에서 작업이 올바르게 수행됐다면(res에 true가 리턴됨에 따라) 커밋
		if(res) {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		
		return res;
	}

	//새 글 쓰기 (Insert)
	@Override
	public boolean insert(BoardDTO dto) {
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO의 insert를 호출해 DB에 insert를 수행
		boolean res = dao.insert(conn, dto);
		
		//DAO에서 작업이 올바르게 수행됐다면 커밋
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
		
		//DAO의 update를 호출해 DB에 update를 수행
		boolean res = dao.update(conn, dto);
		
		//DAO에서 작업이 올바르게 수행됐다면 커밋
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
		
		//DAO의 delete()를 호출해 DB에서 같은 Key값 삭제
		boolean res = dao.delete(conn, boardNo);
		
		//DAO에서 작업이 올바르게 수행됐다면 커밋
		if(res) {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		
		return res;
	}

}