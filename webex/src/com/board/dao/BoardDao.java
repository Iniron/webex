package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.board.dto.BoardDto;

public class BoardDao {
	
	public static final int BOARD_WRITE_SUCCESS = 1;
	public static final int BOARD_WRITE_FAIL = 0;
	public static final int BOARD_HITUP_SUCCESS = 1;
	public static final int BOARD_HITUP_FAIL = 0;
	public static final int BOARD_DELETE_SUCCESS = 1;
	public static final int BOARD_DELETE_FAIL = 0;
	public static final int BOARD_UPDATE_SUCCESS = 1;
	public static final int BOARD_UPDATE_FAIL = 0;
	public static final int BOARD_REPLY_SUCCESS = 1;
	public static final int BOARD_REPLY_FAIL = 0;
	public static final int BOARD_NO_COUNT = 0;
	
	private static BoardDao instance = new BoardDao(); 
	
	//생성자 접근제한(싱글톤 패턴)
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
	
	public int getListCount() {
		// TODO Auto-generated method stub
		int count=BOARD_NO_COUNT;
		Connection connection = null;
		PreparedStatement preparedStatement = null;getClass();
		ResultSet resultSet = null;
		String query = "select count(*) from boardex";
		
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				count = resultSet.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(resultSet!=null) resultSet.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return count;
	}
	
	public ArrayList<BoardDto> getList(int nowpage, int limit) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BoardDto dto = null;
		ArrayList<BoardDto> dtos = null;
		String query = 	"select * "+
						"from (select rownum as rno, bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent "+
						"from (select * from boardex order by bgroup desc, bstep asc)) "+
						"where rno between ? and ?";
		
		int firstlist = (nowpage-1)*limit+1;
		int lastlist = firstlist+limit-1;
		System.out.println("firstlist= "+firstlist);
		System.out.println("lastlist= "+lastlist);
		
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, firstlist);
			preparedStatement.setInt(2, lastlist);
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
	
	public int write(String bname, String btitle, String bcontent) {
		// TODO Auto-generated method stub
		int check=BOARD_WRITE_FAIL;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "insert into boardex values(seq_bid.nextval, ?, ?, ?, default, 0, seq_bid.currval, 0, 0)";
			
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bname);
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
	
	public int update(String bid, String bname, String btitle, String bcontent) {
		// TODO Auto-generated method stub
		int check=BOARD_UPDATE_FAIL;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update boardex set bname=?, btitle=?, bcontent=? where bid=?";
			
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bname);
			preparedStatement.setString(2, btitle);
			preparedStatement.setString(3, bcontent);
			preparedStatement.setString(4, bid);
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
	
	public int reply(String bname, String btitle, String bcontent, String bgroup, String bstep, String bindent) {
		// TODO Auto-generated method stub
		int check=BOARD_REPLY_FAIL;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "insert into boardex values(seq_bid.nextval, ?, ?, ?, default, 0, ?, ?, ?)";
		
		replyStep(bgroup, bstep);
		
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bname);
			preparedStatement.setString(2, btitle);
			preparedStatement.setString(3, bcontent);
			preparedStatement.setString(4, bgroup);
			preparedStatement.setInt(5, Integer.parseInt(bstep)+1);
			preparedStatement.setInt(6, Integer.parseInt(bindent)+1);
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
	
	private void replyStep(String bgroup, String bstep) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update boardex set bstep = bstep + 1 where bgroup = ? and bstep > ?";
		try {
			connection = getConnect();			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(bgroup));
			preparedStatement.setInt(2, Integer.parseInt(bstep));
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
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
	