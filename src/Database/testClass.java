package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import WebCrawling.WebCrwaler;


public class testClass {

	public static void main(String[] args) throws InterruptedException {
//		WebCrwaler.Crwaling();
//		Connection con = null; // 연결
//		PreparedStatement ps = null; // 명령
//		ResultSet rs = null; // 결과
//		ClassDTO dto = new ClassDTO();
		
		ReadExcel read = new ReadExcel();
		read.ReadandStore(read.FindeExcelName());
		System.out.println();
		
		
	}
}
