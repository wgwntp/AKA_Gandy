package aka.gandy.scene;

import java.util.Map;

public interface Scene {
	public String[] getKeyTemplate();
	
	public Map<Integer, String[]> getClickMap();
}
