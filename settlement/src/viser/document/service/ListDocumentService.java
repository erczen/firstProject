package viser.document.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import viser.document.dao.DocumentDao;
import viser.document.model.Document;

public class ListDocumentService {

	private DocumentDao documentDao = new DocumentDao();
	private int size = 10;

	public PageForm getDocumentPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = documentDao.selectCount(conn);
			List<Document> content = documentDao.select(
					conn, (pageNum - 1) * size, size);
			return new PageForm(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
