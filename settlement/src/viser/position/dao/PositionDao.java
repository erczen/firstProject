package viser.position.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import viser.position.model.Position;

public class PositionDao {
	
	public List<Position> select(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from position");
			rs = pstmt.executeQuery();
			
			List<Position> positionList = new ArrayList<>();
			while(rs.next()){
				positionList.add(convertPosition(rs));
			}
			return positionList;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Position selectbyName(Connection conn, String positionName) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from position where position_name = ?");
			pstmt.setString(1, positionName);
			rs = pstmt.executeQuery();
			
			Position position = null;
			if(rs.next()){
				position = convertPosition(rs);
			}
			return position;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Position selectbyNo(Connection conn, int positionNo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from position where position_no = ?");
			pstmt.setInt(1, positionNo);
			rs = pstmt.executeQuery();
			
			Position position = null;
			if(rs.next()){
				position = convertPosition(rs);
			}
			return position;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void insert(Connection conn, Position position) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("insert into position(position_name) values(?)");
			pstmt.setString(1, position.getPositionName());
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	public void delete(Connection conn, Position position) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from position where position_no = ?");
			pstmt.setInt(1, position.getPositionNo());
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	} 
	
	private Position convertPosition(ResultSet rs) throws SQLException{
		return new Position(rs.getInt("position_no"), rs.getString("position_name"));
	}
}
