package sendingnote.dao;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;


import sendingnote.vo.Note;
import sendingnote.vo.User;

public class SendingNoteDao {
	private String sql = null;
	private Connection conn = null;
	private PreparedStatement ps = null;
	
	public boolean insertUser(User user) {

		boolean result = false;
		String id1 = null;
		try {
			sql = "select id from log_in_out where id = ?";
			basicSettings(sql);
			ps.setString(1, user.getId());
			ResultSet rs1 = ps.executeQuery();
			if (rs1.next()) {
				id1 = rs1.getString(1);
			}
			
			if (id1 == null){
			sql = "insert into users1(id, name) values(?, ?)";
			basicSettings(sql);
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.executeQuery();

			result = true;
			
			}else if (id1 != null){
				result = false;
			}
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return result;
	}

	public void insertNote(Note note) {
		try {
			sql = "insert into notes(id, title, content , to_id) values(?, ?, ?, ?)";
			basicSettings(sql);
			ps.setString(1, note.getId());
			ps.setString(2, note.getTitle());
			ps.setString(3, note.getContent());
			ps.setString(4, note.getToUserId());
			ps.executeQuery();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public boolean insertLogInOut(User user) {
		boolean result = false;
		String id1 = null;

		try {
			sql = "select id from log_in_out where id = ?";
			basicSettings(sql);
			ps.setString(1, user.getId());
			ResultSet rs1 = ps.executeQuery();
			if (rs1.next()) {
				id1 = rs1.getString(1);
			}
			if (id1 == null) {
				sql = "insert into log_in_out(id, log) values( ?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, user.getId());
				ps.setString(2, "t");
				ps.executeUpdate();
				result = true;
			} else if (id1 != null) {
				sql = "update log_in_out set log = ?, log_in = sysdate where id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, "t");
				ps.setString(2, user.getId());
				ps.executeUpdate();
				result = true;
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<User> selectAllUsers() {
		ArrayList<User> result = new ArrayList<>();
		try {
			sql = "select a.id, name, log from users1 a, log_in_out where a.id = log_in_out.id order by log desc";
			basicSettings(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String log = rs.getString(3);
				if(log.equals("f")){
					log = "厚立加吝";
				}else{
					log= "立加吝";
				}
				User alluser = new User(id, name, log);
				result.add(alluser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteAllMessager(String id) {
		boolean result = false;
		try {
			sql = "delete from notes where to_id = ?";
			basicSettings(sql);
			ps.setString(1, id);
			ps.executeUpdate();
			conn.commit();
			result = true;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Note> checkMyMessage(String id) {
		
		Note result = null;
		ArrayList<Note> result1 = new ArrayList<>();
		String idd = null;
		Timestamp login = null;
		Timestamp logout = null;
		try {
			sql = "select id, log_in, log_out from log_in_out a where id = ?";
			basicSettings(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				idd = rs.getString(1);
//				login = rs.getTimestamp(2);
//				logout = rs.getTimestamp(3);
			}

//			sql = "select id, title, content, date_created, to_id, read from notes where (date_created >= ? and date_created <= ?) and (to_id = ?) and read = 'f'";
			sql = "select id, title, content, date_created, to_id, read from notes where (to_id = ?) and (lower(read) = 'f')";
			basicSettings(sql);
//			ps.setTimestamp(1, logout);
//			ps.setTimestamp(2, login);
			ps.setString(1, idd);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				String id2 = rs1.getString(1);
				String title = rs1.getString(2);
				String content = rs1.getString(3);
				Timestamp mDate = rs1.getTimestamp(4);
				String to_id = rs1.getString(5);
				String read = rs1.getString(6);
				result = new Note(id2, title, content, mDate, to_id, id2, read);

				result1.add(result);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1;
	}

	public boolean logoutChecking(User user) {
		boolean result = false;
		String id1 = null;
		try {
			sql = "select id from log_in_out where id = ?";
			basicSettings(sql);
			ps.setString(1, user.getId());
			ResultSet rs1 = ps.executeQuery();
			if (rs1.next()) {
				id1 = rs1.getString(1);
			}
			if (id1 != null) {
				sql = "update log_in_out set log = 'f', log_out = sysdate where id = ?";
				basicSettings(sql);
				ps.setString(1, user.getId());
				ps.executeUpdate();
				result = true;
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<Note> allNote(String id){
		ArrayList<Note> result = new ArrayList<>(); 
		try{
			sql = "select id, title, content, date_created, to_id, read from notes where to_id = ?";
			basicSettings(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String id1 = rs.getString(1);
				String title1 = rs.getString(2);
				String content1 = rs.getString(3);
				Timestamp tt = rs.getTimestamp(4);
				String to_id1 = rs.getString(5);
				String read = rs.getString(6);
				Note noo = new Note(id1, title1, content1, tt, id1, to_id1, read);
				result.add(noo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<Note> allnjotNote(String id){
		ArrayList<Note> result = new ArrayList<>(); 
		try{
			sql = "select id, title, content, date_created, to_id, read from notes where id = ?";
			basicSettings(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String id1 = rs.getString(1);
				String title1 = rs.getString(2);
				String content1 = rs.getString(3);
				Timestamp tt = rs.getTimestamp(4);
				String to_id1 = rs.getString(5);
				String read = rs.getString(6);
				Note noo = new Note(id1, title1, content1, tt, id1, to_id1, read);
				result.add(noo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public boolean readAndUnread(Note note) throws SQLException{
		boolean result = false;
		try{
			sql = "update notes set read = ? where title = ? and to_id = ? ";
			basicSettings(sql);
			ps.setString(1, note.getRead());
			ps.setString(2, note.getTitle());
			ps.setString(3, note.getToUserId());
			ps.executeUpdate();
			result = true;
			conn.commit();
		}catch(Exception e){
			conn.rollback();
			e.printStackTrace();
		}
		return result;
	}
	public Note selectedNote(String touserId){
		Note result = null;
		try{
			sql = "select * from notes where to_id = ? and read = 'f'";
			basicSettings(sql);
			ps.setString(1, touserId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				String name = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Timestamp date = rs.getTimestamp(4);
				String to_id = rs.getString(5);
				String read = rs.getString(6);
				result = new Note(name, title, content, date, to_id, to_id, read);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public void basicSettings(String sql) {
		conn = MyConnection.getConnection();
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
