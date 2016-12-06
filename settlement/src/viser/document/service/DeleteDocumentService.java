package viser.document.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import viser.document.dao.DocumentDao;
import viser.document.model.Document;

public class DeleteDocumentService {
	
	private DocumentDao documentDao = new DocumentDao();
	
	public void delete(int documentNo){
		try(Connection conn = ConnectionProvider.getConnection()){
			Document document = new Document();
			document.setDocumentNo(documentNo);
			documentDao.delete(conn, document);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
