import com.ibm.mq.jms.MQQueueReceiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;

public class MQConnector {
    private Message message;
    private MessageProducer producer;
    private MQQueueReceiver mqReceiver;
    private MessageGetter messageGetter;

    public void StartService() throws JMSException {
        connectAndGet("MQ.IN");
        connectAndPut("MQ.OUT");
    }

    public void connectAndGet(String queueName) throws JMSException {

        CreateConnection connectionIN = new CreateConnection(queueName, false);
            mqReceiver = (MQQueueReceiver) connectionIN.getMqQSession().createReceiver(connectionIN.getMqQueue());
            messageGetter = new MessageGetter();
            mqReceiver.setMessageListener(messageGetter.getListener());
            connectionIN.getMqConn().start();
            connectionIN.getMqConn().stop();
    }

    public void connectAndPut(String queueName) throws JMSException {
        CreateConnection connectionOUT = new CreateConnection(queueName, false);
        producer = connectionOUT.getMqQSession().createProducer(connectionOUT.getMqQueue());
        for (String s : messageGetter.getMsgTextString()) {
            message = connectionOUT.getMqQSession().createTextMessage(s);
            producer.send(message);
            System.out.printf("Sending message\n%s\n",s);
        }
        connectionOUT.getMqConn().start();
        connectionOUT.getMqConn().stop();
    }
}
