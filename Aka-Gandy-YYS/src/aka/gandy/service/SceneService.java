package aka.gandy.service;
import org.bytedeco.javacpp.opencv_core.IplImage;
public interface SceneService {
	public String getName();

	public boolean isMatch(IplImage baseImage);
	
	public void setMode(int mode);
	
	public void RandomClick(int hwnd);
	
	public boolean click(int hwnd, IplImage baseImage);
	
	public void exit();
}
