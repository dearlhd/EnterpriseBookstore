package websocket;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ejb.bean.ChatRoomBean;
import ejb.remote.ChatRoomManager;

@ServerEndpoint("/chatRoom")
public class ChatRoomEndPoint {
    /* Queue for all open WebSocket sessions */
    static Queue<Session> queue = new ConcurrentLinkedQueue<>();
    
    /* PriceVolumeBean calls this method to send updates */
    public static void send(String msg) {
        try {
            /* Send updates to all open WebSocket sessions */
            for (Session session : queue) {
                session.getBasicRemote().sendText(msg);
            }
        } catch (IOException e) {
        }
    }

    @OnOpen
    public void openConnection(Session session) {
        /* Register this connection in the queue */
        queue.add(session);
        
		try {
			final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);
			final String appName = "";        //������.EAR�������ƣ����������JAR�����Ļ�������������
		    final String moduleName = "BookStoreEJB";        //�������㷢����JAR�ļ�������helloworld.jar,������Ӧ��Ϊhelloworld��ȥ����׺����
		    final String distinctName = "";                  //���û�ж��������ϸ�����ƣ�����������
		    final String beanName = ChatRoomBean.class.getSimpleName();           //����Ϊʵ���������
		    final String viewClassName = ChatRoomManager.class.getName();        //����Ϊ��Ľӿ�����
		    ChatRoomManager crm = (ChatRoomManager) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
		    List<String> msgs = crm.getMessages();
		    if (msgs != null) {
		    	String msg = "";
			    for (int i = 0; i < msgs.size(); i++) {
			    	msg += msgs.get(i) + "\n";
			    }
			    msg += "- - - - - - - - - - - - - - - - - - - - - - - - - - - -\n";
			    try {
					session.getBasicRemote().sendText(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    @OnMessage
    public void onMessage(String msg) {
    	System.out.println(msg);
    	
    	try {
			final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);
			final String appName = "";        //������.EAR�������ƣ����������JAR�����Ļ�������������
		    final String moduleName = "BookStoreEJB";        //�������㷢����JAR�ļ�������helloworld.jar,������Ӧ��Ϊhelloworld��ȥ����׺����
		    final String distinctName = "";                  //���û�ж��������ϸ�����ƣ�����������
		    final String beanName = ChatRoomBean.class.getSimpleName();           //����Ϊʵ���������
		    final String viewClassName = ChatRoomManager.class.getName();        //����Ϊ��Ľӿ�����
		    ChatRoomManager crm = (ChatRoomManager) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
		    crm.sendMessage(msg);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        send(msg);
    }
    
    @OnClose
    public void closedConnection(Session session) {
        /* Remove this connection from the queue */
        queue.remove(session);
    }
    
    @OnError
    public void error(Session session, Throwable t) {
        /* Remove this connection from the queue */
        queue.remove(session);
    }
}
