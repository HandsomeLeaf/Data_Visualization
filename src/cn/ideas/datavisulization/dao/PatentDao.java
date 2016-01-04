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

	public Map<String, Integer> getPatentApplyTrend() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			Connection con = JdbcUtils.getConnection();
			String sql = "SELECT COUNT(*),(SELECT year_quarter FROM date_dim WHERE  date_sk = aplly_date_sk) AS _quarter"
					+ " FROM patent" + " GROUP BY _quarter";
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(2), rs.getInt(1));
			}
		} catch (Exception e) {

		}
		return map;
	}

	public Map<String, Integer> getPatentPublicyTrend() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			Connection con = JdbcUtils.getConnection();
			String sql = "SELECT COUNT(*),(SELECT year_quarter FROM date_dim WHERE  date_sk = publicity_date_sk) AS _quarter"
					+ " FROM patent" + " GROUP BY _quarter";
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(2), rs.getInt(1));
			}
		} catch (Exception e) {

		}
		return map;
	}

	public List<List> getPatentType() {
		List<List> listMap = new ArrayList<List>();
		try {
			Connection con = JdbcUtils.getConnection();
			String sql = "SELECT COUNT(*),IFNULL(TYPE,'暂无类型') FROM patent GROUP BY type";
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				List templist = new ArrayList();
				templist.add(rs.getString(2));
				templist.add(rs.getInt(1));
				listMap.add(templist);
			}
		} catch (Exception e) {

		}
		return listMap;
	}

	public int getPatentTotalCount() {
		try {
			Connection con = JdbcUtils.getConnection();
			String sql = "SELECT COUNT(*) FROM patent";
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {

		}
		return 0;
	}

	// 研发人员数量趋势
	public Map<String, Integer> getPatentPeopleTrend() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			Connection con = JdbcUtils.getConnection();
			String sql = "SELECT COUNT(DISTINCT NAME,company) AS peopleCount,(SELECT year_quarter FROM date_dim WHERE  date_sk = people_dim.date_sk) AS _quarter"
					+ " FROM people_dim" + " GROUP BY _quarter";
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(2), rs.getInt(1));
			}
		} catch (Exception e) {

		}
		return map;
	}
}
