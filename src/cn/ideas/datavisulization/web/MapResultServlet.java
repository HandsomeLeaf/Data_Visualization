package cn.ideas.datavisulization.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.ideas.datavisulization.dao.PatentProvinceDao;

public class MapResultServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		
		
		JSONObject jo = new JSONObject();
		PatentProvinceDao dao = new PatentProvinceDao();
		Map<String,Integer> provincePatentCount = dao.getProvincePatentCount();
		jo.put("provincePatentCountMap",provincePatentCount);
		jo.put("province", provincePatentCount.keySet());
		jo.put("patentCount",provincePatentCount.values());
		out.print(jo.toString());
		out.flush();
		out.close();
	}

}
