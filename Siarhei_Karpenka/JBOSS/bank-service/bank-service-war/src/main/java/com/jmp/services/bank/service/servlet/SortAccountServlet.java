package com.jmp.services.bank.service.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jmp.services.bank.service.persistance.eao.AccountManagerLocal;
import com.jmp.services.bank.service.util.AccountSortParam;
import com.jmp.services.bank.service.util.SortType;

@WebServlet(description = "sort Account Servlet", urlPatterns = {"/sort"})
public class SortAccountServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    @EJB
    AccountManagerLocal accountManagerLocal;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountSortParam param = AccountSortParam.valueOf(request.getParameter("param").toUpperCase());
        SortType type = SortType.valueOf(request.getParameter("type").toUpperCase());
        request.setAttribute("accounts", accountManagerLocal.sort(type, param));
        request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request, response);
    }
}
