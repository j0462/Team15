import java.util.ArrayList;

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
    }
    //학생 아이디 반환
    public String GetStudentID(){ return StudentID; }
    //학생 이름 반환
    public String GetStudentName(){ return StudentName; }
    //학생 수강과목 반환
    public ArrayList<Subject> GetSubjectList(){ return subjectList; }
}
