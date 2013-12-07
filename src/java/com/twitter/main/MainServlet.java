/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitter.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author hendri
 */
public class MainServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainServlet at " + request.getContextPath() + "</h1>");
            Enumeration<String> head = request.getHeaderNames();
            String temp = head.nextElement();
            while (temp != null) {
                out.println(temp);
                temp = head.nextElement();
            }

            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("uGMLFUw9iVBQd9QnoIQYQ")
                    .setOAuthConsumerSecret("70RTdEimZyf6ja4O941Vfr3oGmOZdqlko4gZSz4nEUY")
                    .setOAuthAccessToken("137978001-FRETKcfUZkIgpzpAvQypKNY4zl13cguIE4WgXDa1")
                    .setOAuthAccessTokenSecret("HD6IOZIjPNK5sr2gHS9qs5caBJHvaq50FPXk3iR9bRiXO");
            TwitterFactory tf = new TwitterFactory(cb.build());
//        Twitter twitter = tf.getInstance();
//        	https://api.twitter.com/oauth/access_token
//        https://api.twitter.com/oauth/authorize

            Twitter twitter = null;

//            RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
//            String verifier = request.getParameter("oauth_verifier");
            try {

                twitter = tf.getInstance();
                System.out.println("play");

                twitter.updateStatus("wawawawawa... authentic");
                System.out.println("left");
                User user = twitter.verifyCredentials();

                List<Status> statuses = twitter.getHomeTimeline();
//                System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
//                twitter.getOAuthAccessToken(requestToken, verifier);
//                request.getSession().removeAttribute("requestToken");
                for (Status status : statuses) {
//                    System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                    out.println("@" + status.getUser().getScreenName() + " - " + status.getText() + "<br>");
                }
            } catch (TwitterException e) {
                throw new ServletException(e);
            }
            response.sendRedirect(request.getContextPath() + "/");

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
