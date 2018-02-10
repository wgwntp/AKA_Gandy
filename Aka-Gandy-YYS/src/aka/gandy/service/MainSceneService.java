package aka.gandy.service;

import org.bytedeco.javacpp.opencv_core.IplImage;

import aka.gandy.config.GameConfig;
import aka.gandy.config.ModeConfig;
import aka.gandy.javacv.ImageProc;
import aka.gandy.scene.Scene;
import aka.gandy.scene.SceneFactory;
import aka.gandy.win32.Mouse;
import aka.gandy.win32.Win32Point;

public class MainSceneService implements SceneService {
	
	private Scene mainScene = SceneFactory.getMainScene();

	private String name = "mainScene";
	
	private int mode;
	@Override
	public boolean isMatch(IplImage baseImage) {
		for (String template : mainScene.getKeyTemplate()) {
			Win32Point point = ImageProc.imgMatch(baseImage, GameConfig.getTemplateMap().get(template));
			if (point != null) {
				return true;
			}
		}
		return false;
	}
	
	public void analyze(IplImage baseImage) {
		
	}

	@Override
	public void RandomClick(int hwnd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean click(int hwnd, IplImage baseImage) {
		boolean isClick = false;
		switch(mode) {
		case ModeConfig.MODE_YH_TEAM_P2:
			for(String tempPath : mainScene.getClickMap().get(mode)) {
				Win32Point point = ImageProc.imgMatch(baseImage, GameConfig.getTemplateMap().get(tempPath));
				if (point == null) {
					continue;
				}
				Mouse.click(hwnd, point.x, point.y);
				isClick = true;
				break;
			}
			break;
		default:
			for(String tempPath : mainScene.getClickMap().get(mode)) {
				Win32Point point = ImageProc.imgMatch(baseImage, GameConfig.getTemplateMap().get(tempPath));
				if (point == null) {
					continue;
				}
				Mouse.click(hwnd, point.x, point.y);
				isClick = true;
				break;
			}
			if (!isClick) {
				Win32Point point = getMoveRightPoint(baseImage);
				Mouse.click(hwnd, point.x, point.y);
			}
			break;
		}
		
		return isClick;
	}
	
	/**
	 * When Gandy couldn't find the TanSuo button
	 * Move to right of main scene to find button
	 */
	private Win32Point getMoveRightPoint(IplImage baseImage){
		Win32Point moveRightPoint = new Win32Point();
		moveRightPoint.x = baseImage.width() - 10;
		moveRightPoint.y = (int) (baseImage.height() * 0.3);
		return moveRightPoint;
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
