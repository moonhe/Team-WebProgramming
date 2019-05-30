/*
 * 2019. 5. 28
 * 
 * author : kyeongjun
 * last modified 2019. 5. 28
 * details : 데이터 베이스 연결  
 * 
 */

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ClassDAO {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/db_TimeTable";
	private static final String USER = "root"; // DB ID
	private static final String PASS = "sjrnfl12"; // DB 패스워드

	public ClassDAO() {
	}

	/** DB연결 메소드 */
	public static Connection getConn() {
		Connection con = null;
		try {
			Class.forName(DRIVER); // 1. 드라이버 로딩
			con = DriverManager.getConnection(URL, USER, PASS); // 2. 드라이버 연결
			System.out.println("connection is success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	public static boolean insertClass(Connection con,ClassDTO dto){
	       
        boolean ok = false;
       
        PreparedStatement ps = null; //명령
       
        try{
            String sql = "insert into tb_class(" +
                        "sb_IDnum,sb_grade,complecation_classfication," +
                        "sb_ID,sb_name,sb_num,sb_credit,sb_classification,"
                        + "grading_type,english_teaching,professor,"
                        + "sb_time,sb_thour,sb_tcredit,sb_phour,sb_pcredit,"
                        + "std_num) "+
                        "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
           
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getSb_ID()+"-"+dto.getSb_num());
            ps.setString(2, dto.getSb_grade());
            ps.setString(3, dto.getComplecation_classfication());
            ps.setString(4, dto.getSb_ID());
            ps.setString(5, dto.getSb_name());
            ps.setString(6, dto.getSb_num());
            ps.setInt(7, dto.getSb_credit());
            ps.setString(8, dto.getSb_classification());
            ps.setString(9, dto.getGrading_type());
            ps.setString(10, dto.getEnglish_teaching());
            ps.setString(11, dto.getProfessor());
            ps.setString(12, dto.getSb_time());
            ps.setInt(13, dto.getSb_thour());
            ps.setInt(14, dto.getSb_tcredit());
            ps.setInt(15, dto.getSb_phour());
            ps.setInt(16, dto.getSb_pcredit());
            ps.setInt(17, dto.getStd_num());
            
            
     
            
            
            int r = ps.executeUpdate(); //실행 -> 저장
           
           
            if(r>0){
                System.out.println("insert success");   
                ok=true;
            }else{
                System.out.println("insert failed");
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }
       
        return ok;
    }//insertMmeber

}
