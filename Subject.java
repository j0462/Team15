public enum Subject{
    // 필수 과목
    Java(1,"Java", true),
    Object_oriented(2,"객체지향", true),
    Spring(3,"Spring", true),
    JPA(4,"JPA", true),
    MySQL(5,"MySQL", true),

    // 선택 과목
    Design_pattern(6,"디자인 패턴", false),
    Spring_Security(7,"Spring_Security", false),
    Redis(8,"Redis", false),
    MongoDB(9,"MongoDB", false);

    private final int subjectId;
    private final String subjectName;
    private final boolean isRequired; // 필수 여부

    // 생성자
    Subject(int subjectId, String subjectName, boolean isRequired) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.isRequired = isRequired;
    }
    //과목 ID로 과목 찾기
    public static Subject findByCode(int subjectId) {
        Subject[] subjects = values();
        for (Subject subject : subjects) {
            if (subject.GetSubjectId() == subjectId) {
                return subject;
            }
        }
        return null;
    }

    // 과목 아이디 반환 메서드
    public int GetSubjectId() {
        return subjectId;
    }
    // 과목 이름 반환 메서드
    public String GetSubjectName() {
        return subjectName;
    }
    // 필수 여부 반환 메서드
    public boolean GetisRequired() {
        return isRequired;
    }
}
//사용 방법 Subject."enum명"."메소드명";
