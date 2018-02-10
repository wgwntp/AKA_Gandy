package aka.gandy.control;

import java.util.Random;

import org.bytedeco.javacpp.opencv_core.IplImage;

import aka.gandy.config.GameConfig;
import aka.gandy.dto.Dto;
import aka.gandy.javacv.ImageProc;
import aka.gandy.javacv.JavaCVUtil;
import aka.gandy.service.SceneService;
import aka.gandy.ui.JFrameGandy;
import aka.gandy.win32.Mouse;
import aka.gandy.win32.Win32Point;

public class GandyControl {
	private MainThread mainThread;
	private Dto dto;
	private boolean isMainWindow;

	public  GandyControl(String dtoName, boolean isManWindow) {
		dto = new Dto();
		dto.setName(dtoName);
		this.isMainWindow = isManWindow;
		this.mainThread = new MainThread(dto);
		this.mainThread.start();
		new JFrameGandy(this, dto);
	}
	
	class MainThread extends Thread {
		Dto dto;
		Random random;
		public MainThread(Dto dto) {
			this.dto = dto;
			dto.setSleepTime(1500);
			random = new Random(System.currentTimeMillis());
			
		}
		int randomClickCount = 0;
		@Override
		public void run() {
			while(true) {
				try {
					sleep(dto.getSleepTime() + random.nextInt() % 500);
					if (dto.isPause()) {
						continue;
					}
					if (randomClickCount++ > 5) {
						Random random = new Random(System.currentTimeMillis());
						for (int i = 0; i < 2; i++) {
							int x = random.nextInt(200) + 250;
							int y = random.nextInt(50) + 100;
							Mouse.click(dto.getHwnd(), x, y);
							System.out.println(dto.getName() + "Over Time Random Click !" + "x : " + x + " y : " + y);
						}
						randomClickCount = 0;
						System.out.println(dto.getName() + " match over time !");
					}
					if (dto.getMode() == 0){
						continue;
					}
					IplImage image = ImageProc.capture(dto.getHwnd());
					sleep(100);
					scenePretreatement(image, dto.getHwnd());
					for (SceneService sceneService : dto.getSceneServiceList()) {
						if (!sceneService.isMatch(image)) {
							continue;
						}
						System.out.println(dto.getName() + " Match :" + sceneService.getName());
						sceneService.setMode(dto.getMode());
						sceneService.click(dto.getHwnd(), image);
						if(randomClickCount >= 4) {
							randomClickCount = 0;
							sceneService.RandomClick(dto.getHwnd());
							System.out.println(dto.getName() + "Thread Random Click !");
						}
					}
					JavaCVUtil.release(image);
				} catch (InterruptedException e) {
					continue;
				}
			}
		}
		
		private void scenePretreatement(IplImage image, int hwnd) {
			String[] keyTemp = new String[]{
					"t_xs_js.PNG",
				};
			for(String t : keyTemp) {
				Win32Point point = ImageProc.imgMatch(image, GameConfig.getTemplateMap().get(t));
				if (point == null) {
					return;
				}
				Mouse.click(hwnd, point.x, point.y);
			}
		}
	}

	/**
	 * @return the isMainWindow
	 */
	public boolean isMainWindow() {
		return isMainWindow;
	}

	/**
	 * @param isMainWindow the isMainWindow to set
	 */
	public void setMainWindow(boolean isMainWindow) {
		this.isMainWindow = isMainWindow;
	}
}
