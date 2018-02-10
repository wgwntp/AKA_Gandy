package aka.gandy.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aka.gandy.config.ModeConfig;
import aka.gandy.service.SceneService;
import aka.gandy.service.ServiceManager;

public class Dto {
	private int hwnd = -1;
	
	private int sleepTime;
	
	private String name;
	
	private boolean isPause = true;
	
	int mode = 0;
	
	List<SceneService> sceneServiceList;
	
	Map<String, SceneService> sceneServiceMap = new HashMap<>();
	
	public Dto() {
		ServiceManager.initServices(sceneServiceMap);
	}
	
	public void setHwnd(int hwnd) {
		this.hwnd = hwnd;
	}

	/**
	 * @return the hwnd
	 */
	public int getHwnd() {
		return hwnd;
	}

	/**
	 * @return the sleepTime
	 */
	public int getSleepTime() {
		return sleepTime;
	}

	/**
	 * @param sleepTime the sleepTime to set
	 */
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the isPause
	 */
	public boolean isPause() {
		return isPause;
	}

	/**
	 * @param isPause the isPause to set
	 */
	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	/**
	 * @return the mode
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(int mode) {
		this.mode = mode;
		sceneServiceList = new ArrayList<>();
		for(String key : ModeConfig.modeToSceneMap.get(mode)) {
			sceneServiceList.add(sceneServiceMap.get(key));
		}
	}

	/**
	 * @return the sceneServiceList
	 */
	public List<SceneService> getSceneServiceList() {
		return sceneServiceList;
	}

	/**
	 * @param sceneServiceList the sceneServiceList to set
	 */
	public void setSceneServiceList(List<SceneService> sceneServiceList) {
		this.sceneServiceList = sceneServiceList;
	}
	
}
