package cn.ideas.datavisulization.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.itcast.jdbc.JdbcUtils;

public class CompanyDao {
	// 获取所有的公司名称
		public List<String> getCompanyList() {
			List<String> list = new ArrayList<String>();
			try {
				Connection con = JdbcUtils.getConnection();
				String sql = "SELECT DISTINCT(company_name)FROM company_dim ORDER BY company_name";
				PreparedStatement psmt = con.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					list.add(rs.getString(1));
				}
			} catch (Exception e) {

			}
			return list;
		}
}
