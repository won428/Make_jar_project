package com.itgroup;

import com.itgroup.bean.Member;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MemberManager manager = new MemberManager();
        Member member = new Member();
        String id;
        String name;
        String password;
        String gender;
        String birth;
        String marriage;
        int salary;
        String address;
        String member_manager;

        while(true){
            System.out.println("메뉴 선택");
            System.out.println("0: 종료, 1: 목록 조회, 2: 가입, 3: 수정, 4: 총회원수, 5: 탈퇴, 6: 회원정보. 7: 성별조회, 8: 상세보기");
            int menu = scan.nextInt();
            switch (menu){
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0); // 운영체제에게 종료됨을 알리고 빠져 나가기
                    break;
                case 1:
                    manager.selectAll();
                    break;
                case 2:
                    System.out.print("ID를 입력하세요 : ");
                    id = scan.next();
                    System.out.print("NAME을 입력하세요 : ");
                    name = scan.next();
                    System.out.print("PASSWORD를 입력하세요 : ");
                    password = scan.next();
                    System.out.print("GENDER를 입력하세요 : ");
                    gender = scan.next();
                    System.out.print("BIRTH를 입력하세요 : ");
                    birth = scan.next();
                    System.out.print("MARRIAGE를 입력하세요 : ");
                    marriage = scan.next();
                    System.out.print("SALARY를 입력하세요 : ");
                    salary = scan.nextInt();
                    System.out.print("ADDRESS를 입력하세요 : ");
                    address = scan.next();
                    System.out.print("MANAGER를 입력하세요 : ");
                    member_manager = scan.next();
                    manager.insertInfo(id, name,password, gender,birth, marriage, salary, address, member_manager);
                    break;
                case 3:
                    break;
                case 4:
                    manager.getSize();
                    break;
                case 5:
                    System.out.println("삭제하고자 하는 ID를 입력하세요");
                    id = scan.next();
                    manager.deleteData(id);
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("성별을 입력하세요.");
                    gender = scan.next();
                    manager.findBygender(gender);

                    break;
                case 8:
                    System.out.println("검색할 ID를 입력하세요");
                    String findid = scan.next();
                    manager.getMemberOne(findid);
                    break;

            }
        }
    }
}