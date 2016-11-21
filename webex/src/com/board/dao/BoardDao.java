package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.board.dto.BoardDto;

public class BoardDao {
	
	public static final int BOARD_WRITE_SUCCESS = 1;
	public static final int BOARD_WRITE_FAIL = 0;
	public static final int BOARD_HITUP_SUCCESS = 1;
	public static final int BOARD_HITUP_FAIL = 0;
	public static final int BOARD_DELETE_SUCCESS = 1;
	public static final int BOARD_DELETE_FAIL = 0;
	
	private static BoardDao instance = new BoardDao(); 
	
	//������ ��������(�̱��� ����)
	private BoardDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static BoardDao getInstance() {
		return instance;
	}
	
	private Connection getConnect() {
		// TODO Auto-generated method stub
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			connection = dataSource.getConnection();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return connection;
	}
	
	public ArrayList<BoardDto> getList() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BoardDto dto = null;
		ArrayList<BoardDto> dtos = null;
		String query = "select bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent from boardex order by bid desc";
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			dtos = new ArrayList<BoardDto>();
			while(resultSet.next()){
				dto = new BoardDto();

				dto.setBid(resultSet.getInt("bid"));
				dto.setBname(resultSet.getString("bname"));
				dto.setBtitle(resultSet.getString("btitle"));
				dto.setBcontent(resultSet.getString("bcontent"));
				dto.setBdate(resultSet.getDate("bdate"));
				dto.setBhit(resultSet.getInt("bhit"));
				dto.setBgroup(resultSet.getInt("bgroup"));
				dto.setBstep(resultSet.getInt("bstep"));
				dto.setBindent(resultSet.getInt("bindent"));
				
				dtos.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(resultSet!=null) resultSet.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dtos;
	}

	
	public int write(String bid, String btitle, String bcontent) {
		// TODO Auto-generated method stub
			int check=BOARD_WRITE_FAIL;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String query = "insert into boardex values(seq_bid.nextval, ?, ?, ?, default, 0, seq_bid.currval, 0, 0)";
			
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bid);
			preparedStatement.setString(2, btitle);
			preparedStatement.setString(3, bcontent);
			check = preparedStatement.executeUpdate();	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return check;
	}
	
	public BoardDto contentView(String bid) {
		// TODO Auto-generated method stub
		BoardDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from boardex where bid=?";
		
		try {
			if(hitup(bid)==BOARD_HITUP_SUCCESS){
				connection = getConnect();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, bid);
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()){
					dto = new BoardDto();
					dto.setBid(resultSet.getInt("bid"));
					dto.setBname(resultSet.getString("bname"));
					dto.setBtitle(resultSet.getString("btitle"));
					dto.setBcontent(resultSet.getString("bcontent"));
					dto.setBdate(resultSet.getDate("bdate"));
					dto.setBhit(resultSet.getInt("bhit"));
					dto.setBgroup(resultSet.getInt("bgroup"));
					dto.setBstep(resultSet.getInt("bstep"));
					dto.setBindent(resultSet.getInt("bindent"));
				}			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(resultSet!=null) resultSet.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public int delete(String bid) {
		// TODO Auto-generated method stub
		int check= BOARD_DELETE_FAIL;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "delete from boardex where bid=?";
		
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bid);
			check = preparedStatement.executeUpdate();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return check;
	}
	
	private int hitup(String bid) {
		// TODO Auto-generated method stub
		int check=BOARD_HITUP_FAIL;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update boardex set bhit=bhit+1 where bid=?";
			
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bid);
			check = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return check;
	}
	
}
	