package aka.gandy.win32;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Structure;

//矩形结构体
public class Rect extends Structure {

	public int left;
	public int top;
	public int right;
	public int bottom;
	@Override
	protected List<String> getFieldOrder() {
		List<String> fieldList = new ArrayList<>();
		fieldList.add("left");
		fieldList.add("top");
		fieldList.add("right");
		fieldList.add("bottom");
		return fieldList;
	}
}
