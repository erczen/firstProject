package viser.position.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import viser.position.dao.PositionDao;
import viser.position.model.Position;

public class SearchPositionService {
	
	private PositionDao positionDao = new PositionDao();
	
	public Position search(int positionNo){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Position position = positionDao.selectByNo(conn, positionNo);
			
			conn.commit();
			
			return position;
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	public Position search(String positionName){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Position position = positionDao.selectByName(conn, positionName);
			
			conn.commit();
			
			return position;
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
}
