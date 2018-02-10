package aka.gandy.scene;

import java.util.HashMap;
import java.util.Map;

import aka.gandy.config.ModeConfig;

public class SceneFactory {
	public static Scene getMainScene() {
		String[] keyTemplate = {
				"t_main_fy.PNG",
				"t_main_ts1.PNG",
				"t_main_ts2.PNG",
				"t_main_ts3.PNG",
				"t_main_ts4.PNG",
				"t_main_ts5.PNG",
				"t_main_ts6.PNG",
				"t_main_ts7.PNG",
			};
		Map<Integer, String[]> clickMap = new HashMap<>();
		clickMap.put(ModeConfig.MODE_YH_SINGLE, new String[]{
				"t_main_ts1.PNG",
				"t_main_ts2.PNG",
				"t_main_ts3.PNG",
				"t_main_ts4.PNG",
				"t_main_ts5.PNG",
				"t_main_ts6.PNG",
				"t_main_ts7.PNG",});
		
		clickMap.put(ModeConfig.MODE_YH_TEAM, new String[]{
				"t_main_ts1.PNG",
				"t_main_ts2.PNG",
				"t_main_ts3.PNG",
				"t_main_ts4.PNG",
				"t_main_ts5.PNG",
				"t_main_ts6.PNG",
				"t_main_ts7.PNG",});
		
		clickMap.put(ModeConfig.MODE_YH_TEAM_P2, new String[]{
				"t_team_zdjs.PNG",
				"t_team_js.PNG",
		});
		
		clickMap.put(ModeConfig.MODE_ACTIVITY_SF, new String[]{
				"t_main_ts1.PNG",
				"t_main_ts2.PNG",
				"t_main_ts3.PNG",
				"t_main_ts4.PNG",
				"t_main_ts5.PNG",
				"t_main_ts6.PNG",
				"t_main_ts7.PNG",});
		return new SceneImp(keyTemplate, clickMap);
	}
	
	public static Scene getTanSuoScene() {
		String[] keyTemplate = {
				"t_ts_jx.PNG",
				"t_ts_yh.PNG",
				"t_ts_yl.PNG",
				"t_ts_jjtp.PNG",
		};
		
		Map<Integer, String[]> clickMap = new HashMap<>();
		clickMap.put(ModeConfig.MODE_YH_SINGLE, new String[]{
				"t_ts_yh.PNG",	
		});
		
		clickMap.put(ModeConfig.MODE_YH_TEAM, new String[]{
				"t_ts_yh.PNG",	
		});
		
		clickMap.put(ModeConfig.MODE_ACTIVITY_SF, new String[]{
				"t_ts_jx.PNG",	
		});
		return new SceneImp(keyTemplate, clickMap);
	}
	
	public static Scene getYuHunSelectScene() {
		String[] keyTemplate = {
				"t_yh_yhxz.PNG",
		};
		
		Map<Integer, String[]> clickMap = new HashMap<>();
		clickMap.put(ModeConfig.MODE_YH_SINGLE, new String[]{
				"t_yh_bqds.PNG",	
		});
		
		clickMap.put(ModeConfig.MODE_YH_TEAM, new String[]{
				"t_yh_bqds.PNG",	
		});
		
		clickMap.put(ModeConfig.MODE_YH_YYH, new String[]{
				"t_yh_yyh.PNG",
		});
		return new SceneImp(keyTemplate, clickMap);
	}
	
	public static Scene getSnakeScene(){
		String[] keyTemplate = {
				"t_yh_snake.PNG",
		};
		Map<Integer, String[]> clickMap = new HashMap<>();
		clickMap.put(ModeConfig.MODE_YH_TEAM, new String[]{
				"t_team_zd.PNG",
		});
		
		return new SceneImp(keyTemplate, clickMap);
	}
	
	public static Scene getCreateTeamScene(){
		String[] keyTemplate = {
				"t_cjdw.PNG",
				"t_cj.PNG",
		};
		Map<Integer, String[]> clickMap = new HashMap<>();
		clickMap.put(ModeConfig.MODE_YH_TEAM, new String[]{
				"t_cjdw.PNG",
				"t_cj.PNG",
		});
		
		clickMap.put(ModeConfig.MODE_ACTIVITY_SF, new String[]{
				"t_cjdw.PNG",
				"t_cj.PNG",
		});
		
		return new SceneImp(keyTemplate, clickMap);
	}
	
	public static Scene getTeamScene() {
		String[] keyTemplate = {
				"t_team_hy.PNG",
				"t_team_yq.PNG",
				"t_team_yq3.PNG",
				"t_team_kszd.PNG",
		};
		Map<Integer, String[]> clickMap = new HashMap<>();
		clickMap.put(ModeConfig.MODE_YH_TEAM, new String[]{
				"t_team_kszd.PNG",
				"muti t_team_hy.PNG t_team_yq2.PNG",
				"t_team_yq.PNG",
				"t_team_yq3.PNG",
		});
		
		clickMap.put(ModeConfig.MODE_YH_TEAM_P2, new String[]{});
		
		clickMap.put(ModeConfig.MODE_ACTIVITY_SF, new String[]{
				"t_sf.PNG",
				"t_team_kszd.PNG",
				"t_team_ddkszd.PNG",
				});
		return new SceneImp(keyTemplate, clickMap);
	}
	
	public static Scene getYHFightScene() {
		String[] keyTemplate = {
				"t_zb.PNG",
				"t_zb2.PNG",
				"t_zb3.PNG",
				"t_zb4.PNG",
				"t_zb5.PNG",
				"t_zd.PNG",
				"t_sd.PNG",
				"t_jl.PNG",
				"t_team_jxyq.PNG",
				"t_yh_pet.PNG",
				"t_sl.PNG",
				
		};
		Map<Integer, String[]> clickMap = new HashMap<>();
		clickMap.put(ModeConfig.MODE_YH_TEAM, new String[]{
				"t_zb.PNG",
				"t_zb2.PNG",
				"t_zb3.PNG",
				"t_zb4.PNG",
				"t_zb5.PNG",
				"t_jl.PNG",
				"t_team_mr.PNG",
				"t_team_qd.PNG",
				"t_yh_pet.PNG",
				"t_sl.PNG",
		});
		clickMap.put(ModeConfig.MODE_YH_TEAM_P2, new String[]{
				"t_zb.PNG",
				"t_jl.PNG",
				"t_team_mr.PNG",
				"t_team_qd.PNG",
				"t_yh_pet.PNG",
				"t_sl.PNG",
		});
		return new SceneImp(keyTemplate, clickMap);
	}
	
	public static Scene getJXFightScene() {
		String[] keyTemplate = {
				"t_zb.PNG",
				"t_zb2.PNG",
				"t_zb3.PNG",
				"t_zb4.PNG",
				"t_zb5.PNG",
				"t_zd.PNG",
				"t_sd.PNG",
				"t_jl.PNG",
				"t_team_jxyq.PNG",
				"t_yh_pet.PNG",
				"t_sl.PNG",
				
		};
		Map<Integer, String[]> clickMap = new HashMap<>();
		clickMap.put(ModeConfig.MODE_ACTIVITY_SF, new String[]{
				"t_zb.PNG",
				"t_jl.PNG",
				"t_team_mr.PNG",
				"t_team_qd.PNG",
				"t_sl.PNG",
		});
		
		return new SceneImp(keyTemplate, clickMap);
	}
	
	public static Scene getJueXingSelectScene() {
		String[] keyTemplate = {
				"t_jx_jxxz.PNG",
		};
		
		Map<Integer, String[]> clickMap = new HashMap<>();
		clickMap.put(ModeConfig.MODE_ACTIVITY_SF, new String[]{
				"t_jx_lql.PNG",	
		});
		
		return new SceneImp(keyTemplate, clickMap);
	}
	
	public static Scene getLeiQLScene(){
		String[] keyTemplate = {
				"t_jx_lql2.PNG",
		};
		Map<Integer, String[]> clickMap = new HashMap<>();
		clickMap.put(ModeConfig.MODE_ACTIVITY_SF, new String[]{
				"t_team_zd.PNG",
		});
		
		return new SceneImp(keyTemplate, clickMap);
	}
}
