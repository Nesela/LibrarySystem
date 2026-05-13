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

            java.util.Scanner sc =new java.util.Scanner(System.in);
            System.out.print("\n새로 등록할 책 제목을 입력하세요: ");
            String newTitle = sc.nextLine();

            String insertSql = "INSERT INTO book (title) VALUES ('" + newTitle + "')";

            //실행
            stmt.executeUpdate(insertSql);

            System.out.println(" DB에 '" + newTitle + "' 등록 완료!");

            System.out.println("\n 대여할 책의 번호 (id)를 입력하세요: ");
            int rentId = sc.nextInt();

            //SQL 업데이트문 id가 입력받은 번호인 책의 si_rneted를 1(true)로 바꾸기

            String rentSql = "UPDATE book SET is_rented = true WHERE id = " + rentId;
            String returnSql = "UPDATE book SET is_rented = false WHERE id = " + rentId;

            stmt.executeUpdate(rentSql);
            stmt.executeUpdate(returnSql);

            System.out.println(rentId + " 번 도서가 대여 처리되었습니다.");

// 연결 끊기 (전화 끊기)
            conn.close();

        } catch (Exception e) {
            System.out.println("❌ 실패: 연결 중에 에러가 났어요.");
            e.printStackTrace(); // 에러 이유를 자세히 알려줌
        }
    }
}