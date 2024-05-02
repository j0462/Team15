import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Student {
    private String StudentID;
    private String StudentName;
    private ArrayList<Subject> subjectList;
    //학생 생성자
    public Student(String StudentID, String StudentName) {
        this.StudentID = StudentID;
        this.StudentName = StudentName;
        this.subjectList = new ArrayList<>();
    }
    //과목 등록
    public void SetSubjectList(ArrayList<Subject> list) {
        this.subjectList.addAll(list);
        sortSubjectList();
    }
    public void sortSubjectList() {
        Collections.sort(subjectList, new Comparator<Subject>() {
            public int compare(Subject subject1, Subject subject2) {
                return Integer.compare(subject1.GetSubjectId(), subject2.GetSubjectId());
            }
        });
    }

    public void registerExamScore(int subjectindex, int index, int score) { // 점수 등록 메소드
        if (index >= 0 && index < 10 && subjectList.get(subjectindex).examResults[index] == 0) { // 이미 점수가 입력된건 못 함
            subjectList.get(subjectindex).examResults[index] = score;
        } else if (index < 0 || index >= 10) {
            System.out.println("유효하지 않은 인덱스입니다.");
        } else {
            System.out.println("이미 점수가 등록된 인덱스입니다.");
        }
    }

    public void updateExamScore(int subjectindex, int index, int newScore) { // 점수 수정 메소드
        if (index >= 0 && index < 10) {
            if (subjectList.get(subjectindex).examResults[index] != 0) { // 점수가 이미 등록된 것 만 수정 가능
                subjectList.get(subjectindex).examResults[index] = newScore;
                System.out.println("점수가 성공적으로 수정되었습니다.");
            } else {
                System.out.println("점수가 등록되지 않았습니다. 수정할 수 없습니다.");
            }
        } else {
            System.out.println("유효하지 않은 인덱스입니다.");
        }
    }

    public String[] getExamResultOrUnregistered(int subjectindex) { //점수 미등록, 등록 알려주는 메소드
        String[] results = new String[subjectList.get(subjectindex).examResults.length];
        for (int i = 0; i < subjectList.get(subjectindex).examResults.length; i++) {
            if (subjectList.get(subjectindex).examResults[i] == 0) {
                results[i] = "미등록";
            } else {
                results[i] = String.valueOf(subjectList.get(subjectindex).examResults[i]);
            }
        }
        return results;
    }
    //학생 아이디 반환
    public String GetStudentID(){ return StudentID; }
    //학생 이름 반환
    public String GetStudentName(){ return StudentName; }
    //학생 수강과목 반환
    public ArrayList<Subject> GetSubjectList(){ return subjectList; }
}
