package aka.gandy.config;

import static org.bytedeco.javacpp.opencv_imgcodecs.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bytedeco.javacpp.opencv_core.IplImage;

public class GameConfig {
	private static Map<String, IplImage> templateMap = null;
	
	public static Map<String, IplImage> getTemplateMap() {
		if (templateMap == null) {
			loadTemplateMap();
		}
		return templateMap;
	}
	
	private static void loadTemplateMap() {
		File dir = new File("template/");
		System.out.println("file : " +  dir.getAbsolutePath());
		File[] files = dir.listFiles();
		templateMap = new HashMap<>();
		for (File file : files) {
			if (file.isDirectory()) {
				continue;
			}
			templateMap.put(file.getName(), cvLoadImage(file.getPath()));
		}
	}
	
	public static void releaseTemplateMap() {
		if (templateMap != null) {
			for(String key : templateMap.keySet()) {
				IplImage iplImage = templateMap.get(key);
				iplImage.release();
				iplImage = null;
			}
			templateMap = null;
		}
	}
}
