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
    //학생 아이디 반환
    public String GetStudentID(){ return StudentID; }
    //학생 이름 반환
    public String GetStudentName(){ return StudentName; }
    //학생 수강과목 반환
    public ArrayList<Subject> GetSubjectList(){ return subjectList; }
}
