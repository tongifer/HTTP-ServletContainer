package edu.upenn.cis.cis455.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import edu.upenn.cis.cis455.http.HTTP;
import edu.upenn.cis.cis455.http.HttpResponse;

/**
 * @author tjgreen
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Response implements HttpServletResponse {

	private Socket socket;
	private HttpResponse httpResponse;
	private String characterEncoding;
	private boolean commited=false;
	private StringWriter stringWriter;
	private BufferedPrintWriter bufferedPrintWriter;
	private int bufferSize=0;
	private Locale locale = Locale.getDefault();
	public Response(HttpResponse httpResponse)
	{
		this.httpResponse = httpResponse;
	}
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#addCookie(javax.servlet.http.Cookie)
	 */
	public void addCookie(Cookie cookie) {
		StringBuilder cookieHeader = new StringBuilder();
		cookieHeader.append(cookie.getName()+"="+cookie.getValue());
		cookieHeader.append("; ");
		cookieHeader.append("PATH="+cookie.getPath());
		cookieHeader.append("; ");
		cookieHeader.append("Domain="+cookie.getDomain());
		cookieHeader.append("; ");
		cookieHeader.append("Max-Age="+cookie.getMaxAge());
		String headerName = "set-cookie";
		if(httpResponse.getHeaders().containsKey(headerName))
		{
			httpResponse.getHeaders().get(headerName).add(cookieHeader.toString());
		}
		else
		{
			ArrayList<String> values = new ArrayList<String>();
			values.add(cookieHeader.toString());
			httpResponse.getHeaders().put(headerName, values);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#containsHeader(java.lang.String)
	 */
	public boolean containsHeader(String headerName) {
		return httpResponse.getHeaders().containsKey(headerName);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#encodeURL(java.lang.String)
	 */
	public String encodeURL(String arg0) {
		return arg0;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#encodeRedirectURL(java.lang.String)
	 */
	public String encodeRedirectURL(String arg0) {
		return arg0;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#encodeUrl(java.lang.String)
	 */
	public String encodeUrl(String arg0) {
		return arg0;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#encodeRedirectUrl(java.lang.String)
	 */
	public String encodeRedirectUrl(String arg0) {
		return arg0;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#sendError(int, java.lang.String)
	 */
	public void sendError(int arg0, String arg1) throws IOException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#sendError(int)
	 */
	public void sendError(int arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#sendRedirect(java.lang.String)
	 */
	public void sendRedirect(String arg0) throws IOException {
		System.out.println("[DEBUG] redirect to " + arg0 + " requested");
		System.out.println("[DEBUG] stack trace: ");
		Exception e = new Exception();
		StackTraceElement[] frames = e.getStackTrace();
		for (int i = 0; i < frames.length; i++) {
			System.out.print("[DEBUG]   ");
			System.out.println(frames[i].toString());
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#setDateHeader(java.lang.String, long)
	 */
	public void setDateHeader(String headerName, long time) {
		ArrayList<String> values = new ArrayList<String>();
		values.add(HTTP.getHttpDateFormat().format(new Date(time)));
		httpResponse.getHeaders().put(headerName, values);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#addDateHeader(java.lang.String, long)
	 */
	public void addDateHeader(String headerName, long time) {
		if(httpResponse.getHeaders().containsKey(headerName))
		{
			httpResponse.getHeaders().get(headerName).add(HTTP.getHttpDateFormat().format(new Date(time)));
		}
		else
		{
			ArrayList<String> values = new ArrayList<String>();
			values.add(HTTP.getHttpDateFormat().format(new Date(time)));
			httpResponse.getHeaders().put(headerName, values);
		}

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#setHeader(java.lang.String, java.lang.String)
	 */
	public void setHeader(String headerName, String headerValue) {
		ArrayList<String> values = new ArrayList<String>();
		values.add(headerValue);
		httpResponse.getHeaders().put(headerName, values);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#addHeader(java.lang.String, java.lang.String)
	 */
	public void addHeader(String headerName, String headerValue) {
		if(httpResponse.getHeaders().containsKey(headerName))
		{
			httpResponse.getHeaders().get(headerName).add(headerValue);
		}
		else
		{
			ArrayList<String> values = new ArrayList<String>();
			values.add(headerValue);
			httpResponse.getHeaders().put(headerName, values);
		}

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#setIntHeader(java.lang.String, int)
	 */
	public void setIntHeader(String headerName, int headerValue) {
		ArrayList<String> values = new ArrayList<String>();
		values.add(String.valueOf(headerValue));
		httpResponse.getHeaders().put(headerName, values);

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#addIntHeader(java.lang.String, int)
	 */
	public void addIntHeader(String headerName, int headerValue) {
		if(httpResponse.getHeaders().containsKey(headerName))
		{
			httpResponse.getHeaders().get(headerName).add(String.valueOf(headerValue));
		}
		else
		{
			ArrayList<String> values = new ArrayList<String>();
			values.add(String.valueOf(headerValue));
			httpResponse.getHeaders().put(headerName, values);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#setStatus(int)
	 */
	public void setStatus(int statusCode) {
		if(statusCode >=200 && statusCode<600)
		{
			httpResponse.setResponseCode(String.valueOf(statusCode));
			if(HTTP.getResponseCodes().containsKey(statusCode))
			{
				httpResponse.setResponseCodeString(HTTP.getResponseCodes().get(statusCode));
			}
		}

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#setStatus(int, java.lang.String)
	 */
	public void setStatus(int arg0, String arg1) {
		//deprecated

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#getCharacterEncoding()
	 */
	public String getCharacterEncoding() {
		if(characterEncoding!=null)
		{
			return characterEncoding;
		}
		else
		{
			return "ISO-8859-1";
		}
	}
	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#getContentType()
	 */
	public String getContentType() {
		if(httpResponse.getHeaders().containsKey("content-type"))
		{
			return httpResponse.getHeaders().get("content-type").get(0);
		}
		else
		{
			return null;
		}
	}

	/* (non-Javadoc) 
	 * @see javax.servlet.ServletResponse#getOutputStream()
	 */
	public ServletOutputStream getOutputStream() throws IOException {
		//out of scope for assignment
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#getWriter()
	 */
	public PrintWriter getWriter() throws IOException {
		if(stringWriter==null || bufferedPrintWriter==null)
		{
			stringWriter = new StringWriter(bufferSize);
			bufferedPrintWriter = new BufferedPrintWriter(stringWriter, false, this);
		}
		return bufferedPrintWriter;

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#setCharacterEncoding(java.lang.String)
	 */
	public void setCharacterEncoding(String encoding) {
		
		characterEncoding = encoding;

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#setContentLength(int)
	 */
	public void setContentLength(int length) {
		
		ArrayList<String> values = new ArrayList<String>();
		values.add(String.valueOf(length));
		httpResponse.getHeaders().put("content-length", values);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#setContentType(java.lang.String)
	 */
	public void setContentType(String value) {
		
		ArrayList<String> values = new ArrayList<String>();
		values.add(value);
		httpResponse.getHeaders().put("content-type", values);
		if(value.contains(";"))
		{
			String encodeString = value.split(";")[1];
			if(encodeString.contains("="))
			{
				setContentType(encodeString.split("=")[1]);
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#setBufferSize(int)
	 */
	public void setBufferSize(int size) {
		bufferSize = size;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#getBufferSize()
	 */
	public int getBufferSize() {
		return bufferSize;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#flushBuffer()
	 */
	public void flushBuffer() throws IOException {
		if(!commited)
		{
			StringBuilder response = new StringBuilder();
			response.append(httpResponse.getResponseStringHeadersOnly());
			response.append(stringWriter.getBuffer());
			socket.getOutputStream().write(response.toString().getBytes());
			commited=true;
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#resetBuffer()
	 */
	public void resetBuffer() {
		if(commited)
		{
			throw new IllegalStateException("Trying to reset a commited servlet response");
		}
		else
		{
			stringWriter.getBuffer().setLength(0);
		}

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#isCommitted()
	 */
	public boolean isCommitted() {
		return commited;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#reset()
	 */
	public void reset() {
		if(commited)
		{
			throw new IllegalStateException("Trying to reset a commited servlet response");
		}
		else
		{
			httpResponse.reset();
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#setLocale(java.util.Locale)
	 */
	public void setLocale(Locale newLocale) {
		if(!commited && bufferedPrintWriter ==null && characterEncoding == null)
		{
			//TODO add encoding 
			locale=newLocale;
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponse#getLocale()
	 */
	public Locale getLocale() {
		return locale;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public HttpResponse getHttpResponse() {
		return httpResponse;
	}
	public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}
	
	

}