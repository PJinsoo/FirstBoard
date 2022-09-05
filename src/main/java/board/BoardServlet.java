package board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//컨트롤러
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public BoardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//요청받을 변수
		//command에게 건물 이름을 알려주어 이동하는 느낌
		String command = request.getParameter("command");
		
		//컨트롤러에서의 요청을 biz가 넘겨 받고, biz는 dao로부터 실제 DB데이터를 받음
		BoardBiz biz = new BoardBizImpl();
		
		//게시판 메인화면 출력
		if(command.equals("main")) {
			
			//DB의 모든 게시글을 List로 담기
			List<BoardDTO> list = biz.selectList();
			
			//담긴 List를 view로 보내줌
			request.setAttribute("list", list);
			
			//main.jsp로 이동, forward 방식
			dispatch("main.jsp", request, response);
		}
		
		//사용자가 게시글을 클릭 했을 때
		//게시글 하나 보기
		else if(command.equals("one")) {
			
			//사용자가 클릭한 게시글의 Key값을 저장
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			//사용자가 클릭한 게시글의 정보를 biz에게 요청함
			BoardDTO dto = biz.selectOne(boardNo);
			
			//응답된 게시글의 내용을 view로 전송
			request.setAttribute("dto", dto);
			dispatch("selectOne.jsp", request, response);
		}
		
		//새 글 쓰기 페이지 요청(단순 페이지 요청)
		else if(command.equals("insert")) {
			//단순 페이지 요청이기 때문에 데이터를 담을 필요 없이 새 글 쓰기 페이지로 이동시킴
			response.sendRedirect("insert.jsp");
		}
		
		//새 글 쓰기 수행 (Insert)
		else if(command.equals("boardInsert")) {
			
			//수정에 필요한 게시글의 정보를 담음
			String writer = request.getParameter("boardWriter");
			String title = request.getParameter("boardTitle");
			String content = request.getParameter("boardContent");
			
			//DTO에 수정을 요구하는 게시글의 정보를 담고
			BoardDTO dto = new BoardDTO(writer, title, content);
			//biz에게 전달, biz는 dao에게 전달하여 실제 DB에 반영(insert를 수행)
			boolean res = biz.insert(dto);
			
			if(res) { //작성에 성공했다면 성공 alert 창을 띄워줌
				jsResponse("새 게시글이 작성되었습니다.", "controller.do?command=main", response);
			} else { //작성에 실패했다면 다시 insert.jsp 페이지로 돌려보냄
				dispatch("controller.do?command=insert", request, response);
			}
		}
		
		//게시글의 수정 페이지 요청
		//insert 페이지와는 다르게 수정을 원하는 게시글의 PK를 알아야 함
		else if(command.equals("update")) {
			
			//수정을 요구한 게시글의 Key값을 저장
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			//수정을 요구한 게시글의 정보를 그대로 저장
			BoardDTO dto = biz.selectOne(boardNo);
			
			//update.jsp로 이동하면서 dto에 저장된 정보를 그대로 출력
			request.setAttribute("dto", dto);
			dispatch("update.jsp", request, response);
		}
		
		//게시글 수정 수행 (Update)
		else if(command.equals("boardUpdate")) {
			//수정을 요구한 게시글의 key값을 저장
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			//수정에 필요로 하는 게시글의 정보를 저장
			String title = request.getParameter("boardTitle");
			String content = request.getParameter("boardContent");
			
			//dto에게 필요한 정보만으로 생성자 초기화
			BoardDTO dto = new BoardDTO(boardNo, title, content);
			
			//해당 정보를 biz에게 넘겨주며, biz는 dao를 통해 실제 DB에 반영
			boolean res = biz.update(dto);
			
			if(res) {
				jsResponse("게시글이 수정되었습니다.", "controller.do?command=one&boardNo="+boardNo, response);
			} else {
				dispatch("controller.do?command=update&boardNo="+boardNo, request, response);
			}
		}
		
		//게시글 삭제
		else if(command.equals("delete")) {
			//삭제할 게시글의 PK를 저장
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			//biz에게 게시글 삭제를 요청
			boolean res = biz.delete(boardNo);
			
			if(res) {
				jsResponse("게시글이 삭제되었습니다.", "controller.do?command=main", response);
			} else {
				dispatch("controller.do?command=update&boardNo="+boardNo, request, response);
			}
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	//dispatcher -> forward 방식
	//클라이언트 요청에 전송한 데이터 유지
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	//알림창 alert 메서드
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String alert = "<script type='text/javascript'>" +
					   "alert('"+msg+"');" +
					   "location.href='"+url+"';" +
					   "</script>";
		PrintWriter out = response.getWriter();
		out.print(alert);
	}
}