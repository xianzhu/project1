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

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MysqlHelper75 {
	private static final Logger LOGGER = LoggerFactory.getLogger(MysqlHelper75.class);
	static HashMap<String, MysqlHelper75> dbName2Helper = new HashMap<>();
	private ComboPooledDataSource basicDataSource = null;

	public static MysqlHelper75 getInstance(String db_name) {
		MysqlHelper75 instance = dbName2Helper.get(db_name);

		if (instance == null) {
			synchronized (MysqlHelper75.class) {
				instance = dbName2Helper.get(db_name);
				if (instance == null) {
					instance = new MysqlHelper75(db_name);
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

	private MysqlHelper75(String db_name) {
		String dbUrl = String.format(
				"jdbc:mysql://datanode-03:3306/%s?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true",
				db_name);

		if (this.basicDataSource == null)
			try {
				basicDataSource = getDataSource("com.mysql.jdbc.Driver", dbUrl, "rdd", "s6eN8HZTg9Sgr2kD");
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
			pStatement = basicDataSource.getConnection().createStatement();
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
			Connection connection = statement.getConnection();
			statement.close();
			if (connection != null) {
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
		}
		return rs;
	}

	// update/delete/insert
	// sql格式 eg:UPDATE tablename SET columnn = ? WHERE column = ?
	public void executeUpdate(String sql, Object[] parameters) throws SQLException {
		PreparedStatement ps = null;
		try {
			// 1.创建一个ps
			ps = basicDataSource.getConnection().prepareStatement(sql);
			// 给参数赋值
			if (parameters != null)
				for (int i = 0; i < parameters.length; i++) {
					ps.setObject(i + 1, parameters[i]);
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
		basicDataSource.close(true);
	}

	public static void closeAll() {
		for (Entry<String, MysqlHelper75> entry : dbName2Helper.entrySet()) {
			entry.getValue().close();
		}
	}
}
