public enum Subject{
    // 필수 과목
    Java("001","Java", true),
    Object_oriented("002","객체지향", true),
    Spring("003","Spring", true),
    JPA("004","JPA", true),
    MySQL("005","MySQL", true),

    // 선택 과목
    Design_pattern("006","디자인 패턴", false),
    Spring_Security("007","Spring_Security", false),
    Redis("008","Redis", false),
    MongoDB("009","MongoDB", false);

    private final String subjectId;
    private final String subjectName;
    private final boolean isRequired; // 필수 여부

    // 생성자
    Subject(String subjectId, String subjectName, boolean isRequired) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.isRequired = isRequired;
    }
    // 과목 아이디 반환 메서드
    public String GetSubjectId() {
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
