import java.util.Scanner;
import java.util.ArrayList;


public class Book {
    static int allBook;
    static ArrayList<Book> bookList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
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

        //유저 입력 텍스트를 변수(title)에 저장
        String title = sc.nextLine();

        //리스트내에 Book 개체를 확인
        for (Book b : bookList) {
            //b 변수는 리스트에 위치한 책들
            if (b.bookTitle.equals(title)) {
                System.out.println("이미 등록된 책입니다.");
                return;
            }
        }
        //for문 통과시 실행, 새로운책 등록
        Book newBook = new Book(title);

        //리스트에 책 추가 for문에서 쓰이는 리스트
        bookList.add(newBook);
        System.out.println(title + " 의 책이 등록되었습니다.");
    }

    public static void main(String[] args) {
        int menu = 0;


        while (true) {
            System.out.println("(1) 대여하기");
            System.out.println("(2) 반납하기");
            System.out.println("(3) 책 리스트 확인");
            System.out.println("(9) 관리자 메뉴");
            System.out.println("(0) 메뉴 종료 하기");

            //유저 입력값을 통째로 저장후
            String tem = sc.nextLine();


            try {
                // 입력값을 숫자로 변환
                menu = Integer.parseInt(tem);
                if (menu == 1) {
                    rental();
                } else if (menu == 2) {
                    returnBook();
                } else if (menu == 3) {
                    showList();
                } else if (menu == 9) {
                    register();
                } else if (menu == 0) {
                    break;
                }
            } catch (Exception e) {
                // 숫자로 변환되지않는 문자값은 에러로 판단
                System.out.println("숫자만 입력해주세요");
            }
        }
    }
    public static void adminMenu() {

    }

    // 대여
    public static void rental() {


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

    //책 리스트 확인
    public static void showList() {

        if (bookList.isEmpty()) {
            System.out.println("책이 존재하지 않습니다");
            return;
        } else {
            for (Book b : bookList) {
                if (b.isBook) {
                    System.out.println(b.bookTitle + "대여중");
                } else {
                    System.out.println(b.bookTitle + "미대여중");
                }
            }

        }
    }
}