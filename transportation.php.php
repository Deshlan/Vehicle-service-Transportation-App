<?php

if(isset($_POST['items']) && isset($_POST['weight']) && isset($_POST['size']) && isset($_POST['pickup']) && isset($_POST['dropoff']) && isset($_POST['scheduled_date']) && isset($_POST['user_id']))
    require_once 'conn.php';
    require_once "validate.php";

    $items = validate($_POST['items']);
    $weigth = validate($_POST['weight']);
    $size = validate($_POST['size']);
    $pickup = validate($_POST['pickup']);
    $dropoff = validate($_POST['dropoff']);
    $scheduled_date = validate($_POST['scheduled_date']);
    $user_id = validate($_POST['user_id']);

    $statement = $conn->prepare("INSERT INTO transportation (items, weight_of_items, size_of_items, pickup_location, dropoff_location, scheduled_date, user_id) VALUES (?,?,?,?,?,?,?)");
    $statement-> bind_param("sssssss", $items, $weigth, $size, $pickup, $dropoff, $scheduled_date, $user_id);

    if ($statement->execute()) {
        echo "success";  // Registration successful
    } else {
        echo "failure";  // Registration failed
    }

    $statement -> close();
?>