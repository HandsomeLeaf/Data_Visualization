package cn.ideas.datavisulization.test;



import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.ideas.datavisulization.dao.PatentDao;
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
}
