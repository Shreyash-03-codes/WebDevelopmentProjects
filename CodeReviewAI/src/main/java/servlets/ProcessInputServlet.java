
package servlets;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
@WebServlet("/processInputServlet")
public class ProcessInputServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the code input from the form
        String codeInput = request.getParameter("codeInput");

        // Initialize Gemini API client
        GeminiAPIClient geminiClient = new GeminiAPIClient();

        // String to store AI response
        String aiResponse = "";

        try {
            // Use Gemini API to analyze the code
            aiResponse = geminiClient.analyzeCode(codeInput);

            // Remove backticks and replace line breaks with <br> for HTML rendering
            aiResponse = aiResponse.replace("```", "").replace("\n", "<br>");

        } catch (Exception e) {
            // Handle any exceptions from the API call
            aiResponse = "An error occurred while analyzing the code: " + e.getMessage();
        }

        // Set AI response in the request scope for JSP
        request.setAttribute("aiResponse", aiResponse);
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
    }
}
