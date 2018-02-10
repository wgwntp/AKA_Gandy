package aka.gandy.service;

import org.bytedeco.javacpp.opencv_core.IplImage;

import aka.gandy.config.GameConfig;
import aka.gandy.javacv.ImageProc;
import aka.gandy.scene.Scene;
import aka.gandy.scene.SceneFactory;
import aka.gandy.win32.Mouse;
import aka.gandy.win32.Win32Point;

public class LeiQLSceneService implements SceneService{
	private Scene leiQLScene = SceneFactory.getLeiQLScene();
	private String name = "LeiQLScene";
	private int mode;
	@Override
	public boolean isMatch(IplImage baseImage) {
		for (String template : leiQLScene.getKeyTemplate()) {
			Win32Point point = ImageProc.imgMatch(baseImage, GameConfig.getTemplateMap().get(template));
			if (point != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void RandomClick(int hwnd) {
		return;
	}

	@Override
	public boolean click(int hwnd, IplImage baseImage) {
		boolean isClick = false;
		for(String tempPath : leiQLScene.getClickMap().get(mode)) {
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
