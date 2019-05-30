package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	public String FindeExcelName() {
		
		Calendar cal = Calendar.getInstance();
		java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("yyyyMMddHH"); 
		
		String fullFileName ="";
		String subFileName = "grd_master_"+formatter.format(new java.util.Date());
		
		String FilePath = "./external_files/TimeTable_excel/";
		File FileList = new File(FilePath);

		System.out.println(subFileName);
		String fileList[] = FileList.list();
		for(int i=0; i<fileList.length; i++){
		  String FileName = fileList[i];

		  if(FileName.contains(subFileName)){
			  fullFileName = FileName;
		  }
		}
		
		System.out.println(fullFileName);
		return fullFileName;
	}
	
	
	
	
	
	public void ReadandStore(String excelName){
		Connection con = null;
		con = ClassDAO.getConn();
		
		String path = "./external_files/TimeTable_excel/"+excelName;//읽을 파일 경로
		
		try {
			File file = new File(path);//파일 경로를 받아옴
			FileInputStream inputStream = new FileInputStream(file);
            XSSFWorkbook xworkbook = new XSSFWorkbook(inputStream); // 2007 이후 버전(xlsx파일)
			
			XSSFSheet curSheet;//현재 sheet
			XSSFCell curCell;//현재 cell
			XSSFRow curRow;//현재 row
			
			int sheetNumber = xworkbook.getNumberOfSheets();//액셀 Sheet의 개수
			
			while(sheetNumber != 0) {
				sheetNumber--;
				
				curSheet = xworkbook.getSheetAt(sheetNumber);//sheetNumber에 해당하는 시트
				int row = curSheet.getPhysicalNumberOfRows();
				
				//0행은 장식임으로 1행부터
				for(int i=1; i<row; i++) {
					ClassDTO dto = new ClassDTO();
					curRow=curSheet.getRow(i);
					
					
					if(String.valueOf(curRow.getCell(0)).equals("") && String.valueOf(curRow.getCell(1)).equals(""))
						continue;
					
					dto.setSb_grade(String.valueOf(curRow.getCell(1)));
					dto.setComplecation_classfication(String.valueOf(curRow.getCell(2)));
					dto.setSb_ID(String.valueOf(curRow.getCell(3)));
					dto.setSb_name(String.valueOf(curRow.getCell(4)));
					dto.setSb_num(String.valueOf(curRow.getCell(5)));
					dto.setSb_credit(Integer.valueOf((int)curRow.getCell(6).getNumericCellValue()));
					dto.setSb_classification(String.valueOf(curRow.getCell(7)));
					dto.setGrading_type(String.valueOf(curRow.getCell(8)));
					dto.setEnglish_teaching(String.valueOf(curRow.getCell(9)));
					dto.setProfessor(String.valueOf(curRow.getCell(10)));
					dto.setSb_time(String.valueOf(curRow.getCell(11)));
					dto.setSb_thour(Integer.valueOf((int)curRow.getCell(13).getNumericCellValue()));
					dto.setSb_tcredit(Integer.valueOf((int)curRow.getCell(14).getNumericCellValue()));
					dto.setSb_phour(Integer.valueOf((int)curRow.getCell(15).getNumericCellValue()));
					dto.setSb_pcredit(Integer.valueOf((int)curRow.getCell(16).getNumericCellValue()));
					dto.setStd_num(Integer.valueOf((int)curRow.getCell(17).getNumericCellValue()));
					ClassDAO.insertClass(con, dto);
				}
				
			}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return;
	}
}
