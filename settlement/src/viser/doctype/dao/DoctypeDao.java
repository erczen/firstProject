package viser.doctype.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import viser.doctype.model.Doctype;

public class DoctypeDao {
	
	public List<Doctype> select(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from doctype");
			rs = pstmt.executeQuery();
			
			List<Doctype> doctypeList = new ArrayList<>();
			while(rs.next()){
				doctypeList.add(convertDoctype(rs));
			}
			return doctypeList;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Doctype selectbyNo(Connection conn, int doctypeNo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from doctype where doctype_no = ?");
			pstmt.setInt(1, doctypeNo);
			rs = pstmt.executeQuery();
			
			Doctype doctype = null;
			if(rs.next()){
				doctype = convertDoctype(rs);
			}
			return doctype;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void insert(Connection conn, Doctype doctype) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("insert into doctype(doctype_name) values(?)");
			pstmt.setString(1, doctype.getDoctypeName());
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	public void delete(Connection conn, Doctype doctype) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from doctype where doctype_no = ?");
			pstmt.setInt(1, doctype.getDoctypeNo());
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	} 
	
	private Doctype convertDoctype(ResultSet rs) throws SQLException{
		return new Doctype(rs.getInt("doctype_no"), rs.getString("doctype_name"));
	}
}
