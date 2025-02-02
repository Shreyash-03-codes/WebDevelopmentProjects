package servlets;

import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeminiAPIClient {
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent";
    private static final String API_KEY = "AIzaSyBStzbIHcGpmUq7SuqdUoY997xpVUP55lg"; // Replace with correct API key

    public String analyzeCode(String codeInput) {
        try {
            // Construct URL with API key
            URL url = new URL(API_URL + "?key=" + API_KEY);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Create JSON request body
            JSONObject jsonRequest = new JSONObject();
            JSONObject content = new JSONObject();
            JSONArray parts = new JSONArray();
            parts.put(new JSONObject().put("text", codeInput));
            content.put("parts", parts);
            jsonRequest.put("contents", new JSONArray().put(content));

            // Send request data
            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonRequest.toString().getBytes("utf-8"));
            }

            // Read the response from the API
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                return "❌ API Error: HTTP " + responseCode;
            }

            // Build the response string from input stream
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }

            // Parse AI response (assuming expected structure)
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Extract the text response from the candidates array
            if (jsonResponse.has("candidates")) {
                JSONArray candidates = jsonResponse.getJSONArray("candidates");
                if (candidates.length() > 0) {
                    JSONObject firstCandidate = candidates.getJSONObject(0);
                    JSONObject aiContent = firstCandidate.getJSONObject("content");
                    JSONArray aiParts = aiContent.getJSONArray("parts");
                    if (aiParts.length() > 0) {
                        return aiParts.getJSONObject(0).getString("text");
                    }
                }
            }

            // Fallback if the expected response format is not received
            return "❌ Unexpected API Response Structure";

        } catch (IOException e) {
            return "❌ API Communication Error: " + e.getMessage();
        }
    }
}
