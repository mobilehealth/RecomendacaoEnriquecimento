package mobilehealth.prc.temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertRecommendation {

	public static void insert(int id_person, int id_content) {
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/mobilehealth2",
					"postgres", "postgres");
			stmt = c.createStatement();

			String sql = "insert into public.recommendation (id, id_person, id_content, datecreation, visited) "
					+ "values (nextval('recommendation_id_seq'), " + id_person + ", " + id_content + ", now(), false)";
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
