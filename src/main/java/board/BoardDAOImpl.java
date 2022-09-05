package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Data Access Object
//DAO는 DB에서 직접 접근하여 CRUD를 수행하는 객체
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
		PreparedStatement pstm = null;
		ResultSet rs = null;
		BoardDTO res = null;
		
		try {
			pstm = conn.prepareStatement(selectOneSQL);
			pstm.setInt(1,boardNo);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				res = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstm);
		}
	
		return res;
	}
	
	//조회수 카운팅
	@Override
	public boolean countingView(Connection conn, BoardDTO dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = conn.prepareStatement(viewCountUpSQL);
			pstm.setInt(1, dto.getBoardNo());
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstm);
		}
		return (res>0) ? true : false;
	}

	//새 글 쓰기
	@Override
	public boolean insert(Connection conn, BoardDTO dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			//value 값이 될 인자들을 삽입 
			pstm = conn.prepareStatement(insertSQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstm);
		}
		
		return (res>0) ? true : false;
	}

	//글 수정
	@Override
	public boolean update(Connection conn, BoardDTO dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = conn.prepareStatement(updateSQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getBoardNo());
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstm);
		}
		return (res>0) ? true : false;
	}

	//글 삭제
	@Override
	public boolean delete(Connection conn, int boardNo) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = conn.prepareStatement(deleteSQL);
			pstm.setInt(1, boardNo);
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstm);
		}
		return (res>0) ? true : false;
	}

}