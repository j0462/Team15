import java.util.Arrays;

public class SubjectScore {
    private int[] scores;
    private Subject subject;
    private char[] Rank;
    public SubjectScore(Subject subject) {
        this.subject = subject;
        scores = new int[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        Rank = new char[]{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'};
    }
    public int getSubjectId(){ // 과목 아이디 호출
        return subject.GetSubjectId();
    }
    public String getSubjectName(){ // 과목 아이디 호출
        return subject.GetSubjectName();
    }
    public boolean getSubjectIsrequried(){ // 필수 여부
        return subject.GetisRequired();
    }

    public int getSubjectScore(int index){
        return scores[index];
    }
    public char getSubjectRank(int index){
        return Rank[index];
    }
    public void setSubjectScore(int index, int score){
        scores[index] = score;
    }
    public void setSubjectRank(int index, char rank){
        Rank[index] = rank;
    }
}
