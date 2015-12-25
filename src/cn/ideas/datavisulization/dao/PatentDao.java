package cn.ideas.datavisulization.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import cn.ideas.datavisulization.domain.Patent;
import cn.itcast.jdbc.TxQueryRunner;

public class PatentDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public List<Patent> findAll() {

		String sql = "select * from patent";
		try {
			return qr.query(sql, new BeanListHandler<Patent>(Patent.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
