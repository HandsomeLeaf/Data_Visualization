package cn.ideas.datavisulization.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.ideas.datavisulization.dao.PatentDao;

public class JsonResultServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		
		JSONObject jo = new JSONObject();
		PatentDao dao = new PatentDao();
		Map<String,Integer> applymap = dao.getPatentApplyTrend();
		jo.put("timeShaft",applymap.keySet());
		jo.put("applyPatentCount",applymap.values());
		
		Map<String,Integer> publicymap = dao.getPatentPublicyTrend();
		jo.put("publicyTimeShaft",publicymap.keySet());
		jo.put("publicyPatentCount",publicymap.values());
		
		List<List> patentTypeList = dao.getPatentType();
		jo.put("patentType",patentTypeList);
		
		List<List> patentTypeRateList = new ArrayList<List>();
		
		int totalPatent = dao.getPatentTotalCount();
		for(List list:patentTypeList)
		{
			List tempList = new ArrayList();
			tempList.add(list.get(0));
			float temp = (float) (((int)list.get(1))*1.0/totalPatent);
			BigDecimal b = new BigDecimal(temp);
			tempList.add(b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
			patentTypeRateList.add(tempList);
		}
		
		jo.put("patentTypeRateList",patentTypeRateList);
		
		
		Map<String,Integer> patentPeopleList = dao.getPatentPeopleTrend();
		jo.put("patentPeopleTimeShaft",patentPeopleList.keySet());
		jo.put("patentPeopleCount",patentPeopleList.values());
		out.print(jo.toString());
		out.flush();
		out.close();
	}

}
