package cz.muni.fi.pa165.vozovypark.carparkclient;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AjaxProxy extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    final Log log = LogFactory.getLog(AjaxProxy.class);

    String line = "";
    URL new_url = null;
    HttpURLConnection httpURLConnection = null;
    HttpsURLConnection httpsURLConnection = null;
    InputStream inputStream = null;
    BufferedReader bufferedReader = null;

    log.debug("An foreign domain call from AJAX...");

    response.setContentType("application/json");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "No-cache");
    response.setDateHeader("Expires", 0);

    PrintWriter out = response.getWriter();

    String url = request.getParameter("url");
    System.out.println("URL=" + url);
    //using this script behind a proxy server
    //InetAddress inetAddress=InetAddress.getByName("put the proxy name here");   
    //e.g. www.proxy.com
    //InetSocketAddress inetSocketAddress=new InetSocketAddress(inetAddress, port); 
    //port is an integer (e.g. 8080)
    //java.net.Proxy proxy=new java.net.Proxy(java.net.Proxy.Type.HTTP,inetSocketAddress);
    try {
      if (url.indexOf("https") != -1) {
        log.info("Resolving your HTTPS connection...");

        new_url = new URL(url);

        if (url != null) {
          httpsURLConnection = (HttpsURLConnection) (new_url.openConnection());

          //or, if you are behind a proxy server
          //httpsURLConnection=(HttpsURLConnection)(new_url.openConnection(proxy));
        } else {
          log.error("Unable to create the URL...");
        }

        if (httpsURLConnection != null) {
          inputStream = httpsURLConnection.getInputStream();
          bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

          while ((line = bufferedReader.readLine()) != null) {
            out.println(line);
          }

          try {
            if (bufferedReader != null) {
              bufferedReader.close();
            }
          } catch (Exception e) {
            log.error("Unable to close the beffered stream ...");
          }
          try {
            if (inputStream != null) {
              inputStream.close();
            }
          } catch (Exception e) {
            log.error("Unable to close the beffered stream ...");
          }

        } else {
          log.error("Unable to create the HTTPS connection with the required server...");
        }

      } else if (url.indexOf("http") != -1) {

        log.info("Resolving your HTTP connection...");

        new_url = new URL(url);

        if (url != null) {
          httpURLConnection = (HttpURLConnection) (new_url.openConnection());

          //or, if you are behind a proxy server
          //httpURLConnection=(HttpURLConnection)(new_url.openConnection(proxy));
        } else {
          log.error("Unable to create the URL...");
        }

        if (httpURLConnection != null) {
          inputStream = httpURLConnection.getInputStream();
          bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

          while ((line = bufferedReader.readLine()) != null) {
            out.println(line);
          }

          try {
            if (bufferedReader != null) {
              bufferedReader.close();
            }
          } catch (Exception e) {
            log.error("Unable to close the beffered stream ...");
          }
          try {
            if (inputStream != null) {
              inputStream.close();
            }
          } catch (Exception e) {
            log.error("Unable to close the beffered stream ...");
          }

        } else {
          log.error("Unable to create the HTTP connection with the required server...");
        }

      } else {
        log.error("The used protocol is not supported ...");
      }

    } catch (Exception e) {
      log.error("Can't access this URL... please, provide another path...");
    } finally {
      if (out != null) {
        out.close();
      }
    }

  }

  /** Handles the HTTP GET method.
   * @param request servlet request
   * @param response servlet response
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /** Handles the HTTP POST method.
   * @param request servlet request
   * @param response servlet response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /** Returns a short description of the servlet.
   */
  public String getServletInfo() {
    return "Short description";
  }
}
