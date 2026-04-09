import java.util.Scanner;
import java.util.ArrayList;

public class Book {
    static int allBook;
    static  ArrayList<Book> bookList = new ArrayList<>();
    String bookTitle;
    boolean isBook;

    //현재 책의 총갯수 를 확인가능
    public Book(String title) {

        this.bookTitle = title;
        allBook++;

    }

    public  static  void showList() {
        for (Book b : bookList) {
            System.out.printf(b.bookTitle + " 책은");
            if (b.isBook){
                System.out.println(" 대여 상태입니다.");
            } else {
                System.out.println(" 미대여 상태입니다.");
            }
        }
    }

    // 테스트용 코드 프론트랑 연결시에는 스캐너 x
    public static void register() {
        System.out.println("등록하실 책을 지정해주세요.");
        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();
        Book b = new Book(title);
        bookList.add(b);
        System.out.println(title + " 의 책이 등록되었습니다.");
    }

    public static void main(String[] args) {
        int menu = 0;
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("(1) 책 리스트 확인");
            System.out.println("(2) 대여하기");
            System.out.println("(3) 반납하기");
            System.out.println("(4) 신규 책 등록 하기");
            System.out.println("(0) 메뉴 종료 하기");

            //유저 입력값을 통째로 저장후 숫자로 변환
            String tem = sc.nextLine();
            menu = Integer.parseInt(tem);

            if (menu == 0) {
                break;
            } else if (menu == 1) {
                showList();
                System.out.println("책 리스트를 불러왔습니다.");
            } else if (menu == 2) {
                rental();
            }
        }



//        showList();
    }

    // 대여
    public static void rental() {
        if (this.isBook) {
            System.out.println(this.bookTitle + " 은 현재 대여상태입니다");
            return;
        } else {
            System.out.println(this.bookTitle + " 의 대여가 완료되었습니다.");
            this.isBook = true;
        }
    }

    //반납
    public void returnBook() {
        if (!this.isBook) {
            System.out.println(this.bookTitle + " 은 현재 미대여 상태입니다.");
            return;
        } else {
            System.out.println(this.bookTitle + " 의 반납이 완료되었습니다.");
            this.isBook = false;
        }
    }
}
