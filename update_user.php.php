<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    require_once 'conn.php'; 

    $user_id = $_POST['user_id'];

    // Build the SQL query dynamically based on provided fields
    $updates = [];

    if (!empty($_POST['Name'])) {
        $updates[] = "Name = '" . mysqli_real_escape_string($conn, $_POST['Name']) . "'";
    }
    if (!empty($_POST['VATNumber'])) {
        $updates[] = "VATNumber = '" . mysqli_real_escape_string($conn, $_POST['VATNumber']) . "'";
    }
    if (!empty($_POST['phone'])) {
        $updates[] = "phone = '" . mysqli_real_escape_string($conn, $_POST['phone']) . "'";
    }
    if (!empty($_POST['password'])) {
        $updates[] = "password = '" . mysqli_real_escape_string($conn, $_POST['password']) . "'";
    }
    

    if (count($updates) > 0) {
        // Combine all updates into a single query
        $update_query = implode(", ", $updates);
        $query = "UPDATE users SET $update_query WHERE user_id = '$user_id'";

        $result = mysqli_query($conn, $query);

        if ($result) {
            echo json_encode(["message" => "Account updated successfully"]);
        } else {
            echo json_encode(["message" => "Error updating account"]);
        }
    } else {
        echo json_encode(["message" => "No fields to update"]);
    }
}
?>
