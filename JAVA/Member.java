package JAVA;

// 일반 회원 클래스 (부모 클래스)
public class Member {
    // 필드
    String memberName; // 회원 이름
    String memberId; // 회원 아이디
    int point; // 포인트 점수

    // 생성자
    public Member(String memberName, String memberId) {
        this.memberName = memberName; // 회원 이름
        this.memberId = memberId; // 회원 아이디
        this.point = 0; // 최소 포인트는 0점
    }

    // 포인트 적립 (구매금액의 1%)
    public void calcPrice(int price) {
        int point = (int) (price * 0.01);
        this.point += point;
        System.out.println(memberName + "님의 포인트 적립: " + point);
    }

    // 회원 정보 출력
    public void showMemberInfo() {
        System.out.println("== 회원 정보 ==");
        System.out.println("회원명: " + memberName);
        System.out.println("아이디: " + this.memberId); // this 생략 가능
        System.out.println("포인트: " + point);
    }
}

// VIP 회원 클래스 (자식 클래스)
class VIPMember extends Member {
    double saleRatio; // 할인율
    String counselor; // 전문 상담원

    // 생성자
    public VIPMember(String memberName, String memberId, String counselor) {
        super(memberName, memberId); // 부모 클래스의 생성자 호출 (가장 상단 위치)
        this.saleRatio = 0.1; // 10% 할인
        this.counselor = counselor; // 전문 상담원
    }

    // 포인트 적립 오버라이딩 (구매금액의 5%)
    @Override
    public void calcPrice(int price) {
        int point = (int) (price * 0.05);
        this.point += point;
        System.out.println(memberName + "님의 포인트 적립: " + point);
    }
    
    // VIP 회원 정보 출력 (오버라이딩)
    @Override
    public void showMemberInfo() {
        // 부모 클래스의 메소드 호출
        super.showMemberInfo();
        System.out.println("할인율: " + (saleRatio * 100) + "%");
        System.out.println("상담원: " + counselor);
    }
}

// 실행을 위한 메인 클래스
class MemberExample {
    public static void main(String[] args) {
        // 일반 회원 생성
        Member member = new Member("홍길동", "hong");
        member.calcPrice(10000); // 100포인트 적립
        member.showMemberInfo();

        System.out.println("\n"); // 줄바꿈

        // VIP 회원 생성
        VIPMember vip = new VIPMember("김철수", "kim", "박상담");
        vip.calcPrice(10000); // 500포인트 적립
        vip.showMemberInfo();
    }
}
