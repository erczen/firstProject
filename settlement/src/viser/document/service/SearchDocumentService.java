package viser.document.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import viser.doctype.dao.DoctypeDao;
import viser.doctype.model.Doctype;
import viser.document.dao.DocumentDao;
import viser.document.model.Document;

public class SearchDocumentService {
	
	private DocumentDao documentDao = new DocumentDao();
	private DoctypeDao doctypeDao = new DoctypeDao(); 
	
	public List<Document> search(String doctypeName){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Doctype doctype = doctypeDao.selectByName(conn, doctypeName);
			List<Document> listDocument = documentDao.selectByDoctype(conn, doctype.getDoctypeNo());
			
			conn.commit();
			
			return listDocument;
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
}
