import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TesteInsert {
	public static void main(String[] args) {
		Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/mobilehealth2", "postgres", "teste2");
			stmt = c.createStatement();

			String sql = "insert into public.recommendation (id, id_person, id_content, datecreation, visited) values (998, 2, 2, now(), false)";
			stmt.executeUpdate(sql);

	        stmt.close();
	        c.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
	}
}
