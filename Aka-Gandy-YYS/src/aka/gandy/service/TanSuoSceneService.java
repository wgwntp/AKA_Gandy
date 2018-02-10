package aka.gandy.service;

import org.bytedeco.javacpp.opencv_core.IplImage;

import aka.gandy.config.GameConfig;
import aka.gandy.javacv.ImageProc;
import aka.gandy.javacv.JavaCVUtil;
import aka.gandy.scene.Scene;
import aka.gandy.scene.SceneFactory;
import aka.gandy.win32.Mouse;
import aka.gandy.win32.Win32Point;

public class TanSuoSceneService implements SceneService{
	private Scene tansuoScene = SceneFactory.getTanSuoScene();
	private String name = "TanSuoScene";
	private int mode;
	@Override
	public boolean isMatch(IplImage baseImage) {
		for (String template : tansuoScene.getKeyTemplate()) {
			Win32Point point = ImageProc.imgMatch(baseImage, GameConfig.getTemplateMap().get(template));
			if (point != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void RandomClick(int hwnd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean click(int hwnd, IplImage baseImage) {
		boolean isClick = false;
		for(String tempPath : tansuoScene.getClickMap().get(mode)) {
			Win32Point point = ImageProc.imgMatch(baseImage, GameConfig.getTemplateMap().get(tempPath));
			if (point == null) {
				continue;
			}
			Mouse.click(hwnd, point.x, point.y);
			isClick = true;
			break;
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
