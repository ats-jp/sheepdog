package develop;

import java.sql.DriverManager;
import java.sql.SQLException;

import jp.ats.liverwort.jdbc.ConcreteTransaction;
import jp.ats.liverwort.jdbc.LiTransaction;
import jp.ats.liverwort.jdbc.TransactionFactory;

public class MyTransactionFactory implements TransactionFactory {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public LiTransaction createTransaction() {
		try {
			return new ConcreteTransaction(DriverManager.getConnection(
				"jdbc:postgresql://localhost/",
				"postgres",
				"password"));
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
