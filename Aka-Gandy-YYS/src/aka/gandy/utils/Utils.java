package aka.gandy.utils;

import aka.gandy.win32.Window;

public class Utils {
	public static void setWindow(int hwnd, boolean isMainWindow) {
		if (isMainWindow) {
			Window.moveWindow(hwnd, 1180, 0, 740, 515);
		} else {
			Window.moveWindow(hwnd, 1180, 515, 740, 515);
		}
	}
}
