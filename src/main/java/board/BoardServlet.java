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
		String command = request.getParameter("command");
		BoardBiz biz = new BoardBizImpl();
		
		//클라이언트의 요청이 main이라면 게시판 목록을 띄워줌
		if(command.equals("main")) {
			
			//DB의 값을 list에 담은 후 main에게로 넘겨줌
			List<BoardDTO> list = biz.selectList();
			request.setAttribute("list", list);
			
			//main.jsp로 이동, forward 방식
			dispatch("main.jsp", request, response);
		}
		//클라이언트가 게시글을 클릭 했을 때
		//게시글 하나 보기
		else if(command.equals("one")) {
			
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			BoardDTO dto = biz.selectOne(boardNo);
			
			request.setAttribute("dto", dto);
			dispatch("selectOne.jsp", request, response);
		}
		//새 글 쓰기를 요청 했을 때
		else if(command.equals("insert")) {
			//insert 요청은 데이터를 담을 필요가 없어 바로 화면 출력
			response.sendRedirect("insert.jsp");
		}
		//새 글 쓰기를 완료 했을 때
		else if(command.equals("boardInsert")) {
			String writer = request.getParameter("boardWriter");
			String title = request.getParameter("boardTitle");
			String content = request.getParameter("boardContent");
			
			BoardDTO dto = new BoardDTO(writer, title, content);
			boolean res = biz.insert(dto);
			
			if(res) {
				jsResponse("새 글 작성 완료", "controller.do?command=main", response);
			} else {
				dispatch("controller.do?command=insert", request, response);
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