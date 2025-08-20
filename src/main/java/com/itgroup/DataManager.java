package com.itgroup;

import com.itgroup.bean.Board;
import com.itgroup.bean.Member;
import com.itgroup.dao
.BoardDao;
import com.itgroup.dao
.MemberDao;

import java.util.List;
import java.util.Scanner;

//메인 클래스 대신 실제 모든 업무를 총 책임지는 매니저 클래스
public class DataManager {

    Scanner scan = null;


    private MemberDao mdao = null; //실제 데이터 베이스와 연동하는 다오 클래스
    private BoardDao bdao = null;

    public DataManager(){
        this.mdao = new MemberDao();
        this.bdao = new BoardDao();
        this.scan = new Scanner(System.in);
    }

    public void selectAll() {//모든 회원 정보 조회
        List<Member> members = mdao.selectAll();
        System.out.println("이름\t급여\t주소\t");
        for(Member member : members){
            String name = member.getName();
            int salary = member.getSalary();
            String address = member.getAddress();
            String message = name + "\t" + salary + "\t " + address;
            System.out.println(message);
        }
    }

    public void getSize() {//총 회원 수 조회
        int cnt = mdao.getSize();
        String message;
        if (cnt == 0) {
            message = "검색된 회원이 존재하지 않습니다.";
            System.out.println(message);
        } else {
            message =  "검색된 회원은 총 " + cnt + "명입니다.";
            System.out.println(message);
        }
    }

    public void findBygender(String gender) {
        List<Member> members = mdao.findBygender(gender);
        if(members == null){
            System.out.println("찾으시는 성별의 회원이 존재하지 않습니다");
        }else{
            System.out.println("id\tname\tpassword\tgender\tbirth\tmarriage\tsarlary\taddress\tmanager");
        for(Member member : members) {
            String name = member.getName();
            String id = member.getId();
            String password = member.getPassword();
            String member_gender = member.getGender();
            String birth= member.getBirth();
            String marriage = member.getMarriage();
            int salary = member.getSalary();
            String address = member.getAddress();
            String manager = member.getManager();
            String message = id +"\t" + name +"\t" + password+"\t" + member_gender+"\t" + "\t"+ birth +"\t"+ marriage +"\t"+ salary+"\t" + address+"\t" + manager;
            System.out.println(message);
        }
        }
    }

    public void getMemberOne(String findid) {
        Member someone = mdao.getMemberOne(findid);
        if(someone == null){
            System.out.println("찾으시는 회원이 존재하지 않습니다.");
        }else{
            String id = someone.getId();
            String name = someone.getName();
            String gender = someone.getGender();
            String message = id + "\t" + name + "\t" + gender;
            System.out.println(message);
        }
    }

    public void deleteData(String id) {//나의 id를 사용한 탈퇴
        int cnt = -1;
        cnt = mdao.deleteData(id);
        if(cnt == -1){
            System.out.println("회원 탈퇴 실패(접속, 네트워크 오류)");
        }else if(cnt == 0){
            System.out.println(id + " 회원이 존재하지 않습니다");
        }else if(cnt >= 1){
            System.out.println(id + "님의 탈퇴가 완료되었습니다.");
        }else{

        }
    }

    public void insertInfo(String id, String name,String password, String gender, String birth, String marriage, int salary, String address, String manager) {
        int cnt = -1;
        cnt = mdao.insetInfo(id,name,password,gender,birth,marriage,salary,address,manager);
        if(cnt == -1){
            System.out.println("회원가입에 실패하였습니다.");
        }else if(cnt > 0){
            System.out.println(name + "님의 가입을 축하합니다.");
        }else{

        }
    }


    public void insertData() {
        Member m = new Member();
        int cnt = -1;
        System.out.print("ID를 입력해주세요 :");
        String id = scan.nextLine();
        m.setId(id);
        System.out.print("NAME을 입력해주세요 :");
        String name = scan.nextLine();
        m.setName(name);
        System.out.print("PASSWORD를 입력해주세요 :");
        String password = scan.nextLine();
        m.setPassword(password);
        System.out.print("GENDER를 입력해주세요 :");
        String gender = scan.nextLine();
        m.setGender(gender);
        System.out.print("BIRTH를 입력해주세요 :");
        String birth = scan.nextLine();
        m.setBirth(birth);
        System.out.print("MARRIAGE를 입력해주세요 :");
        String marriage = scan.nextLine();
        m.setMarriage(marriage);
        System.out.print("SALARY를 입력해주세요 :");
        int salary = scan.nextInt();
        m.setSalary(salary);
        scan.nextLine();
        System.out.print("ADDRESS를 입력해주세요 :");
        String address = scan.nextLine();
        m.setAddress(address);
        System.out.print("MANAGER를 입력해주세요 :");
        String manager = scan.nextLine();
        m.setManager(manager);

        cnt = mdao.insertInData(m);
        if(cnt == -1){
            System.out.println("회원가입에 실패하였습니다.");
        }else if(cnt > 0){
            System.out.println(id + "님의 가입을 축하합니다.");
        }else{
            System.out.println("알수없는 오류");
        }
    }


    public void updateData() {
        int cnt = -1;
        System.out.print("수정하고자 하는 회원 id 입력 : ");
        String findid = scan.next();
        Member m = mdao.getMemberOne(findid);
        // 여기서 m은 이전에 입력했던 나의 정보입니다.
        this.getMemberOne(findid);
        //편의상 이름과 결혼 여부를 변경해 보겠습니다.
        System.out.print("이름 입력 : ");
        String name = scan.next();
        m.setName(name);



        System.out.print("결혼 여부 입력 : ");
        String marriage = scan.next();
        m.setMarriage(marriage);
        m.setId(findid);

        cnt = mdao.updateData(m);

        if(cnt == -1){
            System.out.println("업데이트 실패");
        }else if(cnt > 0){
            System.out.println("업데이트 성공");
        }else{

        }
    }

    public void selectAllBoard() {
        List<Board> boardList = bdao.selectAllBoard();
        System.out.println("전체 게시물을 출력합니다.");
        System.out.println("no\twriter\tpassword\tsubject\tcontent\treadhit\tregdate");
        for(Board board : boardList){
            int no = board.getNo();
            String writer = board.getWriter();
            String password = board.getPassword();
            String subject = board.getSubject();
            String content = board.getContent();
            int readhit = board.getReadhit();
            String regdate = board.getRegdate();
            String message;
            message = no + "\t" + writer + "\t" + password + "\t" + subject + "\t" + content + "\t" + readhit + "\t" + regdate + "\t";
            System.out.println(message);
        }

    }

    public void selectEvenDate() {
        List<Board> boardList = bdao.selectEvenData();
        System.out.println("글번호가 짝수인 게시물을 출력합니다.");
        System.out.println("no\twriter\tpassword\tsubject\tcontent\treadhit\tregdate");
        for(Board board : boardList){
            int no = board.getNo();
            String writer = board.getWriter();
            String password = board.getPassword();
            String subject = board.getSubject();
            String content = board.getContent();
            int readhit = board.getReadhit();
            String regdate = board.getRegdate();
            String message;
            message = no + "\t" + writer + "\t" + password + "\t" + subject + "\t" + content + "\t" + readhit + "\t" + regdate + "\t";
            System.out.println(message);
        }
    }
}

