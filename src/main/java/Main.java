import javax.jms.JMSException;

public class Main {

    public static void main(String[] args) throws JMSException {
        new MQConnector().StartService();
    }
}
