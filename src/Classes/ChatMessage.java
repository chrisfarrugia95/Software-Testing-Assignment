package Classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatMessage {

    public String timestamp;
    public String content;


    public ChatMessage(Date d, String message){
        setTimestamp(d);
        setContent(message);
    }







    public void setTimestamp(Date d){

        /*DateFormat df = new SimpleDateFormat("DD/MM/YYYY-HH:MM:SS");
        String reportDate = df.format(today);*/
        String reportDate = "14/11/2015-00:00:01";
        timestamp = reportDate;
    }

    public void setContent(String m){

        //content = m;
        content = "This is a dummy";

    }



    public String getContent(){

        return content;
    }

    public String getTimestamp(){
        return timestamp;
    }









}
