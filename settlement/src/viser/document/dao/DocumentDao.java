package viser.document.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
	
	public List<Document> select(Connection conn, int startRow, int size, int doctypeNo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from document " +
					"where doctype_no = ? order by document_no desc limit ?, ?");
			pstmt.setInt(1, doctypeNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
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
	
	public List<Document> selectSubmitDocument(Connection conn, int startRow, int size, int employeeNo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from document " +
					"where employee_no = ? order by document_no desc limit ?, ?");
			pstmt.setInt(1, employeeNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
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
	
	public List<Document> select(Connection conn, int startRow, int size, boolean officerCheck, int officerNo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from document " +
					"where officer_check = ? and officer_no = ? order by document_no desc limit ?, ?");
			pstmt.setBoolean(1, officerCheck);
			pstmt.setInt(2, officerNo);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, size);
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
	
	public int selectCount(Connection conn, boolean officerCheck, int officerNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select count(*) from document where officer_check = ? and officer_no = ?");
			pstmt.setBoolean(1, officerCheck);
			pstmt.setInt(2, officerNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectEmployeeDocumentCount(Connection conn, int employeeNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select count(*) from document where employee_no = ?");
			pstmt.setInt(1, employeeNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
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
	
	public int selectCount(Connection conn, int doctypeNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select count(*) from document where doctype_no = ?");
			pstmt.setInt(1, doctypeNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int insert(Connection conn, Document document) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("insert into document(employee_no, doctype_no, title, content, officer_no, officer_check, regdate) values(?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, document.getEmployeeNo());
			pstmt.setInt(2, document.getDoctypeNo());
			pstmt.setString(3, document.getTitle());
			pstmt.setString(4, document.getContent());
			pstmt.setInt(5, document.getOfficerNo());
			pstmt.setBoolean(6, document.isOfficerCheck());
			pstmt.setTimestamp(7, new Timestamp(document.getRegDate().getTime()));
			return pstmt.executeUpdate();
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
	
	private Date toDate(Timestamp date){
		return date == null ? null : new Date(date.getTime());
	}
	
	private Document convertDocument(ResultSet rs) throws SQLException{
		return new Document(rs.getInt("document_no"), rs.getInt("employee_no"), rs.getInt("doctype_no")
				, rs.getString("title"), rs.getString("content"), rs.getInt("officer_no"), rs.getBoolean("officer_check"), rs.getDate("regdate"));
	}
}
