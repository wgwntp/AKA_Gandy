package aka.gandy.service;

import java.util.Random;

import org.bytedeco.javacpp.opencv_core.IplImage;

import aka.gandy.config.GameConfig;
import aka.gandy.config.ModeConfig;
import aka.gandy.javacv.ImageProc;
import aka.gandy.scene.Scene;
import aka.gandy.scene.SceneFactory;
import aka.gandy.win32.Mouse;
import aka.gandy.win32.Win32Point;

public class TeamSceneService implements SceneService{
	private Scene teamScene = SceneFactory.getTeamScene();
	private String name = "teamScene";
	private boolean isUseTemplate = true;
	private int count = 0;
	private int mode;
	@Override
	public boolean isMatch(IplImage baseImage) {
		for (String template : teamScene.getKeyTemplate()) {
			Win32Point point = ImageProc.imgMatch(baseImage, GameConfig.getTemplateMap().get(template));
			if (point != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void RandomClick(int hwnd) {
		switch (this.mode) {
		case ModeConfig.MODE_YH_TEAM_P2:
			Random random = new Random(System.currentTimeMillis());
			for (int i = 0; i < (random.nextInt(2) + 1); i++) {
				int x = random.nextInt(380) + 120;
				int y = random.nextInt(200) + 100;
				System.out.println("x : " + x + " y : " + y);
				Mouse.click(hwnd, x, y);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			break;
		default:
			return;
		}
	}

	@Override
	public boolean click(int hwnd, IplImage baseImage) {
		boolean isClick = false;
		switch (mode) {
		case ModeConfig.MODE_YH_TEAM :
			for(String tempPath : teamScene.getClickMap().get(mode)) {
				if (tempPath.indexOf("muti") == 0) {
					String[] paths = tempPath.split("\\s");
					for (int i = 1; i < paths.length; ++i) {
						IplImage template = GameConfig.getTemplateMap().get(paths[i]);
						Win32Point point = ImageProc.imgMatch(baseImage, template);
						if (point == null) {
							continue;
						}
						if (paths[i].equals("t_team_hy.PNG")) {
							point.y = point.y + template.height(); 
							template = null;
						}
						Mouse.click(hwnd, point.x, point.y);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} else if(tempPath.equals("t_team_yq.PNG") || tempPath.equals("t_team_yq3.PNG")){
					if(!isUseTemplate) {
						continue;
					}
					Win32Point point = ImageProc.imgMatch(baseImage, GameConfig.getTemplateMap().get(tempPath));
					if (point == null) {
						continue;
					}
					isUseTemplate = false;
					Mouse.click(hwnd, point.x, point.y);
					isClick = true;
				} else {
					Win32Point point = ImageProc.imgMatch(baseImage, GameConfig.getTemplateMap().get(tempPath));
					if (point == null) {
						continue;
					}
					Mouse.click(hwnd, point.x, point.y);
					isClick = true;
					count = 0;
					System.out.println("count set 0");
					break;
				}
			}
			break;
		case ModeConfig.MODE_ACTIVITY_SF:
			for(String tempPath : teamScene.getClickMap().get(mode)) {
				Win32Point point = ImageProc.imgMatch(baseImage, GameConfig.getTemplateMap().get(tempPath));
				if (point == null) {
					continue;
				}
				if(tempPath.equals("t_team_ddkszd.PNG")) {
					System.out.println("Waiting for start !");
				} else {
					Mouse.click(hwnd, point.x, point.y);
				}
				isClick = true;
			}
			break;
		}
		System.out.println("count : " + count + " isYQ : " + isUseTemplate);
		if (!isClick && !isUseTemplate ) {
			System.out.println("locking : " + count);
			count++;
		}
		
		if (count > 2) {
			count = 0;
			isUseTemplate = true;
			System.out.println("release lock !");
		}
		return isClick;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setMode(int mode) {
		this.mode = mode;
	}
}
