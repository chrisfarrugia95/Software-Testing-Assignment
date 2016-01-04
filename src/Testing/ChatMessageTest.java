package Testing;

import Classes.ChatMessage;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class ChatMessageTest extends TestCase {

    private ChatMessage c;



    @After
    public void after_ChatMessage(){
        c = null;
    }

    @Test
    public void test_ChatMessage_timestamp(){

        Date d = Calendar.getInstance().getTime();
        c = new ChatMessage(d,null);
        Assert.assertEquals("14/11/2015-00:00:01", c.getTimestamp());

    }

    @Test
    public void test_ChatMessage_content(){

        String message = "This is useless";
        c = new ChatMessage(null,message);
        Assert.assertEquals("This is a dummy", c.getContent());

    }



}