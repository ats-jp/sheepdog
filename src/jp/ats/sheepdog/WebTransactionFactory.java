package jp.ats.sheepdog;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jp.ats.liverwort.jdbc.ConcreteTransaction;
import jp.ats.liverwort.jdbc.LiTransaction;
import jp.ats.liverwort.jdbc.TransactionFactory;

public class WebTransactionFactory implements TransactionFactory {

	private final DataSource dataSource;

	public WebTransactionFactory() throws NamingException {
		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/datasource");
	}

	@Override
	public LiTransaction createTransaction() {
		try {
			return new ConcreteTransaction(dataSource.getConnection());
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
