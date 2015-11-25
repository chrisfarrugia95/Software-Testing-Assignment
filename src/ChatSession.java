import org.mockito.verification.Timeout;

import javax.security.auth.login.LoginException;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeoutException;

public class ChatSession {

    private boolean parentalLock;
    private String friendID;
    public List<ChatMessage> receivedMessages;
    public ChatProvider p;


    public ChatSession(ChatProvider provider){

        receivedMessages = new ArrayList<ChatMessage>();
        parentalLock = false;
        p = provider;
        initSession("user","password","1");

    }



    public int initSession(String username, String password, String fID){

        int login;
        setFriendID(fID);
        try {//Check if constructor of Chat Provider throws exception
            login = p.chatProvider(username, password);
        }catch(TimeoutException t){

            return 2;
        }

        if(login == 0){

            return 0;

        } else {

            return 1;
        }
    }



    public int sendMessage(String text) throws Exception {

        int check;


        if(text.isEmpty()){ //Empty
            return 3;
        }

        else if((getLock()) && (text.contains("Fudge") ||text.contains("Yikes") || text.contains("Pudding"))){ //Lock

            return 4;
        }

        else if(text.length() > p.getMaxMessageLength()){ //Too long
            return 2;
        }


        try {
            check = p.sendMessageTo(friendID, text);

        }catch (TimeoutException t){ //If timeout occurs

            return 1;

        }catch (LoginException l){ //Invalid FriendID

            return 5;
        }



        if (check == 0){//Success

            return 0;
        }

        return -1;

    }



    public void onMessageReceived(String text){
       addMessage(text);
    }






    public boolean getLock(){

        return parentalLock;
    }


    public List<ChatMessage> getMessages(){

        return receivedMessages;
    }

    public void addMessage(String text){

        Date date = new Date();
        ChatMessage n = new ChatMessage(date,text);
        receivedMessages.add(n);
    }

    public void setFriendID(String ID){
        friendID = ID;
    }
}
