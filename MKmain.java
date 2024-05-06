import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MKmain {
    static Scanner sc = new Scanner(System.in);
    // Student 객체를 저장할 리스트
    //static Student student;
    static ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Student student1 = new Student("1", "가나다");
        Student student2 = new Student("2", "라마바");
        ArrayList<Subject> subjectList1 = new ArrayList<>();
        ArrayList<Subject> subjectList2 = new ArrayList<>();

        subjectList1.addAll(Arrays.stream(Subject.values()).toList());
        subjectList2.addAll(Arrays.stream(Subject.values()).toList());

        student1.SetSubjectList(subjectList1);
        student2.SetSubjectList(subjectList2);

        studentList.add(student1);
        studentList.add(student2);


        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }

    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");  // 구현 할 항목 @@@@@@@@@@@@
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요... ");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리 // 완료
                case 2 -> displayScoreView(); // 점수 관리 // 구현 할 항목 @@@@@@@@@@@@
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
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동... ");
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
        //student = new Student(studentList.getLast().GetStudentID(), studentName);
        //student = new Student("1", studentName);
        //studentList.add(student);

        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(" [ " + (i+1) + ". " + studentList.get(i).GetStudentName() + " ] ");
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    private static void displayScoreView() {// 구현 할 항목@@@@@@@@@@@
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록"); // 구현 할 항목 @@@@@@@@@@@@
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요... ");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록 // 구현 할 항목@@@@@@@@@@@
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

    private static String getStudentId() { // 구현 중 @@@@@@@@@@@@
        // 점수를 등록할 수강생을 선택해 주세요
        if (!studentList.isEmpty()) {
            System.out.print("\n관리할 수강생의 번호를 입력하시오...\n");
            for (int i = 0; i < studentList.size(); i++) {
                System.out.println("[ " + studentList.get(i).GetStudentID() + " : " + studentList.get(i).GetStudentName() + " ]");
            }
        } else {
            System.out.println("등록되어 있는 수강생이 없습니다. \n 메인으로 돌아갑니다.");
        }

        return String.valueOf(sc.nextInt()-1);
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() { // 구현 중 @@@@@@@@@@@@
        // 관리할 수강생 고유 번호
        int studentId = Integer.parseInt(getStudentId());
        int SubjectInput = 0;
        int IndexInput;
        int ScoreInput;

        System.out.println((studentId+1) + "번 " + studentList.get(studentId).GetStudentName()
                + " 수강생의 점수를 등록할 과목을 선택해주세요.");

        // 수강생이 등록한 과목들 출력해주기
        if (!studentList.get(studentId).GetSubjectList().isEmpty()) {
            for (int i = 1; i <= studentList.get(studentId).GetSubjectList().size(); i++) {
                System.out.println("[ " + i + " : " + studentList.get(studentId).GetSubjectList().toString() + " ]");
            }
        } else {
            System.out.println("해당 수강생은 등록되어 있는 과목이 없습니다. \n 메인으로 돌아갑니다.");
        }
        SubjectInput = (sc.nextInt())-1;
        System.out.println((studentId+1) + "번  " + studentList.get(studentId).GetStudentName()
                + " 수강생의 점수를 등록할 과목 "+ studentList.get(studentId).GetSubjectList().get(SubjectInput) +"의 회차를 선택해주세요.");
        // 회차당 등록 미등록 여부 띄워주기
        String[] strings = studentList.get(studentId).getExamResultOrUnregistered(SubjectInput);
        System.out.println(Arrays.toString(strings));

        IndexInput = sc.nextInt()-1;

        System.out.println((studentId+1) + "번  " + studentList.get(studentId).GetStudentName()
                + " 수강생의 점수를 등록할 과목 "+ studentList.get(studentId).GetSubjectList().get(SubjectInput) +"의 "+ (IndexInput+1) +" 회차 점수를 입력해주세요.");

        ScoreInput = sc.nextInt();

        // 점수 등록(과목index, 회차index, 점수)
        studentList.get(studentId).registerExamScore(SubjectInput, IndexInput, ScoreInput);
        // 미등록, 등록 재갱신
        strings = studentList.get(studentId).getExamResultOrUnregistered(SubjectInput);

        // 점수 등록 후 출력
        System.out.println("\n점수 등록 성공!");
        System.out.println(Arrays.toString(strings));
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


