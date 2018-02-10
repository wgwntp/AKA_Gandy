package aka.gandy.service;

import java.util.Random;

import org.bytedeco.javacpp.opencv_core.IplImage;

import aka.gandy.config.GameConfig;
import aka.gandy.javacv.ImageProc;
import aka.gandy.scene.Scene;
import aka.gandy.scene.SceneFactory;
import aka.gandy.win32.Mouse;
import aka.gandy.win32.Win32Point;

public class YHFightSceneService implements SceneService{
	private Scene yhFightScene = SceneFactory.getYHFightScene();
	private String name = "YHFightScene";
	private int mode;
	@Override
	public boolean isMatch(IplImage baseImage) {
		for (String template : yhFightScene.getKeyTemplate()) {
			Win32Point point = ImageProc.imgMatch(baseImage, GameConfig.getTemplateMap().get(template));
			if (point != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void RandomClick(int hwnd) {
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < (random.nextInt(2) + 1); i++) {
			int x = random.nextInt(550) + 200;
			int y = random.nextInt(250) + 100;
			System.out.println("x : " + x + " y : " + y);
			Mouse.click(hwnd, x, y);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean click(int hwnd, IplImage baseImage) {
		boolean isClick = false;
		for(String tempPath : yhFightScene.getClickMap().get(mode)) {
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
