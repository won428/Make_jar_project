package com.itgroup;

import com.itgroup.bean.Member;
import com.itgroup.dao.MemberDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//메인 클래스 대신 실제 모든 업무를 총 책임지는 매니저 클래스
public class MemberManager {




    private MemberDao dao = null; //실제 데이터 베이스와 연동하는 다오 클래스
    public Member member = new Member();
    public MemberManager(){
        this.dao = new MemberDao();
    }

    public void selectAll() {//모든 회원 정보 조회
        List<Member> members = dao.selectAll();
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
        int cnt = dao.getSize();
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
        List<Member> members = dao.findBygender(gender);
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
        Member someone = dao.getMemberOn(findid);
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
        cnt = dao.deleteData(id);
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
        cnt = dao.insetInfo(id,name,password,gender,birth,marriage,salary,address,manager);
        if(cnt == -1){
            System.out.println("회원가입에 실패하였습니다.");
        }else if(cnt > 0){
            System.out.println(name + "님의 가입을 축하합니다.");
        }else{

        }
    }
}

