<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - AI Code Review</title>
    <link rel="stylesheet" href="bootstrap.css">
    <style>
        body {
            padding-bottom: 100px; 
            padding-top: 20px;
        }

       
        .fixed-input-container {
            position: fixed;
            bottom: 0;
            left: 0;
			height:38vh;
            width: 100%;
            background: white;
            z-index: 1000;
            padding: 10px;
            box-shadow: 0px -4px 6px rgba(0, 0, 0, 0.1);
        }

        .form-group textarea {
            width: 100%;
            height: 50%;
            font-size: 1rem;
        }

        .response-box {
            margin-bottom: 20px; 
            border: 1px solid #ddd;
            padding: 10px;
            background-color: #f8f9fa;
            max-height: 45vh; 
            overflow-y: auto; 
			font-size: 130%;
        }
    </style>
</head>
<body>

    <div class="container mt-2 mb-3">
        <h2>AI Code Review Result</h2>
        <div class="response-box pt-3">
            <h5>AI Response:</h5>
            <div id="aiResponseBox">
                <p id="aiResponseText">
                    <%= request.getAttribute("aiResponse") != null ? request.getAttribute("aiResponse") : "Your result will appear here." %>
                </p>
            </div>
        </div>
    </div>


    <div class="fixed-input-container mt-4">
        <nav class="navbar navbar-expand-lg navbar-light">
            <a class="navbar-brand ml-5 font-weight-bold" href="#">CodeReviewAI</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="btn btn-outline-dark mr-3 ml-2" href="aboutus.html">About</a>
                    </li>
                </ul>
            </div>
        </nav>

       
        <form action="processInputServlet" method="POST" class="mt-2 mr-5 ml-5">
            <div class="form-group">
                <label for="codeInput">Enter your code or query:</label>
                <textarea id="codeInput" name="codeInput" class="form-control" placeholder="Enter your code here..." required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>

</body>
</html>
