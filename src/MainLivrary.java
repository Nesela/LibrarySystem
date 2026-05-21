import java.sql.*;
import java.util.Scanner;

public class MainLivrary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //mySql 기본뼈대
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "3124";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            while (true) {
                System.out.println("(1)북리스트");
                System.out.println("(2)대여하기");
                System.out.println("(3)반납하기");
                System.out.println("(4)등록하기");
                System.out.println("(5)삭제하기");
                System.out.println("(0)종료하기");

                int menu = Integer.parseInt(sc.nextLine());
                switch (menu) {
                    case 1:
                        bookList(stmt); break;
                    case 2: rental(conn, sc); break;
                    case 3: returnBook(conn, sc); break;
                    case 4: register(conn, sc); break;
                    case 5: deleteBook(conn, sc); break;
                    case 0:
                        System.out.println("프로그램을 종료합니다.");
                        System.exit(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //모든책 확인
    public static void bookList(Statement stmt) {
        try {
            System.out.println(stmt);
            ResultSet allBook = stmt.executeQuery("SELECT * FROM book");

            while (allBook.next()) {
                String title = allBook.getString("title");
                int state = allBook.getInt("is_rented");

                if (state > 0) {
                    System.out.println("책 제목 : " + title + "[대여중]");
                } else {
                    System.out.println("책 제목 : " + title + "[대여 가능]");
                }
            }

        } catch (Exception ble) {
            System.out.println("북리스트 오류 발생");
        }
    }

    //대여
    public static void rental(Connection conn, Scanner sc) {
        System.out.println("대여하실 책의 제목을 입력해주세요");
        String rentalCK = sc.nextLine();
        try {
            //해커 공격위험성 있는 코드 참고용
            //int result = stme.executeUpdate("UPDATE book SET  is_rented = 1 WHERE title = '" + rentalCK + "' AND is_rented = 0");
            String sql = "UPDATE book SET is_rented = 1 WHERE title = ? AND is_rented = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, rentalCK);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("[" + rentalCK + "] 대여완료");
            } else {
                System.out.println("존재하지않거나 대여중인 책입니다.");
            }
        } catch (Exception renCK) {
            System.out.println("대여하기 오류 발생");
        }
    }

    //반납
    public static void returnBook(Connection conn, Scanner sc) {
        System.out.println("반납하실 책의 제목을 입력해주세요");
        String returnCK = sc.nextLine();
        try {
            //해커 공격위험성 있는 코드 참고용
            //int result = stme.executeUpdate("UPDATE book SET  is_rented = 0 WHERE title = '" + returnCK + "' AND is_rented = 1");

            String sql = "UPDATE book SET is_rented = 0 WHERE title = ? AND is_rented = 1";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, returnCK);
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("[" + returnCK + "] 반납완료");
            } else {
                System.out.println("빌려 간 기록이 없거나 잘못된 책 이름입니다.");
            }
        } catch (Exception returCK) {

            System.out.println("반납하기 오류 발생");
        }
    }

    //책 등록하기
    public static void register(Connection conn, Scanner sc) {
        System.out.println("추가하실 책의 제목을 입력해주세요");
        String newBook = sc.nextLine();
        try{
            String regiSql = "INSERT INTO book (title, is_rented) VALUES (?, 0)";
            PreparedStatement pstmt = conn.prepareStatement(regiSql);
            pstmt.setString(1, newBook);
            int result = pstmt.executeUpdate();

            if (result > 0 ){
                System.out.println("[" + newBook + "] 책이 등록 되었습니다.");
            } else {
                System.out.println("책 등록에 실패하였습니다.");
            }

        } catch (Exception regiCk) {

            System.out.println("책 등록 오류 발생");
        }
    }

    //책 삭제
    public static void deleteBook(Connection conn, Scanner sc){
        System.out.println("삭제하실 책의 제목을 입력해주세요");
        String delBook = sc.nextLine();
        try{
            String deleteSql = "DELETE FROM book WHERE title = ?";
            PreparedStatement pstmt = conn.prepareStatement(deleteSql);
            pstmt.setString(1, delBook);
            int result = pstmt.executeUpdate();

            if (result > 0 ){
                System.out.println("[" + delBook + "] 책이 삭제 되었습니다.");
            } else {
                System.out.println("해당 이름의 책을 찾을 수 없습니다..");
            }
        } catch (Exception delCk){
            System.out.println("삭제 오류 발생");
        }
    }
}
