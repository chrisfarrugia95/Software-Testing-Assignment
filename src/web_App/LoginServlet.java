package web_App;

import chat.ChatSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

        HttpSession session;
        ChatProviderServlet test = new ChatProviderServlet();
        ChatSession s = new ChatSession(test);
        private int login = 1;



        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                session = request.getSession(true);
                test.session = session;
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                int send = 0;

                if(session.getAttribute("username") == null){//If user is not logged in
                        login = s.initSession(request.getParameter("username"),request.getParameter("password"),"1");
                        s.setLock(true);//Turn on lock
                        session.setAttribute("chat", "");
                        session.setAttribute("box", "");
                        session.setAttribute("username",request.getParameter("username"));
                }
                if (login != 0){ //If invalid username and password were used
                        out.println(loginError());
                        session.setAttribute("username",null);

                } else {//Successful Login

                        if(request.getParameter("out")!=null){//Log out button pressed
                                login = 1;
                                session.setAttribute("username",null);
                                session.invalidate();
                                out.println(loginPage());
                                return;
                        }

                        out.println(chatPage());
                        String toSend = request.getParameter("chat");
                        try {

                                 send = s.sendMessage(toSend);


                        } catch (Exception e) {

                                e.printStackTrace();
                        }
                        if(send == 4){//Message contains foul word
                                out.println(chatPageViolation());
                        } else {
                                response.sendRedirect("login");
                        }


                }
        }


        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                HttpSession session = request.getSession(true);
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();


                if(session.getAttribute("chat") == null){//Access login page or chat page
                        response.setHeader("Content-Disposition","inline; filename=Title");
                        out.println(loginPage());
                } else {
                        response.setHeader("Content-Disposition","inline; filename=Title");
                        out.println(chatPage());
                }


        }

        private String loginPage(){


                return ("<html>\n" +
                        "<head>\n" +
                        "<title>Login Page</title>\n" +
                        "<style>\n" +
                        ".center {\n" +
                        "    margin: auto;\n" +
                        "    width: 20%;\n" +
                        "    border: 2px solid #000000;\n" +
                        "    padding: 20px;\n" +
                        "}\n" +
                        "</style>\n" +
                        "\n" +
                        "\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "\t<div id = \"content\" class = \"center\">\n" +
                        "\n" +
                        "\t<b>Kellimnifejtrid Login Page</b>\n" +
                        "\n" +
                        "\t<br><br>\n" +
                        "\n" +
                        "\t<form method = \"POST\"name = \"login\" value = \"login\">\n" +
                        "\t  Username:\n" +
                        "\t  <input type=\"text\"  name=\"username\" value=\"username\">\n" +
                        "\t  <br><br>\n" +
                        "\t  Password: \n" +
                        "\t  <input type=\"text\" name=\"password\" value=\"password\">\n" +
                        "\t  <br><br>\n" +
                        "\t  <input type=\"submit\" name=\"Log in\"value=\"Log in\">\n" +
                        "\n" +
                        "\t</form>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\t</div>\n" +
                        "\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>");


        }

        private String loginError(){

                return ("<center><h2><b>Invalid Username or Password</b></h2></center>");


        }
        protected String chatPage(){
                return ("<html>\n" +
                        "<head>\n" +
                        "<title>Chat Page</title>\n" +
                        "<style>\n" +
                        ".center {\n" +
                        "    margin: auto;\n" +
                        "    width: 500px;\n" +
                        "    height: 350px;\n" +
                        "    border: 2px solid #000000;\n" +
                        "    padding: 10px;\n" +
                        "}\n" +
                        "\n" +
                        ".text {\n" +
                        "    margin: auto;\n" +
                        "    width: 450px;\n" +
                        "    height: 180px;\n" +
                        "    border: 2px solid #000000;\n" +
                        "    padding: 10px;\n" +
                        "}\n" +
                        "</style>\n" +
                        "\n" +
                        "\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "\n" +
                        "\t<div  id = \"content\" class = \"center\">\n" +
                        "\n" +
                        "\t<center><b>Kellimnifejtrid Chat Page</b></center><br>\n" +
                        "\n" +
                        "\n" +
                        "\t<align = \"right\"><form method = \"POST\">\n" +
                        "<input type=\"submit\" name = \"out\"value=\"Log Out\"></form>"+
                        "\n" +
                        "<br><div id = \"window\" class = \"text\">\n" +
                        "\t"+session.getAttribute("box")+"\n" +
                        "</div>\n" +
                        "\n" +
                        "<br>\n" +
                        "\n" +
                        "<center><form method = \"POST\">\n" +
                        "\tChat:\n" +
                        "\t<input type=\"text\" name=\"chat\">\n" +
                        "\t<input type=\"submit\" name = \"send\"value=\"Send\">\n" +
                        "\n" +
                        "</form></center>\n" +
                        "\t\t\n" +
                        "</div>\n" +
                        "\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>");
        }


        private String chatPageViolation() {
                return ("<center><h1><div id = \"error\"><b>Parental-Lock Violated</b><h1></div></center>");

        }
}
