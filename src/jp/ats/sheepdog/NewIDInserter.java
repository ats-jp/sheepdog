package jp.ats.sheepdog;

import jp.ats.liverwort.jdbc.LiConnection;
import jp.ats.liverwort.jdbc.LiManager;
import jp.ats.liverwort.jdbc.LiPreparedStatement;
import jp.ats.liverwort.jdbc.LiResultSet;
import jp.ats.liverwort.jdbc.ResourceLocator;
import jp.ats.liverwort.sql.InsertDMLBuilder;
import jp.ats.liverwort.sql.Updatable;

public class NewIDInserter {

	public static long insert(ResourceLocator locator, Updatable updatable) {
		InsertDMLBuilder builder = new InsertDMLBuilder(locator);
		builder.add(updatable);

		LiConnection connection = LiManager.getConnection();

		String[] columnNames = connection.getPrimaryKeyColumnNames(locator);
		if (columnNames.length != 1) throw new IllegalStateException();

		String sql = builder.toString() + " RETURNING " + columnNames[0];

		LiPreparedStatement statement = connection.prepareStatement(sql);
		builder.complement(statement);

		LiResultSet result = statement.executeQuery();
		result.next();

		long maxID = result.getLong(1);

		result.close();
		statement.close();

		return maxID;
	}
}
