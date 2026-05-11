//public class MainLivrary {
//    public static void main(String[] args) {
//
//    }
//}

import java.sql.Connection;
import java.sql.DriverManager;

public class MainLivrary {
    public static void main(String[] args) {
        // 1. 접속 정보 (본인 MySQL 비번을 꼭 넣어주세요!)
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "3124";

        try {
            // 2. 통역사(JDBC) 불러오기
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 3. 진짜로 DB에 전화 걸기
            Connection conn = DriverManager.getConnection(url, user, password);

            System.out.println("✅ 성공: MySQL과 자바가 연결되었습니다!");

            java.sql.Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM book";
            java.sql.ResultSet rs = stmt.executeQuery(sql);

            System.out.println("--- [도서목록] ---");

            while (rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                boolean isRented = rs.getBoolean("is_rented");
                String rentStatus = isRented ? "대여중" : "대여 가능";

                System.out.println(id + ", " + title + " [" + rentStatus + "]");
            }
// 연결 끊기 (전화 끊기)
            conn.close();

        } catch (Exception e) {
            System.out.println("❌ 실패: 연결 중에 에러가 났어요.");
            e.printStackTrace(); // 에러 이유를 자세히 알려줌
        }
    }
}