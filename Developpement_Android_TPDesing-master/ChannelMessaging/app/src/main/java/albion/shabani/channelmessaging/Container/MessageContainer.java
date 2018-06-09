package albion.shabani.channelmessaging.Container;
import java.util.ArrayList;

import albion.shabani.channelmessaging.Clases.Message;

/**
 * Created by shabania on 26/02/2018.
 */
public class MessageContainer {
    public MessageContainer() {
        messages = new ArrayList<Message>();
    }

    public ArrayList<Message> getMessages() {

        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    private ArrayList<Message> messages;
}

