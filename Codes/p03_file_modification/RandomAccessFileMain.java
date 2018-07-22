package generateCode.p03_file_modification;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileMain {

	private final static String FILEPATH = "C:/Users/Administrator/Desktop";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		modifyFileContent("WsLoanRepayPlan.java", "private", "public");
	}

	/**
	 * 修改文件内容
	 * 
	 * @param fileName
	 * @param oldstr
	 * @param newStr
	 * @return
	 */
	private static boolean modifyFileContent(String fileName, String oldstr, String newStr) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(FILEPATH + "/" + fileName, "rw");
			String line = null;
			long lastPoint = 0; // 记住上一次的偏移量
			while ((line = raf.readLine()) != null) {
				final long ponit = raf.getFilePointer();
				if (line.contains(oldstr)) {
					String str = line.replace(oldstr, newStr);
					raf.seek(lastPoint);
					raf.writeBytes(str);
				}
				lastPoint = ponit;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
