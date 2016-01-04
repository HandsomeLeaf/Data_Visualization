package cn.ideas.datavisulization.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.ideas.datavisulization.dao.CompanyDao;
import cn.ideas.datavisulization.dao.DateDao;

public class SelectResultServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		
		JSONObject jo = new JSONObject();
		CompanyDao dao = new CompanyDao();
		DateDao dateDao = new DateDao();
		List<String> companyList = dao.getCompanyList();
		//List<String> dateList = dateDao.getPatentPublicyDate();
		List<String> dateYearList = dateDao.getPatentPublicyDateYear();
		jo.put("companyList",companyList);
		jo.put("dateList",dateYearList);
		out.print(jo.toString());
		out.flush();
		out.close();
	}

}
