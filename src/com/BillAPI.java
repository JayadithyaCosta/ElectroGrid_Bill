package com;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BillServlet")
public class BillAPI extends HttpServlet {

	private static final long serialVersionUID = -8821173037461710172L;
	Bill billObj = new Bill();

	public BillAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	// INSERT
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = billObj.insertBill(request.getParameter("billCode"), request.getParameter("billName"),
				request.getParameter("address"), request.getParameter("date"), request.getParameter("units"),
				request.getParameter("unitPrice"));
		response.getWriter().write(output);
	}

	// UPDATE
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request);
		String output = billObj.updateBill(paras.get("hidCustomerIDSave").toString(), paras.get("billName").toString(),
				paras.get("address").toString(), paras.get("date").toString(), paras.get("units").toString(),
				paras.get("unitPrice").toString());
		response.getWriter().write(output);
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request);
		String output = billObj.deleteBill(paras.get("billID").toString());
		response.getWriter().write(output);
	}

	public static Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

}
