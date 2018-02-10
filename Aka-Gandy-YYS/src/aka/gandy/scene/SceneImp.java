package aka.gandy.scene;

import java.util.HashMap;
import java.util.Map;

public class SceneImp implements Scene{
	
	private String[] keyTemplate;
	
	private Map<Integer, String[]> clickMap = new HashMap<>();
	
	public SceneImp(String[] keyTemplate, Map<Integer, String[]> clickMap){
		this.keyTemplate = keyTemplate;
		this.clickMap = clickMap;
	}
	
	@Override
	public String[] getKeyTemplate() {
		return this.keyTemplate;
	}

	@Override
	public Map<Integer, String[]> getClickMap() {
		return this.clickMap;
	}
}
