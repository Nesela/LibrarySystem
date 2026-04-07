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

    // 테스트용 코드 프론트랑 연결시에는 스캐너 x
    public static void register() {
        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();
        Book b = new Book(title);
        bookList.add(b);
        System.out.println(title + " 의 책이 등록되었습니다.");
    }

    public static void main(String[] args) {
        register();
        System.out.println("총 권수: " + allBook);
    }

    // 대여
    public void rental() {
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
