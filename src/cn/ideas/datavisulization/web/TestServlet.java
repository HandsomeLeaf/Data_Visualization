package cn.ideas.datavisulization.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class TestServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String str = "{\"msg\":\"成功\",\"success\":\"true\"}";
		
		JSONObject jo = new JSONObject();
		jo.put("name","zhangsan");
		jo.put("age", 18);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(0);
		list.add(4);
		jo.put("data", list);
		
		out.print(jo.toString());
		out.flush();
		out.close();
	}

}
