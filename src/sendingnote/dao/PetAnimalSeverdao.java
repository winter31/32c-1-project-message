package sendingnote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sendingnote.vo.Cat;
import sendingnote.vo.Dog;
import sendingnote.vo.Mouse;
import sendingnote.vo.Protector;

public class PetAnimalSeverdao {

	Connection conn = null;
	PreparedStatement pstmt = null;

	public PetAnimalSeverdao() {

	}

	public boolean insertProtector(Protector p) {
		conn = MyConnection.getConnection();
		try {
			String sql = "insert into protector (protector, jumin, age, pet_type) values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getProtector());
			pstmt.setString(2, p.getJumin());
			pstmt.setInt(3, p.getAge());

			if (p instanceof Dog) {
				pstmt.setString(4, "d");
				pstmt.executeUpdate();

				sql = "insert into Dog (jumin, dogname, dogage, dogtype ) values (?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p.getJumin());
				pstmt.setString(2, ((Dog) p).getDogName());
				pstmt.setInt(3, ((Dog) p).getDogAge());
				pstmt.setString(4, ((Dog) p).getDogType());
				pstmt.executeUpdate();

			} else if (p instanceof Cat) {
				pstmt.setString(4, "c");
				pstmt.executeUpdate();

				sql = "insert into Cat (jumin, catname, catage, cattype ) values (?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p.getJumin());
				pstmt.setString(2, ((Cat) p).getCatName());
				pstmt.setInt(3, ((Cat) p).getCatAge());
				pstmt.setString(4, ((Cat) p).getCatType());
				pstmt.executeUpdate();

			} else if (p instanceof Mouse) {
				pstmt.setString(4, "m");
				pstmt.executeUpdate();

				sql = "insert into Mouse (jumin, mousename, mouseage, mousetype ) values (?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p.getJumin());
				pstmt.setString(2, ((Mouse) p).getMouseName());
				pstmt.setInt(3, ((Mouse) p).getMouseAge());
				pstmt.setString(4, ((Mouse) p).getMouseType());
				pstmt.executeUpdate();

			}
				conn.commit();
		} catch (Exception e) {
			e.printStackTrace();

			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}

	public Protector searchProtector(String jumin) {
		conn = MyConnection.getConnection();

		try {
			String sql = "select protector, jumin, age, pet_type from protector where jumin = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jumin);

			ResultSet rs = pstmt.executeQuery();
			rs.next();

			String protector = rs.getString("protector");
			int age = rs.getInt("age");
			String pet_type = rs.getString("pet_type");

			if (pet_type.equals("d")) {
				sql = "select jumin, dogname, dogage, dogtype from dog where jumin = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, jumin);

				rs = pstmt.executeQuery();
				rs.next();

				String dogName = rs.getString("dogname");
				int dogAge = rs.getInt("dogage");
				String dogType = rs.getString("dogtype");

				return new Dog(protector, age, jumin, dogName, dogAge, dogType);

			} else if (pet_type.equals("c")) {
				sql = "select jumin, catname, catage, cattype from cat where jumin = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, jumin);

				rs = pstmt.executeQuery();
				rs.next();

				String catName = rs.getString("catname");
				int catAge = rs.getInt("catage");
				String catType = rs.getString("cattype");

				return new Cat(protector, age, jumin, catName, catAge, catType);

			} else if (pet_type.equals("m")) {
				sql = "select jumin, mousename, mouseage, mousetype from mouse where jumin = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, jumin);

				rs = pstmt.executeQuery();
				rs.next();

				String mouseName = rs.getString("mousename");
				int mouseAge = rs.getInt("mouseage");
				String mouseType = rs.getString("mousetype");

				return new Mouse(protector, age, jumin, mouseName, mouseAge, mouseType);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void updateProtector(Protector p) {

		conn = MyConnection.getConnection();

		try {
			String sql = "update protector set protector = ?, age = ? where jumin = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ((Protector) p).getProtector());
			pstmt.setInt(2, ((Protector) p).getAge());
			pstmt.setString(3, p.getJumin());
			pstmt.executeUpdate();

			if (p instanceof Dog) {
				sql = "update dog set dogname = ?, dogage = ?, dogtype = ? where jumin = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ((Dog) p).getDogName());
				pstmt.setInt(2, ((Dog) p).getDogAge());
				pstmt.setString(3, ((Dog) p).getDogType());
				pstmt.setString(4, p.getJumin());
			} else if (p instanceof Cat) {
				sql = "update dog set catname = ?, catage = ?, cattype = ? where jumin = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ((Cat) p).getCatName());
				pstmt.setInt(2, ((Cat) p).getCatAge());
				pstmt.setString(3, ((Cat) p).getCatType());
				pstmt.setString(4, p.getJumin());
			} else if (p instanceof Mouse) {
				sql = "update mouse set mousename = ?, mouseage = ?, mousetype = ? where jumin = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ((Mouse) p).getMouseName());
				pstmt.setInt(2, ((Mouse) p).getMouseAge());
				pstmt.setString(3, ((Mouse) p).getMouseType());
				pstmt.setString(4, p.getJumin());
			}

			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteProtector(String jumin) {
		conn = MyConnection.getConnection();
		Protector temp = searchProtector(jumin);
		if (temp != null) {
			try {
				
				String sql = "delete from protector where jumin = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, jumin);
				pstmt.executeQuery();

				if (temp instanceof Dog) {
					sql = "delete from dog where jumin = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, jumin);
					pstmt.executeQuery();
				} else if (temp instanceof Cat) {
					sql = "delete from cat where jumin = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, jumin);
					pstmt.executeQuery();
				} else if (temp instanceof Mouse) {
					sql = "delete from mouse where jumin = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, jumin);
					pstmt.executeQuery();
				}
				conn.commit();

				return true;

			} catch (Exception e) {
				e.printStackTrace();

				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				return false;
			}
		}
		return false;
	}

	public ArrayList<Protector> getAll() {

		Connection conn = MyConnection.getConnection();

		ArrayList<Protector> pList = new ArrayList<>();

		try {
			String sql_d = "select p.protector, p.jumin, p.age, d.dogname, d.dogage, d.dogtype from protector p, dog d where p.jumin = d.jumin";
			pstmt = conn.prepareStatement(sql_d);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String protector = rs.getString(1);
				String jumin = rs.getString(2);
				int age = rs.getInt(3);
				String dogname = rs.getString(4);
				int dogage = rs.getInt(5);
				String dogtype = rs.getString(6);

				Dog d = new Dog(protector, age, jumin, dogname, dogage, dogtype);
				pList.add(d);

			}
			String sql_c = "select p.protector, p.jumin, p.age, c.catname, c.catage, c.cattype from protector p, cat c where p.jumin = c.jumin";
			pstmt = conn.prepareStatement(sql_c);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String protector = rs.getString(1);
				String jumin = rs.getString(2);
				int age = rs.getInt(3);
				String catname = rs.getString(4);
				int catage = rs.getInt(5);
				String cattype = rs.getString(6);

				Cat c = new Cat(protector, age, jumin, catname, catage, cattype);
				pList.add(c);
			}

			String sql_m = "select p.protector, p.jumin, p.age, m.mousename, m.mouseage, m.mousetype from protector p, mouse m where p.jumin = m.jumin";
			pstmt = conn.prepareStatement(sql_m);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String protector = rs.getString(1);
				String jumin = rs.getString(2);
				int age = rs.getInt(3);
				String mousename = rs.getString(4);
				int mouseage = rs.getInt(5);
				String mousetype = rs.getString(6);

				Mouse m = new Mouse(protector, age, jumin, mousename, mouseage, mousetype);
				pList.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pList;
	}
}
