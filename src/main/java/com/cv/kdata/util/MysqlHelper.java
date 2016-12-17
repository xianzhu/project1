package com.cv.kdata.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.kdata.conf.ConfigurationHelper;
import com.cv.kdata.cont.RDDWebConst;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MysqlHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(MysqlHelper.class);
	static HashMap<String, MysqlHelper> dbName2Helper = new HashMap<>();
	private ComboPooledDataSource basicDataSource = null;

	public static MysqlHelper getInstance(String db_name) {
		MysqlHelper instance = (MysqlHelper) dbName2Helper.get(db_name);

		if (instance == null) {
			synchronized (MysqlHelper.class) {
				if (instance == null) {
					instance = new MysqlHelper(db_name);
					dbName2Helper.put(db_name, instance);
				}
			}

		}

		return instance;
	}

	private ComboPooledDataSource getDataSource(String dbDriverName, String url, String userName, String password)
			throws PropertyVetoException, SQLException {
		basicDataSource = new ComboPooledDataSource();
		basicDataSource.setDriverClass(dbDriverName);
		// basicDataSource.setJdbcUrl(url);
		// basicDataSource.setUser(userName);
		// basicDataSource.setPassword(password);
		// basicDataSource.setInitialPoolSize(5);
		// basicDataSource.setMinPoolSize(5);
		// basicDataSource.setMaxPoolSize(20);
		// basicDataSource.setMaxStatements(50);
		// basicDataSource.setTestConnectionOnCheckin(false);
		// basicDataSource.setMaxIdleTime(300);
		// basicDataSource.setPreferredTestQuery("select 1");
		// basicDataSource.setIdleConnectionTestPeriod(30);
		// basicDataSource.setTestConnectionOnCheckout(true);
		basicDataSource.setJdbcUrl(url);
		basicDataSource.setUser(userName);
		basicDataSource.setPassword(password);
		basicDataSource.setInitialPoolSize(5);
		basicDataSource.setMinPoolSize(5);
		basicDataSource.setMaxPoolSize(20);
		basicDataSource.setMaxStatements(50);
		basicDataSource.setMaxIdleTime(300);
		return basicDataSource;
	}

	private MysqlHelper(String db_name) {
		String dbDriverName = null;
		String dbUrl = null;
		String dbUserName = null;
		String dbPassword = null;
		if (db_name.equalsIgnoreCase(RDDWebConst.WEB_DB_NAME)) {
			dbDriverName = ConfigurationHelper.WEB_DB_DRIVER;
			dbUrl = ConfigurationHelper.WEB_DB_URL;
			dbUserName = ConfigurationHelper.WEB_DB_USER;
			dbPassword = ConfigurationHelper.WEB_DB_PASSWORD;
		} else if (db_name.equalsIgnoreCase(RDDWebConst.SPIDER_DB_NAME)) {
			dbDriverName = ConfigurationHelper.SPIDER_DB_DRIVER;
			dbUrl = ConfigurationHelper.SPIDER_DB_URL;
			dbUserName = ConfigurationHelper.SPIDER_DB_USER;
			dbPassword = ConfigurationHelper.SPIDER_DB_PASSWORD;
		} else {
			// configure a default db helper
			dbDriverName = ConfigurationHelper.DEFAULT_DB_DRIVER;
			dbUrl = String.format(ConfigurationHelper.DEFAULT_DB_URL, db_name);
			dbUserName = ConfigurationHelper.DEFAULT_USER_NAME;
			dbPassword = ConfigurationHelper.DEFAULT_DB_PASSWORD;
			// throw new RuntimeException("not configured db_name:"+db_name);
		}

		if (this.basicDataSource == null)
			try {
				basicDataSource = getDataSource(dbDriverName, dbUrl, dbUserName, dbPassword);
			} catch (PropertyVetoException | SQLException ex) {
				ex.printStackTrace();
				LOGGER.error("exception", ex);
			}
	}

	public Connection getConn() throws SQLException {
		return this.basicDataSource.getConnection();
	}

	public ResultSet getResultSet(String sql) {
		ResultSet resultSet = null;
		Statement pStatement = null;
		try {
			// LOGGER.info(String.format("current connection count:%d, busy
			// count:%d",
			// basicDataSource.getNumConnections(),basicDataSource.getNumBusyConnections()));
			pStatement = basicDataSource.getConnection().createStatement();
			// LOGGER.info(String.format("after "));
			resultSet = pStatement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("", e);
		}

		return resultSet;
	}

	public void close(ResultSet rs) {
		try {
			if (rs != null) {
				// LOGGER.info("close result set ");
				Statement statement = rs.getStatement();
				rs.close();
				close("close resultset", statement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(String source, Statement statement) throws SQLException {
		if (statement != null) {
			// LOGGER.info(source+",====> close statement");
			Connection connection = statement.getConnection();
			statement.close();
			if (connection != null) {
				// LOGGER.info("close connection");
				connection.close();
			}
		}
	}

	public void excuteSql(String sql) throws SQLException {
		Statement pStatement = null;
		try {
			pStatement = basicDataSource.getConnection().createStatement();
			pStatement.execute(sql);
		} catch (SQLException ex) {
			LOGGER.error(sql, ex);
		} finally {
			close("execute none query", pStatement);
		}
	}

	// select
	public ResultSet executeQuery(String sql, List<Object> parameters) {
		ResultSet rs = null;
		try {
			PreparedStatement ps = basicDataSource.getConnection().prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.size(); i++) {
					ps.setObject(i + 1, parameters.get(i));
				}
			}
			rs = ps.executeQuery();
			System.out.println(ps);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

		}
		return rs;
	}

	// select
	public ResultSet executeQuery(String sql, Object[] parameters) {
		ResultSet rs = null;
		try {
			PreparedStatement ps = basicDataSource.getConnection().prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setObject(i + 1, parameters[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

		}
		return rs;
	}

	// update/delete/insert
	// sql格式:UPDATE tablename SET columnn = ? WHERE column = ?
	public void executeUpdate(String sql, Object[] parameters) throws SQLException {
		PreparedStatement ps = null;
		try {
			// 1.创建一个ps
			ps = basicDataSource.getConnection().prepareStatement(sql);
			// 给？赋值
			if (parameters != null)
				for (int i = 0; i < parameters.length; i++) {
					ps.setObject(i + 1, parameters[i]);
					// ps.setString(i + 1, parameters[i]);
				}
			// 执行
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();// 开发阶段
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭资源
			close("execute none query", ps);
		}
	}

	public int excuteSqlWithIdRet(String sql) throws SQLException {
		Statement pStatement = null;
		ResultSet resultSet = null;
		int id = 0;
		try {
			pStatement = basicDataSource.getConnection().createStatement();
			pStatement.execute(sql);
			pStatement.close();
			String sql2 = "select LAST_INSERT_ID() as id";
			resultSet = getResultSet(sql2);
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} finally {
			close(resultSet);
		}

		return id;
	}

	public void close() {
		// basicDataSource.get
		basicDataSource.close(true);
	}

	public static void closeAll() {
		for (Entry<String, MysqlHelper> entry : dbName2Helper.entrySet()) {
			entry.getValue().close();
		}
	}
}
