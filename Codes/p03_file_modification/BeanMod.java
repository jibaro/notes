package generateCode.p03_file_modification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeanMod {

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException 
	 */
	public String read(String filePath) throws IOException {
		
		BufferedReader br = null;
		
		String line = null;
		StringBuffer buf = new StringBuffer();

		String regex = "(\\s*)(?:private|protect|public)\\s+\\w+\\s+(\\w+);\\s*//\\s*(.*)";
		
		// 根据文件路径创建缓冲输入流
		br = new BufferedReader(new FileReader(filePath));

		// 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
		while ((line = br.readLine()) != null) {
			
			boolean isMatched = false;
			
			Matcher m = Pattern.compile(regex).matcher(line);
			while (m.find()) {
				
				isMatched = true;
				
//				buf.append(m.group(2));
//				buf.append("\t");
//				buf.append(m.group(3).trim());
				
				buf.append(m.group(1) + "@JsonProperty(\"" + m.group(3).trim() + "\")\n");
				buf.append(m.group());
				
			}
			
			if (!isMatched) {
				buf.append(line);
			}
			
			buf.append("\n");
		}
		
		// 关闭流
		if (br != null) {
			br.close();
		}

		return buf.toString();
	}

	/**
	 * 将内容回写到文件中
	 * 
	 * @param filePath
	 * @param content
	 * @throws IOException 
	 */
	public void write(String filePath, String content) throws IOException {
		
		BufferedWriter bw = null;

		// 根据文件路径创建缓冲输出流
		bw = new BufferedWriter(new FileWriter(filePath));
		// 将内容写入文件中
		bw.write(content);
		
		// 关闭流
		if (bw != null) {
			bw.close();
		}
	}

	public static void main(String[] args) throws IOException {
		
		String filePath = "C:/Users/Administrator/Desktop/WsLoanRepayPlan.java"; // 文件路径
		BeanMod beanMod = new BeanMod();
		String filePathDestination = "C:/Users/Administrator/Desktop/WsLoanRepayPlan_result.java"; // 文件路径
		beanMod.write(filePathDestination, beanMod.read(filePath)); // 读取修改文件
		
//		Matcher m = Pattern.compile("(?:private|protect|public)\\s+\\w+\\s+(\\w+);\\s*//\\s*(.*)")
//				.matcher("private String loanBusinessId;		// 借款业务ID  ");
//		while (m.find()) {
//			System.out.println(m.group(1));
//			System.out.println(m.group(2).trim());
//		}

	}
}
