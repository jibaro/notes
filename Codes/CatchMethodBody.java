package generateCode.p04_method_body;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatchMethodBody {

	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\Administrator\\Desktop\\PermissionServiceImpl.java";
		System.out.println(read(path));
	}

	public static String read(String filePath) throws IOException {

		String regex4Method = "\\s*public\\s+Map<String,\\s*Object>\\s+deletePermissionById\\s*\\(\\s*String\\s+ids\\s*\\)\\s*\\{";

		// 根据文件路径创建缓冲输入流
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		StringBuffer buf = new StringBuffer();

		boolean isMethodMatched = false;
		boolean isReady2Find = false;

		int counter = 0;

		// 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
		String line = null;
		while ((line = br.readLine()) != null) {

			if (!isMethodMatched) {
				Matcher m = Pattern.compile(regex4Method).matcher(line);
				while (m.find()) {
					isMethodMatched = true;
					isReady2Find = true;
				}
			}

			if (!isReady2Find) {
				continue;
			}

			buf.append(line);
			buf.append("\n");

			counter += line.length() - line.replace("{", "").length();
			counter -= line.length() - line.replace("}", "").length();
			if (counter == 0) {
				break;
			}
		}

		br.close();

		return buf.toString();
	}
}
