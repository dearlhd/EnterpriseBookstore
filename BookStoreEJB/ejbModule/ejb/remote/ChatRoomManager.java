package ejb.remote;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ChatRoomManager {
	public void sendMessage (String msg);
	public List<String> getMessages ();
}
