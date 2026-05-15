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

                } else if (menu == 0) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}