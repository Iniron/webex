package com.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.dto.MemberDto;

public class MemberDao {
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_LOGIN_OK = 1;
	public static final int MEMBER_LOGIN_NO_ID = 0;
	public static final int MEMBER_LOGIN_NOT_PASSWORD = -1;
	public static final int MEMBER_UPDATE_SUCCESS = 1;
	public static final int MEMBER_UPDATE_FAIL = 0;	
	public static final int MEMBER_DELETE_SUCCESS = 1;
	public static final int MEMBER_DELETE_FAIL = 0;
	
	public static MemberDao instance = new MemberDao();

	//생성자 접근제한(싱클톤 패턴)
	private MemberDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static MemberDao Getinstance(){
		return instance;
	}
	
	
	//데이터베이스 연결
	private Connection getConnect() {
		// TODO Auto-generated method stub
		Connection connection = null;
		Context context = null;
		DataSource dataSource = null;
		
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
	
	//회원정봉 입력
	public int insertMember(MemberDto dto) {
		// TODO Auto-generated method stub
		int check = MEMBER_JOIN_FAIL;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "insert into loginex3 values(?,?,?,?,?,?,?,default)";
		
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, dto.getId());
			preparedStatement.setString(2, dto.getPw());
			preparedStatement.setString(3, dto.getName());
			preparedStatement.setString(4, dto.getEmail());
			preparedStatement.setString(5, dto.getPhone());
			preparedStatement.setString(6, dto.getGender());
			preparedStatement.setString(7, dto.getBirth());
			
			preparedStatement.executeUpdate();
			check = MEMBER_JOIN_SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
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
	
	
	//회원 정보 확인
	public int userCheck(String id, String pw) {
		// TODO Auto-generated method stub
			int check = MEMBER_LOGIN_NO_ID;
			String dbPw;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			String query = "select password from loginex3 where id=?";
			
			try {
				connection = getConnect();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, id);
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()){
					dbPw = resultSet.getString("password");
					if(dbPw.equals(pw)){
						check = MEMBER_LOGIN_OK;			//로그인 성공
					} else{
						check = MEMBER_LOGIN_NOT_PASSWORD;	//패스워드 불일치
					}
				} else{
					check = MEMBER_LOGIN_NO_ID;				//존재하지 않는 회원
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
			return check;
	}
	
	public MemberDto getMember(String id) {
		// TODO Auto-generated method stub
		MemberDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from loginex3 where id=?";
		
		
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				dto = new MemberDto();
				dto.setId(resultSet.getString("id"));
				dto.setPw(resultSet.getString("password"));
				dto.setName(resultSet.getString("name"));
				dto.setEmail(resultSet.getString("email"));
				dto.setPhone(resultSet.getString("phone"));
				dto.setGender(resultSet.getString("gender"));
				dto.setBirth(resultSet.getString("birth"));
			}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public int updateMember(MemberDto dto) {
		// TODO Auto-generated method stub
		int check = MEMBER_UPDATE_FAIL;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update loginex3 set id=?, password=?, name=?, email=?, phone=?, gender=?, birth=?";
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query); 
			preparedStatement.setString(1, dto.getId());
			preparedStatement.setString(2, dto.getPw());
			preparedStatement.setString(3, dto.getName());
			preparedStatement.setString(4, dto.getEmail());
			preparedStatement.setString(5, dto.getPhone());
			preparedStatement.setString(6, dto.getGender());
			preparedStatement.setString(7, dto.getBirth());
			
			preparedStatement.executeUpdate();
			check = MEMBER_UPDATE_SUCCESS;
			
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
	
	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		int check=MEMBER_DELETE_FAIL;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "delete from loginex3 where id=?";
		
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			
			check = preparedStatement.executeUpdate();
			check = MEMBER_DELETE_SUCCESS;
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

