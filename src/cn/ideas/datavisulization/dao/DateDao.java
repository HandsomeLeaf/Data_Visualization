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

public class DateDao {
	

	public List<String> getPatentPublicyDate() {
		List<String> result = new ArrayList<String>();
		try {
			Connection con = JdbcUtils.getConnection();
			String sql = "SELECT  DISTINCT(SELECT year_quarter FROM date_dim WHERE date_sk = publicity_date_sk) AS _quarter FROM patent";
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (Exception e) {

		}
		return result;
	}
	
	public List<String> getPatentPublicyDateYear() {
		List<String> result = new ArrayList<String>();
		try {
			Connection con = JdbcUtils.getConnection();
			String sql = "SELECT  DISTINCT(SELECT year_number FROM date_dim WHERE date_sk = publicity_date_sk) AS _quarter FROM patent";
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (Exception e) {

		}
		return result;
	}

	
}
