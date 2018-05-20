package generateCode.generateCode02_bean;

import java.util.HashMap;
import java.util.Map;

public class OracleTypeToJavaType {
	
	public static Map getMapping() {
		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("CHAR", "String");
		mapping.put("VARCHAR2", "String");
		mapping.put("LONG", "String");
		mapping.put("NUMBER", "int");
		mapping.put("DATE", "Date");
		return mapping;
	}
}
