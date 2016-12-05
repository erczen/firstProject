package viser.document.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import viser.document.model.Document;

public class DocumentDao {
	
	public List<Document> select(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from document");
			rs = pstmt.executeQuery();
			
			List<Document> documentList = new ArrayList<>();
			while(rs.next()){
				documentList.add(convertDocument(rs));
			}
			return documentList;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Document> select(Connection conn, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from document " +
					"order by document_no desc limit ?, ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Document> documentList = new ArrayList<>();
			while (rs.next()) {
				documentList.add(convertDocument(rs));
			}
			return documentList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Document selectByNo(Connection conn, int documentNo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from document where document_no = ?");
			pstmt.setInt(1, documentNo);
			rs = pstmt.executeQuery();
			
			Document document = null;
			if(rs.next()){
				document = convertDocument(rs);
			}
			return document;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Document> selectByDoctype(Connection conn, int doctypeNo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from document where doctype_no = ?");
			pstmt.setInt(1, doctypeNo);
			rs = pstmt.executeQuery();
			
			List<Document> documentList = new ArrayList<>();
			while(rs.next()){
				documentList.add(convertDocument(rs));
			}
			return documentList;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from document");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public void insert(Connection conn, Document document) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("insert into document(doctype_no, title, content, officer_no, officer_check) values(?, ?, ?, ?, ?)");
			pstmt.setInt(1, document.getDoctypeNo());
			pstmt.setString(2, document.getTitle());
			pstmt.setString(3, document.getContent());
			pstmt.setInt(4, document.getOfficerNo());
			pstmt.setBoolean(5, document.isOfficerCheck());
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	public void delete(Connection conn, Document document) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from document where document_no = ?");
			pstmt.setInt(1, document.getDocumentNo());
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	} 
	
	private Document convertDocument(ResultSet rs) throws SQLException{
		return new Document(rs.getInt("document_no"), rs.getInt("employee_no"), rs.getInt("doctype_no")
				, rs.getString("title"), rs.getString("content"), rs.getInt("officer_no"), rs.getBoolean("officer_check"));
	}
}
