package Classes;

import java.util.concurrent.TimeoutException;

public interface ChatProvider {



    public String getName();

    public int sendMessageTo(String friendID,String message) throws Exception;

    public int getMaxMessageLength();

    public void onMessageReceived(String message);

    public int chatProvider(String username, String password) throws TimeoutException;

}
