package aka.gandy.service;

import java.util.Map;

public class ServiceManager {
	public static void initServices(Map<String, SceneService> sceneServiceMap) {
		sceneServiceMap.put("MainScene", new MainSceneService());
		sceneServiceMap.put("TanSuoScene", new TanSuoSceneService());
		sceneServiceMap.put("YHSelectScene", new YHSelectSceneService());
		sceneServiceMap.put("SnakeScene", new SnakeSceneService());
		sceneServiceMap.put("CreateTeamScene", new CreateTeamSceneService());
		sceneServiceMap.put("TeamScene", new TeamSceneService());
		sceneServiceMap.put("YHFightScene", new YHFightSceneService());
		sceneServiceMap.put("JXSelectScene", new JXSelectSceneService());
		sceneServiceMap.put("LeiQLScene", new LeiQLSceneService());
		sceneServiceMap.put("JXFightScene", new JXFightSceneService());
	}
}
