package web_App;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import chat.ChatProvider;

import javax.security.auth.login.LoginException;





@WebServlet(name = "Chat", urlPatterns = "/chat")
public class ChatProviderServlet extends HttpServlet implements ChatProvider  {


    public HttpSession session;

    private int maxLength = 144;

   /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        session = request.getSession(true);

        if (session.getAttribute("username")== null){

            out.println("<html><body><b>FAIL</b></body></html>");
        } else {
            out.println(chatPage());

        }

       session.invalidate();
       response.sendRedirect("chat");

    }*/



    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        session = request.getSession(true);

        if (!session.getAttribute("username").equals("admin")){

            out.println("<html><body><b>FAIL</b></body></html>");
        } else{
            if(!request.getParameter("chat").equals("")) {


                String message = request.getParameter("chat");

                try {
                    onMessageReceived(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                response.sendRedirect("chat");
            }
        }
    }*/





    public int chatProvider(String username, String password) throws LoginException{

        if(username.equals("admin") && password.equals("admin")){ //Success

            return 1;
        } else throw new LoginException("Invalid username or password");
    }



    public String getName(){
        return "Test Double Chat Provider";
    }


    //Friend id will be useless in this case
    public int sendMessageTo(String friendID, String message) throws InterruptedException {//Message always sent successfully

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = Calendar.getInstance().getTime();

        String current = session.getAttribute("box").toString();
        session.setAttribute("box",current + "<br>" + dateFormat.format(now) + " (Me) - "+message);

        onMessageReceived(message);


        return 1;

    }



    public int getMaxMessageLength(){
        return maxLength;
    }


    public int onMessageReceived(String message) throws InterruptedException { //Called from sendTo



        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = Calendar.getInstance().getTime();
        TimeUnit.SECONDS.sleep(2);

        now = Calendar.getInstance().getTime();

        String current = session.getAttribute("box").toString();
        session.setAttribute("box",current + "<br>" + dateFormat.format(now) + " (Double) - Thank you");

        return 1;
    }






}
