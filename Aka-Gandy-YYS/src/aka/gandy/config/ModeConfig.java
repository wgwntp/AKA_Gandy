package aka.gandy.config;

import java.util.HashMap;
import java.util.Map;

public class ModeConfig {
	public final static int MODE_YH_SINGLE = 1;
	public final static int MODE_YH_YYH = 2;
	public final static int MODE_YH_TEAM = 3;
	public final static int MODE_TP_JJTP = 4;
	public final static int MODE_TP_LTP = 5;
	public final static int MODE_YL_1 = 6;
	public final static int MODE_YL_2 = 7;
	public final static int MODE_YL_3 = 8;
	public final static int MODE_YL_4 = 9;
	public final static int MODE_YH_TEAM_P2 = 10;
	public final static int MODE_ACTIVITY_SF = 180127;
	
	public static Map<Integer, String[]> modeToSceneMap = null;
	public static Map<String, Integer> modeToName = null;
	static {
		initModeNameMap();
		loadSceneMap();
	}
	
	private static void initModeNameMap() {
		modeToName = new HashMap<>();
		modeToName.put("单刷御魂", MODE_YH_SINGLE);
		modeToName.put("业原火", MODE_YH_YYH);
		modeToName.put("御魂组队队长", MODE_YH_TEAM);
		modeToName.put("结界突破", MODE_TP_JJTP);
		modeToName.put("寮突破", MODE_TP_LTP);
		modeToName.put("御魂组队队员", MODE_YH_TEAM_P2);
		modeToName.put("御灵黑龙", MODE_YL_1);
		modeToName.put("御灵白藏主", MODE_YL_2);
		modeToName.put("御灵黑豹", MODE_YL_3);
		modeToName.put("御灵凤凰", MODE_YL_4);
		modeToName.put("活动-发现山风", MODE_ACTIVITY_SF);
	}
	
	private static void loadSceneMap() {
		modeToSceneMap = new HashMap<>();
		modeToSceneMap.put(MODE_YH_SINGLE, new String[]{
			"MainScene",
			"TanSuoScene",
			"YHSelectScene",
		});
		
		modeToSceneMap.put(MODE_YH_TEAM, new String[]{
			"MainScene",
			"TanSuoScene",
			"YHSelectScene",
			"SnakeScene",
			"CreateTeamScene",
			"TeamScene",
			"YHFightScene",
		});
		
		modeToSceneMap.put(MODE_YH_TEAM_P2, new String[]{
				"MainScene",
				"TeamScene",
				"YHFightScene",
			});
		
		modeToSceneMap.put(MODE_ACTIVITY_SF, new String[]{
				"MainScene",
				"TanSuoScene",
				"JXSelectScene",
				"LeiQLScene",
				"CreateTeamScene",
				"TeamScene",
				"JXFightScene",
			});
	}
}	

