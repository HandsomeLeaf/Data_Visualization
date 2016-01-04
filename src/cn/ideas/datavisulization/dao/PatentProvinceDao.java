package cn.ideas.datavisulization.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.ideas.datavisulization.domain.Patent;
import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class PatentProvinceDao {
	

	public Map<String,Integer> getProvincePatentCount() {
		Map<String,Integer> map = new HashMap<String,Integer>();
		try {
			Connection con = JdbcUtils.getConnection();
			String sql = "SELECT province,COUNT(*) FROM patentProvince"+
" GROUP BY province";
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1),rs.getInt(2));
			}
		} catch (Exception e) {

		}
		return map;
	}

	
}
