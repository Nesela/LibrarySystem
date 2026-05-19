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
            Statement stme = conn.createStatement();

            while (true) {
                System.out.println("(1)북리스트");
                System.out.println("(2)대여하기");
                System.out.println("(3)반납하기");
                System.out.println("(0)종료하기");

                int menu = Integer.parseInt(sc.nextLine());
                if (menu == 1) {
                    bookList(stme);
                } else if (menu == 2) {
                    rental(stme, sc);
                }
                    else if (menu == 3){
                        returnBook(stme, sc);
                } else if (menu == 0) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //모든책 확인
    public static void bookList(Statement stme) {
        try{
            System.out.println(stme);
            ResultSet allBook = stme.executeQuery("SELECT * FROM book");

            while (allBook.next()){
                String title = allBook.getString("title");
                System.out.println("책 제목 : " + title);
            }

        } catch (Exception ble){
            System.out.println("북리스트 오류 발생");
        }
    }

    public static void rental(Statement stme, Scanner sc) {
        System.out.println("대여하실 책의 제목을 입력해주세요");
        String rentalCK = sc.nextLine();
        try {
            int result = stme.executeUpdate("UPDATE book SET  is_rented = 1 WHERE title = '" + rentalCK + "' AND is_rented = 0");

            if(result > 0){
                System.out.println("[" + rentalCK + "] 대여완료");
            } else {
                System.out.println("존재하지않거나 대여중인 책입니다.");
            }
        } catch (Exception renCK){
            System.out.println("대여하기 오류 발생");
        }
    }
    public static void returnBook(Statement stme, Scanner sc) {
        System.out.println("반납하실 책의 제목을 입력해주세요");
        String returnCK = sc.nextLine();
        try {
            int result = stme.executeUpdate("UPDATE book SET  is_rented = 0 WHERE title = '" + returnCK + "' AND is_rented = 1");

            if(result > 0){
                System.out.println("[" + returnCK + "] 반납완료");
            } else {
                System.out.println("빌려 간 기록이 없거나 잘못된 책 이름입니다.");
            }
        } catch (Exception renCK){
            System.out.println("반납하기 오류 발생");
        }
    }
}
