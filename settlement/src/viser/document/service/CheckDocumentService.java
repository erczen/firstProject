package viser.document.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import viser.document.dao.DocumentDao;
import viser.document.model.Document;

public class CheckDocumentService {
	
	public DocumentDao documentDao = new DocumentDao();
	
	public Integer check(int documentNo){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Document document = documentDao.selectByNo(conn, documentNo);
			document.setOfficerCheck(true);
			documentDao.update(conn, document);
			
			conn.commit();
			
			return documentNo;
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(RuntimeException e){
			JdbcUtil.rollback(conn);
			throw e;
		}finally{
			JdbcUtil.close(conn);
		}
	}
}
