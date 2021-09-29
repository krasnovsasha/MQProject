import com.ibm.mq.jms.*;

import javax.jms.JMSException;
import javax.jms.Session;

public class CreateConnection {
    private MQQueueConnectionFactory mqCF;
    private MQQueueConnection mqConn;
    private MQQueueSession mqQSession;
    private MQQueue mqQueue;
    private String hostName = "localhost";
    private int port = 1414;
    private String queueManager = "ADMIN";
    private String channel = "SYSTEM.ADMIN.SVRCONN";

    public CreateConnection(String queueName,boolean transacted) throws JMSException {
        mqCF = new MQQueueConnectionFactory();
        mqCF.setHostName(hostName);
        mqCF.setPort(port);
        mqCF.setQueueManager(queueManager);
        mqCF.setChannel(channel);

        mqConn = (MQQueueConnection) mqCF.createQueueConnection();
        mqQSession = (MQQueueSession) mqConn.createQueueSession(transacted, Session.AUTO_ACKNOWLEDGE);
        mqQueue = (MQQueue) mqQSession.createQueue(queueName);
    }

    public MQQueueSession getMqQSession() {
        return mqQSession;
    }

    public MQQueue getMqQueue() {
        return mqQueue;
    }

    public MQQueueConnection getMqConn() {
        return mqConn;
    }
}
