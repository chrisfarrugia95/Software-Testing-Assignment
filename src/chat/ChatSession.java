package chat;

import javax.security.auth.login.LoginException;
import java.util.*;
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



    public int initSession(String username, String password,String fID){

        int login;
        setFriendID(fID);

        try {//Check if constructor of Chat Provider throws exception
            p.chatProvider(username, password);
        }catch(TimeoutException t){

            return 2;

        }catch(LoginException t){//Invalid Login

            return 1;
        }

        return 0; //Success

    }



    public int sendMessage(String text) throws Exception, LoginException {

        int check;


        if(text.isEmpty()){ //Empty
            return 3;
        }

        else if((getLock()) && (text.contains("Fudge") ||text.contains("Yikes") || text.contains("Pudding"))){ //Check Lock

            return 4;
        }

        else if(text.length() > p.getMaxMessageLength()){ //Too long
            return 2;
        }


        try {
            check = p.sendMessageTo(friendID, text);

        }catch (TimeoutException t){ //If timeout occurs

            return 1;

        }catch (LoginException l){//Invalid ID

            return 5;
        }

        if (check == 0){//Success

            return 0;
        }

        return -1;

    }



    public void onMessageReceived(String text){
       addMessage(text);
    } //Add message to text



    public boolean getLock(){
        return parentalLock;
    }

    public void setLock(boolean b){

        if(b){
            parentalLock = true;
        }
        else parentalLock =false;


    }



    public void addMessage(String text){

        Date date = new Date();
        ChatMessage n = new ChatMessage(date,text);
        receivedMessages.add(n);
    }

    private void setFriendID(String ID){
        friendID = ID;
    }
}
