package generateCode.p03_file_modification;

import java.io.File;
import java.io.FileInputStream;

public class ModifyFileNotIgnoringLineSeparator {

	public static String readFile(File path, String filename) {
		String result = null;
		try {
			File file = new File(path, filename);
			if (!file.exists()) {
				return null;
			}
			
			FileInputStream inputStream = new FileInputStream(file);
			byte[] b = new byte[inputStream.available()];
			inputStream.read(b);
			result = new String(b);
			
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
