import java.util.ArrayList;
import java.util.Scanner;

public class TGmain {
    private static ArrayList<Student> studentList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    private static int count = 0;
    public static void main(String[] args) {
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }
    private static void displayMainView() throws InterruptedException{
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> {
                    createStudent();    // 수강생 등록
                    flag = false;       // 메인 화면 이동
                }
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();

        // 기능 구현 (필수 과목, 선택 과목)
        String studentID = Integer.toString(count);
        Student student = new Student(studentID, studentName);
        count++;

        studentList.add(student);

        int requiredCount = 0; // 필수 과목 수 카운트
        int optionalCount = 0; // 선택 과목 수 카운트

        System.out.println("등록할 과목을 숫자로 선택해주세요. 'done'를 입력하면 과목 선택이 완료됩니다.");
        System.out.println("필수과목: [ 1.JAVA | 2.OOP | 3.Spring | 4.JPA | 5.MySQL ]");
        System.out.println("선택과목: [ 6.디자인패턴 | 7.Spring Security | 8.Redis | 9.MongoDB]");

        while (true) {
            String chooseSubject = sc.next();
            if (chooseSubject.equals("done")) {
                if (requiredCount < 3 || optionalCount < 2) {
                    System.out.println("최소 3개 이상의 필수 과목, 2개 이상의 선택 과목을 선택합니다.");
                    continue;
                } else {
                    break;
                }
            }

            try {
                int subjectNumber = Integer.parseInt(chooseSubject);
                if (student.isUnique(subjectNumber)) {
                    Subject subject = Subject.findByCode(subjectNumber);
                    System.out.println("subject = " + subject);
                    if (subject.GetisRequired()) {
                        requiredCount++;
                    } else {
                        optionalCount++;
                    }
                    student.addSubject(subject);
                } else {
                    System.out.println("이미 등록한 과목입니다.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("유효한 과목을 선택하세요.");
            }
        }

        System.out.println("수강생 정보:");
        System.out.println("ID: " + student.GetStudentID());
        System.out.println("이름: " + student.GetStudentName());
        System.out.println("수강한 과목: " + student.GetSubjectList());

        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        int index = 0;
        for (Student student : studentList) {
            if (index == 0) {
                System.out.print("[ ");
            }

            System.out.print(student.GetStudentID() + "." + student.GetStudentName());

            if (index != studentList.size() - 1) {
                System.out.print(" | ");
            } else {
                System.out.println(" ]");
            }
            index++;
        }

        if (index == 0) {
            System.out.println("등록되어 있는 수강생이 없습니다.");
        } else {
            System.out.println("\n수강생 목록 조회 성공!");
        }

    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

}

