<?php

if (isset($_POST['email']) && isset($_POST['password'])) {
    require_once "conn.php";  // Make sure this file has your DB connection details
    require_once "validate.php";  // This should sanitize input data
    
    $email = validate($_POST['email']);
    $password = validate($_POST['password']);
    
    // Correct table name: users
    $stmt = $conn->prepare("SELECT user_id, name, email, password FROM users WHERE email = ?");
    $stmt->bind_param("s", $email);
    $stmt->execute();
    $result = $stmt->get_result();

    // Check if the user exists and verify the password
    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();

        // Verify the hashed password
        if (password_verify($password, $row['password'])) {
            // Send back user data as JSON
            echo json_encode([
                "success" => true,
                "user_id" => $row['user_id'],
                "name" => $row['name'],
                "email" => $row['email']
            ]);
        } else {
            // Incorrect password
            echo json_encode(["success" => false, "message" => "Invalid credentials"]);
        }
    } else {
        // User not found
        echo json_encode(["success" => false, "message" => "User not found"]);
    }

    $stmt->close();
}
?>