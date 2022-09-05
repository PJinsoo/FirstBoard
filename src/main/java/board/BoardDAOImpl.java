package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//DAO : DB에서 직접 접근하는 객체
public class BoardDAOImpl implements BoardDAO{

	//글목록 전체 보기
	@Override
	public List<BoardDTO> selectList(Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		//DB의 값을 저장하기 위한 List
		List<BoardDTO> res = new ArrayList<BoardDTO>();
		
		try {
			pstm = conn.prepareStatement(selectListSQL);
			rs = pstm.executeQuery(); //쿼리문을 실행
			
			//DB의 값을 boardNo를 순서로 res에 저장
			while(rs.next()) {
				BoardDTO tmp = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6));
				res.add(tmp);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	//게시글 하나 보기
	@Override
	public BoardDTO selectOne(Connection conn, int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	//새 글 쓰기
	@Override
	public boolean insert(Connection conn, BoardDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	//글 수정
	@Override
	public boolean update(Connection conn, BoardDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	//글 삭제
	@Override
	public boolean delete(Connection conn, int boardNo) {
		// TODO Auto-generated method stub
		return false;
	}

}