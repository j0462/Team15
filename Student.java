import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Student {
    private int StudentID;
    private String StudentName;
    private String Condition; // GREEN, YELLOW, RED 상태
    private ArrayList<Subject> subjectList; //과목정보만
    private ArrayList<SubjectScore> subjectScoreList; //과목정보 + 점수정보
    //학생 생성자
    public Student(int StudentID, String StudentName) {
        this.StudentID = StudentID;
        this.StudentName = StudentName;
        this.subjectList = new ArrayList<>();
    }

    public void SetStudentName(String studentName) {
        StudentName = studentName;
    }

    public void SetCondition(String Condition) {
        this.Condition = Condition;
    }

    public String GetCondition() {
        return this.Condition;
    }

    //과목 등록
    public void SetSubjectList(ArrayList<Subject> list) {
        this.subjectList.addAll(list);
        sortSubjectList();
        SetSubjectScoreList(subjectList);
    }
    //과목 정렬
    public void sortSubjectList() {
        Collections.sort(subjectList, new Comparator<Subject>() {
            public int compare(Subject subject1, Subject subject2) {
                return Integer.compare(subject1.GetSubjectId(), subject2.GetSubjectId());
            }
        });
    }
    //과목 + 점수 생성
    void SetSubjectScoreList(ArrayList<Subject> list){
        this.subjectScoreList = new ArrayList<>();
        for (Subject subject : list){
            SubjectScore a = new SubjectScore(subject);
            subjectScoreList.add(a);
        }
    }

    public void registerExamScore(int subjectindex, int index, int score) { // 점수 등록 메소드
        if (index >= 0 && index < 10 && subjectScoreList.get(subjectindex).getSubjectScore(index) == -1) { // 이미 점수가 입력된건 못 함
            subjectScoreList.get(subjectindex).setSubjectScore(index, score);
            RegisterExamRank(subjectindex, index, score);
        } else if (index < 0 || index >= 10) {
            System.out.println("유효하지 않은 인덱스입니다.");
        } else {
            System.out.println("이미 점수가 등록된 인덱스입니다.");
        }
    }

    public void updateExamScore(int subjectindex, int index, int newScore) { // 점수 수정 메소드
        if (index >= 0 && index < 10) {
            if (subjectScoreList.get(subjectindex).getSubjectScore(index) != -1) { // 점수가 이미 등록된 것 만 수정 가능
                subjectScoreList.get(subjectindex).setSubjectScore(index, newScore);
                RegisterExamRank(subjectindex, index, newScore);
                System.out.println("점수가 성공적으로 수정되었습니다.");
            } else {
                System.out.println("점수가 등록되지 않았습니다. 수정할 수 없습니다.");
            }
        } else {
            System.out.println("유효하지 않은 인덱스입니다.");
        }
    }

    public String[] getExamResultOrUnregistered(int subjectindex) { //점수 미등록, 등록 알려주는 메소드
        String[] results = new String[10];
        for (int i = 0; i < 10; i++) {
            if (subjectScoreList.get(subjectindex).getSubjectScore(i) == -1) {
                results[i] = (i+1) + ".미등록";
            } else {
                results[i] = (i+1) + String.valueOf("." + subjectScoreList.get(subjectindex).getSubjectScore(i));
            }
        }
        return results;
    }

    void RegisterExamRank(int subjectindex, int index, int score) {
        if(subjectScoreList.get(subjectindex).getSubjectIsrequried()){
            if(score<=100 && score>=95) subjectScoreList.get(subjectindex).setSubjectRank(index,'A');
            else if(score<=94 && score>=90) subjectScoreList.get(subjectindex).setSubjectRank(index,'B');
            else if(score<=89 && score>=80) subjectScoreList.get(subjectindex).setSubjectRank(index,'C');
            else if(score<=79 && score>=70) subjectScoreList.get(subjectindex).setSubjectRank(index,'D');
            else if(score<=69 && score>=60) subjectScoreList.get(subjectindex).setSubjectRank(index,'F');
            else if(score<=59) subjectScoreList.get(subjectindex).setSubjectRank(index,'N');
        }else{
            if(score<=100 && score>=90) subjectScoreList.get(subjectindex).setSubjectRank(index,'A');
            else if(score<=89 && score>=80) subjectScoreList.get(subjectindex).setSubjectRank(index,'B');
            else if(score<=79 && score>=70) subjectScoreList.get(subjectindex).setSubjectRank(index,'C');
            else if(score<=69 && score>=60) subjectScoreList.get(subjectindex).setSubjectRank(index,'D');
            else if(score<=59 && score>=50) subjectScoreList.get(subjectindex).setSubjectRank(index,'F');
            else if(score<=49) subjectScoreList.get(subjectindex).setSubjectRank(index,'N');
        }
    }
    //학생 아이디 반환
    public int GetStudentID(){ return StudentID; }
    //학생 이름 반환
    public String GetStudentName(){ return StudentName; }
    //학생 수강과목 반환
    public ArrayList<Subject> GetSubjectList(){ return subjectList; }

    public ArrayList<SubjectScore> GetSubjectScoreList(){ return subjectScoreList; }
}
