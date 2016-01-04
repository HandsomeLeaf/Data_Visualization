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
import cn.ideas.datavisulization.dao.GroupDao;

public class GroupResultServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		
		String company = request.getParameter("company");
		String time = request.getParameter("time");
		String patentType = request.getParameter("patentType");
		System.out.println(company);
		System.out.println(time);
		JSONObject jo = new JSONObject();
		GroupDao dao = new GroupDao();
		Map<String,Integer> inventonPatentmap=null;
		Map<String,Integer> applyNewPatentmap = null;
		if(company == null||company.equals(""))
		{
			inventonPatentmap = dao.getGroupList("发明专利", "big_group", "2013", "淮安澳洋顺昌光电技术有限公司");
		    applyNewPatentmap = dao.getGroupList("实用新型", "big_group", "2013", "淮安澳洋顺昌光电技术有限公司");
		}
		else
		{
			inventonPatentmap = dao.getGroupList("发明专利", patentType, time,company);
		    applyNewPatentmap = dao.getGroupList("实用新型", patentType,time, company);
		}
		if(inventonPatentmap.keySet().size()>0)
		jo.put("groupkey", inventonPatentmap.keySet());
		else
		jo.put("groupkey", applyNewPatentmap.keySet());
		jo.put("inventonPatent", inventonPatentmap.values());
		jo.put("applyNewPatent", applyNewPatentmap.values());
		
		System.out.println(jo.toString());
		out.print(jo.toString());
		out.flush();
		out.close();
	}

}
