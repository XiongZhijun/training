/*
 * Copyright@2011 FPI,Inc. All rights reserved.
 * 
 * Project  : bear-test
 * Filename : AbstractDataSourceTests.java
 * Create   : zhijun_xiong, 2011-10-9
 */
package com.fpi.bear.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * 在spring-test框架中{@link AbstractTransactionalJUnit4SpringContextTests}
 * 的基础之上新增定义了一些跟数据库测试相关的默认配置定义，与com/fpi/bear/test/AbstractDataSourceTests-
 * context. xml配置文件配合使用，用以减少一些默认（重复）的配置。
 * 
 * @author Xiong Zhijun
 * @since bear-test
 * @create 2011-10-9
 */
public abstract class AbstractDataSourceTests extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	protected DataSource dataSource;

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	protected SimpleJdbcInsert simpleJdbcInsert;

	/**
	 * 执行带参数的sql语句。
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数
	 * @return
	 */
	protected int executeSql(String sql, Object... params) {
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 删除指定表。
	 * 
	 * @param tableName
	 *            待删除的表。
	 */
	protected void removeTable(String tableName) {
		jdbcTemplate.update("drop table " + tableName);
	}

	/**
	 * 获取指定数据源表示的{@link IDataSet}对象。
	 * 
	 * @param dataSource
	 *            数据源
	 * @return
	 */
	protected IDataSet getDataSetByDataSource(final DataSource dataSource) {
		return (IDataSet) jdbcTemplate
				.execute(new ConnectionCallback<IDataSet>() {
					public IDataSet doInConnection(Connection con)
							throws SQLException, DataAccessException {
						try {
							return new DatabaseConnection(con).createDataSet();
						} catch (DatabaseUnitException e) {
							throw new RuntimeException(
									"get dataset from datasource failed. datasource : "
											+ dataSource, e);
						}
					}
				});
	}

	/**
	 * 获取默认配置的数据源表示的{@link IDataSet}对象。
	 * 
	 * @return
	 */
	protected IDataSet getDataSetByDataSource() {
		return getDataSetByDataSource(dataSource);
	}

	/**
	 * 将指定数据配置文件定义的数据insert到数据库表中。
	 * 
	 * @param dataSetPath
	 *            指定数据配置文件的路径
	 */
	protected void insertDataSetToDatabase(final String dataSetPath) {
		jdbcTemplate.execute(new ConnectionCallback<Object>() {
			public Object doInConnection(Connection connection)
					throws SQLException, DataAccessException {
				try {
					DatabaseOperation.CLEAN_INSERT.execute(
							new DatabaseConnection(connection),
							getDataSet(dataSetPath));
				} catch (Exception e) {
					throw new RuntimeException(
							"insert dataset to database failed. dataset path : "
									+ dataSetPath, e);
				}
				return null;
			}
		});
	}

	/**
	 * 将指定数据配置文件定义的数据从数据库表中删除。
	 * 
	 * @param dataSetPath
	 *            指定数据配置文件的路径
	 */
	protected void deleteDataSetFromDatabase(final String dataSetPath) {
		jdbcTemplate.execute(new ConnectionCallback<Object>() {
			public Object doInConnection(Connection connection)
					throws SQLException, DataAccessException {
				try {
					DatabaseOperation.DELETE_ALL.execute(
							new DatabaseConnection(connection),
							getDataSet(dataSetPath));
				} catch (Exception e) {
					throw new RuntimeException(
							"delete dataset from database failed. dataset path : "
									+ dataSetPath, e);
				}
				return null;
			}
		});
	}

	/**
	 * 将指定数据配置文件解析成{@link IDataSet}对象。
	 * 
	 * @param dataSetPath
	 * @return
	 * @throws DataSetException
	 * @throws FileNotFoundException
	 */
	protected IDataSet getDataSet(String dataSetPath) throws DataSetException,
			FileNotFoundException {
		return new FlatXmlDataSetBuilder().build(new FileInputStream(
				dataSetPath));
	}

	protected void dbUnitAssertEquals(String tableName,
			String expectedDataSetPath, DataSource dataSource)
			throws FileNotFoundException, DatabaseUnitException {
		ITable expectedTable = getDataSet(expectedDataSetPath).getTable(
				tableName);
		ITable actualTable = getDataSetByDataSource(dataSource).getTable(
				tableName);
		Assertion.assertEquals(expectedTable, actualTable);
	}

	protected void dbUnitAssertEquals(String tableName,
			String expectedDataSetPath, DataSource dataSource,
			String[] ignoreCols) throws FileNotFoundException,
			DatabaseUnitException {
		ITable expectedTable = getDataSet(expectedDataSetPath).getTable(
				tableName);
		ITable actualTable = getDataSetByDataSource(dataSource).getTable(
				tableName);
		Assertion
				.assertEqualsIgnoreCols(expectedTable, actualTable, ignoreCols);
	}

	protected void dbUnitAssertEquals(String tableName,
			String expectedDataSetPath) throws FileNotFoundException,
			DatabaseUnitException {
		dbUnitAssertEquals(tableName, expectedDataSetPath, dataSource);
	}

	protected void dbUnitAssertEquals(String tableName,
			String expectedDataSetPath, String[] ignoreCols)
			throws FileNotFoundException, DatabaseUnitException {
		dbUnitAssertEquals(tableName, expectedDataSetPath, dataSource,
				ignoreCols);
	}

	protected ITable getTable(String dataSetPath, String tableName)
			throws DataSetException, FileNotFoundException {
		return this.getDataSet(dataSetPath).getTable(tableName);
	}

	protected ITable getTableFromDataSource(DataSource dataSource,
			String tableName) throws DataSetException {
		return getDataSetByDataSource(dataSource).getTable(tableName);
	}

	protected ITable getTableFromDataSource(String tableName)
			throws DataSetException {
		return getDataSetByDataSource(dataSource).getTable(tableName);
	}

	protected void dbUnitAssertEquals(String dataSetPath, String tableName,
			List<Map<String, Object>> actualResult)
			throws DatabaseUnitException, FileNotFoundException {
		ITable expectedTable = getTable(dataSetPath, tableName);
		MapListTable actualTable = new MapListTable(tableName, actualResult);
		Assertion.assertEquals(expectedTable, actualTable);
	}
}
