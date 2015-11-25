

import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito

import javax.security.auth.login.LoginException
import java.util.concurrent.TimeoutException

class ChatSessionTest{



    private ChatSession s = null;
    private ChatProvider mockProvider = null;

    @Before
    public void before(){

        mockProvider = Mockito.mock(ChatProvider.class);
        s = new ChatSession(mockProvider);

    }

    @After
    public void after(){

        mockProvider = null;
        s = null;
    }

    @Test
    public void test_initSession_Success(){

        //s.initSession("username", "pass", "1");
        //Mockito.verify(mockProvider).chatProvider("username", "pass");
        Mockito.when(mockProvider.chatProvider("username", "pass")).thenReturn(0);
        Assert.assertEquals(0,s.initSession("username","pass","1"));

    }

    @Test
    public void test_initSession_timeout(){

        //s.initSession("Timeout","t","1");
        Mockito.when(mockProvider.chatProvider("Timeout","t")).thenThrow(new TimeoutException());

        //Mockito.doThrow(new TimeoutException()).when(mockProvider.chatProvider("Timeout","t"));
        Assert.assertEquals(2, s.initSession("Timeout","t","1"));
    }


    @Test
    public void test_initSession_invalid(){

        Mockito.when(mockProvider.chatProvider("invalid", "invalid")).thenReturn(1);

        Assert.assertEquals(1,s.initSession("invalid","invalid","1"));

    }

    @Test
    public void test_sendMessage_too_long(){

        Mockito.when(mockProvider.getMaxMessageLength()).thenReturn(2);
        Assert.assertEquals(2,s.sendMessage("This message is too long"));
    }


    @Test
    public void test_sendMessage_too_short(){

        Assert.assertEquals(3,s.sendMessage(""));
    }


    @Test
    public void test_sendMessage_timeout(){

        Mockito.when(mockProvider.sendMessageTo(Mockito.anyString(),Mockito.anyString())).thenThrow(new TimeoutException());
        Mockito.when(mockProvider.getMaxMessageLength()).thenReturn(50);
        Assert.assertEquals(1,s.sendMessage("This should get a timeout"));

    }

    @Test
    public void test_sendMessage_login_fail(){

        Mockito.when(mockProvider.sendMessageTo(Mockito.anyString(),Mockito.anyString())).thenThrow(new LoginException());
        Mockito.when(mockProvider.getMaxMessageLength()).thenReturn(50);
        Assert.assertEquals(5,s.sendMessage("Invalid Login"));

    }



    @Test
    public void test_send_Message_violation_Fudge(){

        ChatSession spy = Mockito.spy(s);
        Mockito.when(spy.getLock()).thenReturn(true);
        Assert.assertEquals(4,spy.sendMessage("This Fudge is a disgrace"));

    }

    @Test
    public void test_send_Message_violation_Yikes(){

        ChatSession spy = Mockito.spy(s);
        Mockito.when(spy.getLock()).thenReturn(true);
        Assert.assertEquals(4,spy.sendMessage("Yikes, what a disgrace"));

    }

    @Test
    public void test_send_Message_violation_Pudding(){

        ChatSession spy = Mockito.spy(s);
        Mockito.when(spy.getLock()).thenReturn(true);
        Assert.assertEquals(4,spy.sendMessage("This Pudding is disgusting"));
    }


    @Test
    public void test_addMessage(){


        ChatSession spy = Mockito.spy(s);
        spy.onMessageReceived("Text here");
        Mockito.verify(spy,Mockito.atLeastOnce()).addMessage("Text here");


    }


}
