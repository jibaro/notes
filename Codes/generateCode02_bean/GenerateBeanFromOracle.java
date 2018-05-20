package generateCode.generateCode02_bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import camelAndUnderline.CamelAndUnderline;

public class GenerateBeanFromOracle {

	String tableName = "USERS";
	
	String generatedFilePath = "C:\\Users\\Administrator\\Desktop\\generatedFile.java";
	
	public void generateCodes() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:test", "tester", "tester");
		// String sql = "select * from users";
		String sql = "SELECT t1.COLUMN_NAME, t1.DATA_TYPE, t2.COMMENTS FROM user_tab_columns t1, user_col_comments t2 WHERE t1.TABLE_NAME = '" + tableName + "' AND t1.TABLE_NAME = t2.TABLE_NAME AND t1.COLUMN_NAME = t2.COLUMN_NAME";
		PreparedStatement stat = con.prepareStatement(sql);
		ResultSet rs = stat.executeQuery();
		
		FileOutputStream fos=new FileOutputStream(new File(generatedFilePath));
		OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter  bw=new BufferedWriter(osw);
		
		StringBuffer getterAndSetter = new StringBuffer();
		
		while (rs.next()) {
			String javaType = (String) OracleTypeToJavaType.getMapping().get(rs.getString(2));
			String camel = CamelAndUnderline.underline2Camel(rs.getString(1));
			String camelDealed = camel.substring(0, 1).toLowerCase() + camel.substring(1); // 使首字母小写
			bw.write("private " + javaType + " " + camelDealed + ";\n");
			bw.write("\n");
			getterAndSetter.append("public void set" + camel + "(" + javaType + " " + camelDealed + ") {\n");
			getterAndSetter.append("\tthis." + camelDealed + " = " + camelDealed + ";\n");
			getterAndSetter.append("}\n");
			getterAndSetter.append("\n");
			getterAndSetter.append("public " + javaType + " get" + camel + "() {\n");
			getterAndSetter.append("\treturn " + camelDealed + ";\n");
			getterAndSetter.append("}\n");
			getterAndSetter.append("\n");
		}
		
		bw.write(getterAndSetter.toString());
		
		bw.close();
        osw.close();
        fos.close();
		
		rs.close();
		stat.close();
		con.close();
		
	}
	
	public static void main(String[] args) throws Exception {
		new GenerateBeanFromOracle().generateCodes();
	}

}
