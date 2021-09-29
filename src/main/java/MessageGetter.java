import javax.jms.*;
import java.util.LinkedList;

public class MessageGetter {
    private MessageListener listener;
    private LinkedList<String> tMsgTextString = new LinkedList<>();

    public MessageGetter() {
        this.listener = msg -> {
            if (msg instanceof TextMessage) {
                try {
                    TextMessage tMsg = (TextMessage) msg;
                    System.out.println("Received new message \n" + tMsg.getText());
                    tMsgTextString.add(tMsg.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public MessageListener getListener() {
        return listener;
    }

    public LinkedList<String> getMsgTextString() {
        return tMsgTextString;
    }
}
