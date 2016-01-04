package cn.ideas.datavisulization.test;



import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

import cn.ideas.datavisulization.dao.CompanyDao;
import cn.ideas.datavisulization.dao.GroupDao;
import cn.ideas.datavisulization.dao.PatentDao;
import cn.ideas.datavisulization.dao.PatentProvinceDao;
import cn.ideas.datavisulization.domain.Patent;

public class TestDemo {

	@Test
	public void test(){
		PatentDao dao = new PatentDao();
		List<Patent> patentList = dao.findAll();
		for(Patent pa : patentList)
		{
			System.out.println(pa.getId());
		}
		
	}
	
	@Test
	public void test1()
	{
		PatentDao dao = new PatentDao();
		Map<String,Integer> map = dao.getPatentApplyTrend();
		for(Map.Entry<String,Integer> entry:map.entrySet())
		{
			System.out.println("key="+entry.getKey()+",value="+entry.getValue());
		}
	}
	
	@Test
	public void test2()
	{
		PatentDao dao = new PatentDao();
		Map<String,Integer> map = dao.getPatentPublicyTrend();
		for(Map.Entry<String,Integer> entry:map.entrySet())
		{
			System.out.println("key="+entry.getKey()+",value="+entry.getValue());
		}
	}
	
	@Test
	public void test3()
	{
		PatentDao dao = new PatentDao();
		List<List> map = dao.getPatentType();
		System.out.println(map.size());
		
		for(List item:map)
		{
			for(Object obj:item)
			{
				System.out.println(obj);
			}
		}
		JSONObject jo = new JSONObject();
		jo.put("aa", map);
		System.out.println(jo.toString());
	}
	
	@Test
	public void test4()
	{
		PatentDao dao = new PatentDao();
		System.out.println(dao.getPatentTotalCount());
	}
	
	@Test
	public void test5()
	{
		PatentDao dao = new PatentDao();
		Map<String,Integer> map = dao.getPatentPeopleTrend();
		for(Map.Entry<String,Integer> entry:map.entrySet())
		{
			System.out.println("key="+entry.getKey()+",value="+entry.getValue());
		}
	}
	
	@Test
	public void test6()
	{
		CompanyDao dao = new CompanyDao();
		List<String> list = dao.getCompanyList();
		for(String item : list)
		{
			System.out.println(item);
		}
	}
	
	@Test
	public void test7()
	{
		GroupDao dao = new GroupDao();
		Map<String,Integer> map = dao.getGroupList("发明专利", "big_group", "2013", "淮安澳洋顺昌光电技术有限公司");
		Map<String,Integer> map2 = dao.getGroupList("实用新型", "big_group", "2013", "淮安澳洋顺昌光电技术有限公司");
		for(Map.Entry<String,Integer> entry:map.entrySet())
		{
			System.out.println("key="+entry.getKey()+",value="+entry.getValue());
		}
		
		for(Map.Entry<String,Integer> entry:map2.entrySet())
		{
			System.out.println("key="+entry.getKey()+",value="+entry.getValue());
		}
	}
	
	@Test
	public void test8()
	{
		PatentProvinceDao dao = new PatentProvinceDao();
		Map<String,Integer> map = dao.getProvincePatentCount();
		for(Map.Entry<String,Integer> entry:map.entrySet())
		{
			System.out.println("key="+entry.getKey()+",value="+entry.getValue());
		}
	}
}
