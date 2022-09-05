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
		
		//PreparedStatement : 쿼리문 실행을 위한 인터페이스
		//ResultSet : Select문 실행의 결과 값(레코드 셋)을 저장하는 인터페이스
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		//DB의 값을 저장하기 위한 List
		List<BoardDTO> res = new ArrayList<BoardDTO>();
		
		try {
			pstm = conn.prepareStatement(selectListSQL); //쿼리문 실행
			rs = pstm.executeQuery(); //pstm의 실행 결과 저장
			
			/* ResultSet 객체의 next()는 레코드 셋을 다음으로 이동 시켜주는 메서드
			 * next()을 반복 수행하면서 res에 값을 순서대로 저장함
			 * res에는 모든 게시글이 저장됨
			 */
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
			pstm.setInt(1,boardNo); //쿼리문 첫 번째 ?에 boardNo이 전달됨
			
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
		
		//res가 0보다 작다면 쿼리가 제대로 실행되지 않았음
		//따라서 false를 반환해 실행을 거부
		return (res>0) ? true : false;
	}

	//새 글 쓰기
	@Override
	public boolean insert(Connection conn, BoardDTO dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
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