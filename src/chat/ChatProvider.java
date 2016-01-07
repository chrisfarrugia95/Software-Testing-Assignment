package chat;

import javax.security.auth.login.LoginException;
import java.util.concurrent.TimeoutException;

public interface ChatProvider {



    public String getName();

    public int sendMessageTo(String friendID,String message) throws Exception;

    public int getMaxMessageLength();

    public int onMessageReceived(String message) throws InterruptedException;

    public int chatProvider(String username, String password) throws TimeoutException, LoginException;

}
