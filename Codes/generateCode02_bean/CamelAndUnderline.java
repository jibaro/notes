package generateCode.generateCode01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelAndUnderline {

	public static String underline2Camel(String underlineString) {
		StringBuffer camelString = new StringBuffer();
		String strArray[] = underlineString.split("_");
		for (String str : strArray) {
			str = str.toLowerCase();
			Matcher m = Pattern.compile("(.?)(.*)").matcher(str);
			while (m.find()) {
				camelString.append(m.group(1).toUpperCase() + m.group(2));
			}
		}
		return camelString.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(underline2Camel("AAAAAAAAAA_BBBBBBBBB_CCCCCCCC_1_CCCC"));
	}

}
