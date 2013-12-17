/*
 * Copyright@2011 FPI,Inc. All rights reserved.
 * 
 * Project  : bear-test
 * Filename : MapListTable.java
 * Create   : zhijun_xiong, 2011-10-9
 */
package com.fpi.bear.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dbunit.dataset.Column;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.DefaultTableMetaData;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableMetaData;
import org.dbunit.dataset.datatype.DataType;

/**
 * 定义了一个根据Map List生成{@link ITable}
 * 的实现，在本实现中有一个限制，就是该list至少要包含一条记录。这个限制可以考虑在以后再进行修改。
 * 
 * @author Xiong Zhijun
 * @since bear-test
 * @create 2011-10-9
 */
public class MapListTable implements ITable {

	private String tableName;

	private List<Map<String, Object>> dataList;

	public MapListTable(String tableName, List<Map<String, Object>> dataList) {
		this.tableName = tableName;
		this.dataList = dataList;
	}

	public ITableMetaData getTableMetaData() {
		Map<String, Object> map = dataList.get(0);
		Set<String> keySet = map.keySet();
		Column[] columns = new Column[keySet.size()];
		int i = 0;
		for (String key : keySet) {
			columns[i] = new Column(key, DataType.UNKNOWN);
			i++;
		}

		return new DefaultTableMetaData(tableName, columns);
	}

	public int getRowCount() {
		return dataList.size();
	}

	public Object getValue(int row, String column) throws DataSetException {
		return dataList.get(row).get(column);
	}

}
