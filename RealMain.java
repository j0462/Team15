import java.lang.reflect.Array;
import java.util.*;

public class RealMain {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Student student1 = new Student(1, "김예현"); // [Test] 학생1 더미 데이터
        Student student2 = new Student(2, "신민금"); // [Test] 학생2 더미 데이터
        Student student3 = new Student(3, "황태경"); // [Test] 학생3 더미 데이터
        Student student4 = new Student(4, "이상헌"); // [Test] 학생4 더미 데이터

        ArrayList<Subject> selectedSubjectList1 = new ArrayList<>(); // [Test] 학생1 과목리스트 더미 데이터
        ArrayList<Subject> selectedSubjectList2 = new ArrayList<>(); // [Test] 학생2 과목리스트 더미 데이터
        ArrayList<Subject> selectedSubjectList3 = new ArrayList<>(); // [Test] 학생3 과목리스트 더미 데이터
        ArrayList<Subject> selectedSubjectList4 = new ArrayList<>(); // [Test] 학생4 과목리스트 더미 데이터

        selectedSubjectList1.addAll(List.of(Subject.MYSQL, Subject.JAVA));
        selectedSubjectList2.add(Subject.MONGODB);
        selectedSubjectList3.addAll(List.of(Subject.REDIS, Subject.SPRING_SECURITY, Subject.OBJECT_ORIENTED));
        selectedSubjectList4.add(Subject.JPA);

        student1.SetSubjectList(selectedSubjectList1);
        student2.SetSubjectList(selectedSubjectList2);
        student3.SetSubjectList(selectedSubjectList3);
        student4.SetSubjectList(selectedSubjectList4);

        student1.registerExamScore(0, 0, 80);
        student1.registerExamScore(1, 1, 90);

        students.addAll(List.of(student1, student2, student3, student4));

        sortSubjectList(students); // 학생 순서 id값으로 정렬

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
                case 1 -> createStudent(); // 수강생 등록
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
        int studentID = students.size() + 1;
        Student student = new Student(studentID, studentName);

        students.add(student);

        ArrayList<Subject> list = new ArrayList<>();
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
                    student.SetSubjectList(list);
                    break;
                }
            }

            try {
                int subjectNumber = Integer.parseInt(chooseSubject);
                if (isUnique(list, subjectNumber)) {
                    Subject subject = Subject.findByCode(subjectNumber);
                    System.out.println("subject = " + subject);
                    if (subject.GetisRequired()) {
                        requiredCount++;
                    } else {
                        optionalCount++;
                    }
                    list.add(subject);
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
        for (int i = 0; i < students.size(); i++) {
            System.out.println(" [ " + (i + 1) + ". " + students.get(i).GetStudentName() + " ] ");
        }
        System.out.println("\n수강생 목록 조회 성공!");
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

    private static int getStudentId() {
        int a = -1;
        do {
            for (Student student : students) {
                String info = "[" + student.GetStudentID() + "." + student.GetStudentName() + "]";
                System.out.print(info);
            }
            System.out.print("\n관리할 수강생의 번호를 입력하시오...");
            a = sc.nextInt();
        } while (foundStudent(a) == -1);
        return foundStudent(a); // 학생 고유번호 -> 인덱스
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        int studentId = getStudentId();
        int SubjectInput = 0;
        int ScoreInput;

        int i = -1;
        do {
            System.out.println((studentId + 1) + "번 " + students.get(studentId).GetStudentName() + " 수강생의 점수를 등록할 과목을 선택해주세요.");

            // 수강생이 등록한 과목들 출력해주기
            if (!students.get(studentId).GetSubjectList().isEmpty()) {
                for (Subject subject : students.get(studentId).GetSubjectList()) {
                    String info = "[" + subject.GetSubjectId() + "." + subject.GetSubjectName() + "]";
                    System.out.print(info);
                }
            } else {
                System.out.println("해당 수강생은 등록되어 있는 과목이 없습니다. \n 메인으로 돌아갑니다.");
            }
            i = foundSubject(studentId, (sc.nextInt()));
        } while (i == -1);

        int indexInput = -1;
        do {
            System.out.println((studentId + 1) + "번  " + students.get(studentId).GetStudentName() + " 수강생의 점수를 등록할 과목 " + students.get(studentId).GetSubjectList().get(i) + "의 회차를 선택해주세요.");
            // 회차당 등록 미등록 여부 띄워주기
            String[] strings = students.get(studentId).getExamResultOrUnregistered(i);
            System.out.println(Arrays.toString(strings));
            indexInput = sc.nextInt() - 1;
        } while (indexInput > 10 || indexInput < 0);

        int registScore;
        do {
            System.out.println((studentId + 1) + "번  " + students.get(studentId).GetStudentName()
                    + " 수강생의 점수를 등록할 과목 " + students.get(studentId).GetSubjectList().get(SubjectInput) + "의 " + (indexInput + 1) + " 회차 점수를 입력해주세요.");
            registScore = sc.nextInt();
            if (registScore > 100 || registScore < 0) {
                System.out.println("0~100점 범위를 벗어났습니다.");
            }
        } while (registScore > 100 || registScore < 0);


        // 점수 등록(과목index, 회차index, 점수)
        students.get(studentId).registerExamScore(SubjectInput, indexInput, registScore);
        // 미등록, 등록 재갱신
        String[] strings = students.get(studentId).getExamResultOrUnregistered(i);
        strings = students.get(studentId).getExamResultOrUnregistered(SubjectInput);

        // 점수 등록 후 출력
        System.out.println(Arrays.toString(strings));
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        int studentId = getStudentId(); // 관리할 수강생 고유 번호
        int subjectId = -1;
        do {
            for (Subject subject : students.get(studentId).GetSubjectList()) {
                String info = "[" + subject.GetSubjectId() + "." + subject.GetSubjectName() + "]";
                System.out.print(info);
            }
            System.out.print("\n수정할 과목의 번호를 입력하시오...");
            subjectId = foundSubject(studentId, sc.nextInt());
        } while (subjectId == -1);

        int index;
        do {
            System.out.print(Arrays.toString(students.get(studentId).getExamResultOrUnregistered(subjectId)));
            System.out.print("\n수정할 회차의 번호를 입력하시오...");
            index = sc.nextInt();
            if (index > 10 || index < 1) {
                System.out.println("존재하지 않는 회차입니다.");
            }
        } while (index > 10 || index < 1);

        int score;
        do {
            System.out.print("새로 입력하실 점수를 입력하시오...");
            score = sc.nextInt();
            if (score > 100 || score < 0) {
                System.out.println("0~100점 범위를 벗어났습니다.");
            }
        } while (score > 100 || score < 0);

        students.get(studentId).updateExamScore(subjectId, index - 1, score);
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        int selectedStudentId = getStudentId(); // 관리할 수강생 번호
        // 수강생 과목 선택
        int i = -1; 
        do {
            System.out.print(students.get(selectedStudentId).GetStudentID() + "번 " + students.get(selectedStudentId).GetStudentName() + " 수강생의 과목 회차 등급을 조회할 과목을 선택해 주세요.\n");
            for (Subject subject : students.get(selectedStudentId).GetSubjectList()) {
                String info = "[" + subject.GetSubjectId() + "." + subject.GetSubjectName() + "]";
                System.out.print(info);
            }
            i = foundSubject(selectedStudentId, sc.nextInt());
        } while (i == -1);

        int index = -1;
        do {
            System.out.println(students.get(selectedStudentId).GetStudentID() + "번 " + students.get(selectedStudentId).GetStudentName() + " 수강생의 " + students.get(selectedStudentId).GetSubjectList().get(i) + "과목 회차 등급을 조회할 회차를 선택해주세요.");
            System.out.print(Arrays.toString(students.get(selectedStudentId).getExamResultOrUnregistered(i)));
            System.out.print("\n조회할 회차의 번호를 입력하시오...");
            index = sc.nextInt();
            if (index > 10 || index < 1) {
                System.out.println("존재하지 않는 회차입니다.");
            }
        } while (index > 10 || index < 1);
        System.out.println(students.get(selectedStudentId).GetSubjectScoreList().get(i).getSubjectRank(index - 1));
        System.out.println("등급 조회 성공!");
    }

    //학생 정렬
    private static void sortSubjectList(ArrayList<Student> students) {
        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                return Integer.compare(student1.GetStudentID(), student2.GetStudentID());
            }
        });
    }

    private static int foundStudent(int inputNumber) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.GetStudentID() == inputNumber) {
                return i; //찾은경우 인덱스 반환
            }
        }
        System.out.println("잘못된 번호 입니다.");
        return -1; //없는 존재
    }

    private static int foundSubject(int studentid, int inputNumber) {
        Student student = students.get(studentid);
        ArrayList<Subject> subjectList = student.GetSubjectList();
        for (int i = 0; i < subjectList.size(); i++) {
            Subject subject = subjectList.get(i);
            if (subject.GetSubjectId() == inputNumber) {
                return i; //찾은 경우 인덱스 반환
            }
        }
        System.out.println("잘못된 번호 입니다.");
        return -1; //없는 존재
    }

    private static boolean isUnique(ArrayList<Subject> list, int number) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).GetSubjectId() == number) {
                return false;
            }
        }
        return true;
    }
}

