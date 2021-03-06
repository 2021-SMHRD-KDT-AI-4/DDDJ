package UserService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.UserDAO;
import DTO.CheckDTO;
import DTO.UserDTO;

@WebServlet("/JoinService")
public class JoinService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String tel = request.getParameter("tel");
		
		UserDTO dto = new UserDTO(id, pw, name, category, age,gender,tel);
		UserDAO dao = new UserDAO();
		CheckDTO check;
		int cnt = dao.join(dto);
		
		if (cnt > 0) {
			System.out.println("회원가입 성공");
			check = new CheckDTO(true);
		}else {
			System.out.println("회원가입 실패");
			check = new CheckDTO(false);
		}
		Gson gson = new Gson();
		
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(check));
	}
}
