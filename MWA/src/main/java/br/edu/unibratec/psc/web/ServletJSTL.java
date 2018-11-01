package br.edu.unibratec.psc.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static br.edu.unibratec.psc.web.UtilServlet.*;

/**
 * Servlet implementation class ServletJSTL
 */
@WebServlet("/ServletJSTL")
public class ServletJSTL extends HttpServlet {
	
	public static final String NM_ATRIBUTO_DEVE_IMPRIMIR_LISTA = "deveImprimirLista";
	
	private static final String URL_JSP_JSTL = "/jsp/jstl.jsp";
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletJSTL() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		setListaNoRequest(pRequest);
		
		boolean deveImprimirLista = false;
		String paramDeveImprimir = pRequest.getParameter(NM_ATRIBUTO_DEVE_IMPRIMIR_LISTA);
		if (	isBooleanValido(paramDeveImprimir)		) {
			deveImprimirLista = Boolean.parseBoolean(paramDeveImprimir);
		}
		
		pRequest.setAttribute(NM_ATRIBUTO_DEVE_IMPRIMIR_LISTA, deveImprimirLista);
		pRequest.setAttribute(UtilServlet.NM_ATRIBUTO_DATA_HORA, UtilServlet.getDataHoraHojeAgora());
		
		
		redirecionar(pRequest, pResponse, URL_JSP_JSTL);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		doGet(pRequest, pResponse);
	}
	
	@Override
	protected void service(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		doGet(pRequest, pResponse);
	}
	
}