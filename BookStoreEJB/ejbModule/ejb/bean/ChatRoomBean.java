package ejb.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ejb.remote.ChatRoomManager;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class ChatRoomBean
 */
@Singleton
@LocalBean
public class ChatRoomBean implements ChatRoomManager {

    /**
     * Default constructor. 
     */
    public ChatRoomBean() {
        // TODO Auto-generated constructor stub
    }
    
    Queue<String> chatRecord;
    
    final int SIZE = 100;

    void initChatRecord () {
    	chatRecord = new LinkedList<String>();
    	System.out.println("Chat record initialized.");
    }
    
	@Override
	public void sendMessage(String msg) {
		System.out.println("received "+msg);
		if (chatRecord == null) {
			initChatRecord();
		}
		
		if (chatRecord.size() == SIZE) {
			chatRecord.remove();
		}
		chatRecord.add(msg);
		System.out.println("Chat record size is: " + chatRecord.size());
	}

	@Override
	public List<String> getMessages() {
		System.out.println("client find for chat record");
		if (chatRecord == null) {
			initChatRecord();
			return null;
		}
		else {
			System.out.println("ÓÐ¶«Î÷");
			String[] strs = {};
			strs = chatRecord.toArray(strs);
			List<String> ls = new ArrayList<String>();
			Collections.addAll(ls, strs);
			return ls;
		}
		
	}
}
