package cn.ideas.datavisulization.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.itcast.jdbc.JdbcUtils;

public class GroupDao {
		
		// 根据专利类型、专利分组、专利年份、公司名称 统计分组下的专利数目
				public Map<String,Integer> getGroupList(String patentType,String patentGroup,String year,String companyName) {
					Map<String,Integer> map = new HashMap<String,Integer>();
					Connection con=null;
					try {
					 con = JdbcUtils.getConnection();
						String company_Sql="";
						String companyCondition="";
						String timeSql="";
						String timeCondition="";
						boolean chooseCompany=false;
						boolean chooseTime = false;
						if(!companyName.equals("请选择"))
						{
						 company_Sql =",(SELECT id FROM patentId,"+
								"(SELECT apply_number FROM company_dim WHERE  company_name=?) AS t2 WHERE patentId.apply_number=t2.apply_number) AS t3";
						
						 companyCondition =" AND  group_dim.patent_id = t3.id "; 
						 chooseCompany = true;
						}
						if(!year.equals("请选择"))
						{
						 timeSql = ",(SELECT date_sk FROM date_dim WHERE year_number=?) AS t4";
						 timeCondition ="  AND group_dim.date_sk=t4.date_sk ";
						 chooseTime = true;
						}
						String sql = "SELECT "+patentGroup+",COUNT(*)"+
"FROM group_dim"+ company_Sql+ timeSql+
" WHERE patent_type='"+patentType+"' "+companyCondition+timeCondition+
"GROUP BY "+patentGroup;
						PreparedStatement psmt = con.prepareStatement(sql);
						
						if(chooseCompany && ! chooseTime)
						{
							psmt.setString(1, companyName);
						}
						else if(!chooseCompany && chooseTime)
						{
							psmt.setInt(1, Integer.parseInt(year));
						}
						else if(chooseCompany && chooseTime)
						{
							psmt.setString(1, companyName);
							psmt.setInt(2, Integer.parseInt(year));
						}
						ResultSet rs = psmt.executeQuery();
						while (rs.next()) {
							map.put(rs.getString(1), rs.getInt(2));
						}
					} catch (Exception e) {

					}
					finally
					{
						try {
							JdbcUtils.releaseConnection(con);
						} catch (SQLException e) {
						}
					}
					return map;
				}
}
