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
		modeToName.put("��ˢ����", MODE_YH_SINGLE);
		modeToName.put("ҵԭ��", MODE_YH_YYH);
		modeToName.put("������Ӷӳ�", MODE_YH_TEAM);
		modeToName.put("���ͻ��", MODE_TP_JJTP);
		modeToName.put("�ͻ��", MODE_TP_LTP);
		modeToName.put("������Ӷ�Ա", MODE_YH_TEAM_P2);
		modeToName.put("�������", MODE_YL_1);
		modeToName.put("����ײ���", MODE_YL_2);
		modeToName.put("����ڱ�", MODE_YL_3);
		modeToName.put("������", MODE_YL_4);
		modeToName.put("�-����ɽ��", MODE_ACTIVITY_SF);
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

