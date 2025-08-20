package com.itgroup.dao;


import com.itgroup.bean.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//데이터 베이스와 직접 연동하여 CRUD 작업을 수행해주는 DAO 클래스
public class MemberDao extends Dao{
    public MemberDao(){
        
        super();
    }
    

    public int getSize() {
        String sql = "select count(*) as cnt from members";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        int cnt = 0; // 검색된 회원 명수
        try {
            conn = super.getConnection();
 // 접속 객체 구하기
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if(rs.next()){
                cnt = rs.getInt("cnt");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if(rs != null){ rs.close(); }
                if(pstmt != null){ pstmt.close(); }
                if(conn != null){ conn.close(); }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return cnt;
    }
    public List<Member> selectAll() {
        List<Member> members = new ArrayList<>();
        PreparedStatement pstme = null;
        ResultSet rs = null;
        Connection conn = null;
        String sql = "SELECT * FROM MEMBERS ORDER BY NAME ASC";
        try {
            conn = super.getConnection();

            pstme = conn.prepareStatement(sql);
            rs = pstme.executeQuery();
            while(rs.next()){
                Member m = new Member();
                m.setId(rs.getString("id"));
                m.setName(rs.getString("name"));
                m.setPassword(rs.getString("password"));
                m.setGender(rs.getString("gender"));
                m.setBirth(rs.getString("birth"));
                m.setMarriage(rs.getString("marriage"));
                m.setSalary(rs.getInt("salary"));
                m.setAddress(rs.getString("address"));
                m.setManager(rs.getString("manager"));

                members.add(m);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
                if(rs != null){rs.close();}
                if(pstme != null){pstme.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return members;
    }

    public List<Member> findBygender(String gender) {
        String sql = "SELECT * FROM MEMBERS WHERE GENDER = ?";
        List<Member> members = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = super.getConnection();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,gender);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Member m = new Member();
                m.setId(rs.getString("id"));
                m.setName(rs.getString("name"));
                m.setPassword(rs.getString("password"));
                m.setGender(rs.getString("gender"));
                m.setBirth(rs.getString("birth"));
                m.setMarriage(rs.getString("marriage"));
                m.setSalary(rs.getInt("salary"));
                m.setAddress(rs.getString("address"));
                m.setManager(rs.getString("manager"));

                members.add(m);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
          try{
              if(rs != null){rs.close();};
              if(pstmt != null){pstmt.close();};
              if(conn != null){conn.close();};
          }catch (Exception ex){
              ex.printStackTrace();
          }
        }
        return members;
    }

    public Member getMemberOne(String id) {
        Member someone = null;
        String sql = "SELECT * FROM MEMBERS WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Member m = null;
        try{
            conn = super.getConnection();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                m = new Member();
                m.setId(rs.getString("id"));
                m.setName(rs.getString("name"));
                m.setPassword(rs.getString("password"));
                m.setGender(rs.getString("gender"));
                m.setBirth(String.valueOf(rs.getDate("birth")));
                m.setMarriage(rs.getString("marriage"));
                m.setSalary(rs.getInt("salary"));
                m.setAddress(rs.getString("address"));
                m.setManager(rs.getString("manager"));

            }else{
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(rs != null){rs.close();};
                if(pstmt != null){pstmt.close();};
                if(conn != null){conn.close();};
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return m;
    }

    public int deleteData(String id) {
        String sql = "DELETE FROM MEMBERS WHERE ID = ?";
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
          conn = super.getConnection();

          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1,id);
          cnt = pstmt.executeUpdate();
          conn.commit();
        }catch (Exception ex){
            try {
                conn.rollback();
            }catch (Exception ex2){
                ex2.printStackTrace();
            }
        }finally {
            try {
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public int insetInfo(String id, String name, String password, String gender, String birth, String marriage, int salary, String address, String manager) {
        String sql = "INSERT INTO MEMBERS VALUES (?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        int cnt = -1;
        try {
            conn = super.getConnection();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,password);
            pstmt.setString(4,gender);
            pstmt.setString(5,birth);
            pstmt.setString(6,marriage);
            pstmt.setInt(7,salary);
            pstmt.setString(8,address);
            pstmt.setString(9,manager);
            cnt = pstmt.executeUpdate();
            conn.commit();
        }catch (Exception ex){
            try {
                conn.rollback();
            }catch (Exception ex2){
                ex2.printStackTrace();
            }
        }finally {
            try {
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public int insertInData(Member m) {
        // 웹 페이지에서 회원정보를 입력하고 '가입'버튼을 눌렀습니다.
        int cnt = -1;
        String sql = "INSERT INTO MEMBERS(id, name, password, gender, birth, marriage, salary, address, manager)";
        sql += " VALUES(?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,m.getId());
            pstmt.setString(2,m.getName());
            pstmt.setString(3,m.getPassword());
            pstmt.setString(4,m.getGender());
            pstmt.setString(5,m.getBirth());
            pstmt.setString(6,m.getMarriage());
            pstmt.setInt(7,m.getSalary());
            pstmt.setString(8,m.getAddress());
            pstmt.setString(9,m.getManager());
            cnt = pstmt.executeUpdate();
            conn.commit();
        }catch (Exception ex){
            try {
                  conn.rollback();
            }catch (Exception ex2){
                ex2.printStackTrace();
            }
        }finally {
            try {
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return cnt ;
    }


    public int updateData(Member m) {
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "update members set name = ?, password = ?, gender = ?, birth =?, marriage = ?, salary = ?, address = ?, manager = ? ";
        sql += " where id = ?";

        try {
            conn = super.getConnection();

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, m.getName());
            pstmt.setString(2, m.getPassword());
            pstmt.setString(3, m.getGender());
            pstmt.setString(4, m.getBirth());
            pstmt.setString(5, m.getMarriage());
            pstmt.setInt(6, m.getSalary());
            pstmt.setString(7, m.getAddress());
            pstmt.setString(8, m.getManager());
            pstmt.setString(9, m.getId());
            cnt = pstmt.executeUpdate();

        }catch (Exception ex){
            try {
                ex.printStackTrace();
                conn.rollback();
            }catch (Exception ex2){
                ex2.printStackTrace();
            }
        }finally {
            try {
                if(pstmt != null){pstmt.close();};
                if(conn != null){conn.close();};
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt;
    }
}

