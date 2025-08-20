package com.itgroup.dao;

import com.itgroup.bean.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao extends Dao{
    public BoardDao(){
        super();
    }

    public Connection getConnection(){
        return super.getConnection();
    }


    public List<Board> selectAllBoard() {
        List<Board> boardList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from boards order by no desc";
        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Board b = this.makeBorad(rs);
                boardList.add(b);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }


        return boardList;
    }

    private void close() {

    }

    private Board makeBorad(ResultSet rs) {
        Board b = null;
        try {
            b = new Board();
            b.setNo(rs.getInt("no"));
            b.setWriter(rs.getString("writer"));
            b.setPassword(rs.getString("password"));
            b.setSubject(rs.getString("subject"));
            b.setContent(rs.getString("content"));
            b.setReadhit(rs.getInt("readhit"));
            b.setRegdate(rs.getString("regdate"));
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return b;
    }

    public List<Board> selectEvenData() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "select * from boards where mod(no,2) = 0 order by no desc";
        ResultSet rs = null;
        List<Board> boards = new ArrayList<>();
        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
            Board b = this.makeBorad(rs);
            boards.add(b);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }



        return boards;
    }
}
