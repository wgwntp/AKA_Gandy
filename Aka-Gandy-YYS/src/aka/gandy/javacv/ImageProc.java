
package aka.gandy.javacv;

import static org.bytedeco.javacpp.opencv_core.CV_32FC1;
import static org.bytedeco.javacpp.opencv_core.CV_8UC1;
import static org.bytedeco.javacpp.opencv_core.cvRect;
import static org.bytedeco.javacpp.opencv_core.cvSetImageROI;
import static org.bytedeco.javacpp.opencv_core.cvarrToMat;
import static org.bytedeco.javacpp.opencv_core.minMaxLoc;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.CV_COMP_INTERSECT;
import static org.bytedeco.javacpp.opencv_imgproc.TM_CCORR_NORMED;
import static org.bytedeco.javacpp.opencv_imgproc.cvCompareHist;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.matchTemplate;
import static org.bytedeco.javacpp.opencv_highgui.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.opencv_core.CvHistogram;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.Size;

import aka.gandy.win32.Win32Point;
import aka.gandy.win32.Window;

/**
 * @author Lie
 *
 */

public class ImageProc {
	public static Win32Point imgMatch(IplImage baseImage, IplImage template) {
		if (template == null) {
			//TODO here need log to notice miss template file
			return null;
		}
		// read in image default colors
		cvSetImageROI(baseImage, cvRect(0, 30, baseImage.width(),baseImage.height() - 60));
		Mat sourceColor = cvarrToMat(baseImage);
		Mat sourceGrey = new Mat(sourceColor.size(), CV_8UC1);
		cvtColor(sourceColor, sourceGrey, COLOR_BGR2GRAY);
		//read template
		Mat templateColor = cvarrToMat(template);
		Mat templateGrey = new Mat(templateColor.size(), CV_8UC1);
		cvtColor(templateColor, templateGrey, COLOR_BGR2GRAY);
		// Size for the result image
		Size size = new Size(sourceGrey.cols() - templateGrey.cols() + 1,
				sourceGrey.rows() - templateGrey.rows() + 1);
		Mat result = new Mat(size, CV_32FC1);
		matchTemplate(sourceGrey, templateGrey, result, TM_CCORR_NORMED);
		DoublePointer minVal = new DoublePointer();
		DoublePointer maxVal = new DoublePointer();
		Point min = new Point();
		Point max = new Point();
		minMaxLoc(result, minVal, maxVal, min, max, null);
		Mat resultMat = sourceColor.apply(new Rect(max.x(), max.y(), templateGrey.cols(), templateGrey.rows()));
		CvHistogram resultHis = JavaCVUtil.getHueHistogram(new IplImage(resultMat)) ;
		CvHistogram tempHis = JavaCVUtil.getHueHistogram(template);
		double point = histMatch(tempHis, resultHis);
		//System.out.println("Template Match point : " + point);
		if (point > 0.75) {
			Win32Point clickPoint = new Win32Point();
			Random random = new Random(System.currentTimeMillis());
			int offsetX = random.nextInt() % (templateGrey.cols() / 3);
			int offsetY = random.nextInt() % (templateGrey.rows() / 3);
			clickPoint.x = ((max.x() + (max.x() + templateGrey.cols())) / 2) + offsetX;
			clickPoint.y = ((max.y() + (max.y() + templateGrey.rows())) / 2) + offsetY + 30;
			return clickPoint;
		}
		sourceColor.release();
		sourceGrey.release();
		templateGrey.release();
		templateColor.release();
		result.release();
		resultHis.release();
		tempHis.release();
		resultMat.release();
		sourceColor = null;
		sourceGrey = null;
		templateColor = null;
		templateGrey = null;
		result = null;
		tempHis = null;
		resultHis = null;
		resultMat = null;
		return null;
	}
	
	public static double histMatch(CvHistogram hist , CvHistogram hist2) {
		if (hist == null || hist2 == null) {
			return 0;
		}
		//CV_COMP_CHISQR
		double matchValue = cvCompareHist(hist, hist2, CV_COMP_INTERSECT);
		return matchValue;
	}
	
	public static IplImage capture(int hwnd) {
		File f = null;
		IplImage baseImage = null;
		try {
			BufferedImage buffImage = Window.getImage(hwnd);
			f = File.createTempFile("snapshoot", ".bmp");
			// Ð´³öÎ»Í¼
			if (buffImage == null) {
				f.delete();
				return null;
			}
			FileOutputStream out = new FileOutputStream(f);
			ImageIO.write(buffImage, "bmp", out);
			baseImage = cvLoadImage(f.getAbsolutePath());
			buffImage = null;
			out.close();
			f.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baseImage;
	}
}
