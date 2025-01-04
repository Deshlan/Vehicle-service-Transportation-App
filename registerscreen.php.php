<?php

if (isset($_POST['name']) && isset($_POST['phone']) && isset($_POST['vatNumber']) && isset($_POST['email']) && isset($_POST['password'])) {
    require_once "conn.php";
    require_once "validate.php";
    
    // Sanitize input data
    $name = validate($_POST['name']);
    $phone = validate($_POST['phone']);
    $vatNumber = validate($_POST['vatNumber']);
    $email = validate($_POST['email']);
    $password = validate($_POST['password']);

    // Hash the password before storing it
    $hashedPassword = password_hash($password, PASSWORD_DEFAULT);

    // Connecting to users table
    $stmt = $conn->prepare("INSERT INTO users (name, phone, vatNumber, email, password) VALUES (?, ?, ?, ?, ?)");
    $stmt->bind_param("sssss", $name, $phone, $vatNumber, $email, $hashedPassword);
    
    // Execute the query and return success or failure
    if ($stmt->execute()) {
        echo "success";  // Registration successful
    } else {
        echo "failure";  // Registration failed
    }

    $stmt->close();
}
?>
