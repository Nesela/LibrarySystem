import java.util.Scanner;
import java.util.ArrayList;

public class Book {
    static int allBook;
    static ArrayList<Book> bookList = new ArrayList<>();
    String bookTitle;
    boolean isBook;

    //현재 책의 총갯수 를 확인가능
    public Book(String title) {

        this.bookTitle = title;
        allBook++;

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

        while (true) {
            System.out.println("(1) 신규 책 등록 하기");
            System.out.println("(2) 대여하기");
            System.out.println("(3) 반납하기");
            System.out.println("(0) 메뉴 종료 하기");

            //유저 입력값을 통째로 저장후 숫자로 변환
            String tem = sc.nextLine();
            menu = Integer.parseInt(tem);

            if (menu == 0) {
                break;
            } else if (menu == 1) {
                register();
            } else if (menu == 2) {
                rental();
            } else if (menu == 3) {
                returnBook();
            }
        }

    }

    // 대여
    public static void rental() {
        Scanner sc = new Scanner(System.in);

        System.out.println("대여하실 책의 제목을 입력해주세요");
        String usRental = sc.nextLine();

        for (Book b : bookList) {
            if (b.bookTitle.equals(usRental)) {
                if (b.isBook) {
                    System.out.println(b.bookTitle + " 은 현재 대여상태입니다");
                    return;
                } else {
                    b.isBook = true;
                    System.out.println(b.bookTitle + " 의 대여가 완료되었습니다.");
                    return;
                }

            }
        }
        System.out.println("찾으시는 '" + usRental + "' 책은 등록되어 있지 않습니다.");
    }

    //반납
    public static void returnBook() {
        Scanner sc = new Scanner(System.in);

        System.out.println("반납하실 책의 제목을 입력해주세요");
        String usRental = sc.nextLine();

        for (Book b : bookList) {
            if (b.bookTitle.equals(usRental)) {
                if (!b.isBook) {
                    System.out.println(b.bookTitle + " 은 현재 미대여 상태입니다.");
                    return;
                } else {
                    b.isBook = false;
                    System.out.println(b.bookTitle + " 의 반납이 완료되었습니다.");
                    return;
                }

            }
        }
        System.out.println("찾으시는 '" + usRental + "' 책은 등록되어 있지 않습니다.");

    }
}
