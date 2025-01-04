<?php

if(isset($_POST['vehicle_make']) && isset($_POST['vehicle_model']) && isset($_POST['vehicle_year']) && isset($_POST['vin_number']) && isset($_POST['scheduled_date']) && isset($_POST['user_id']))
    require_once 'conn.php';
    require_once "validate.php";

    $model = validate($_POST['vehicle_model']);
    $year = validate($_POST['vehicle_year']);
    $vinNum = validate($_POST['vin_number']);
    $make = validate($_POST['vehicle_make']);
    $scheduled_date = validate($_POST['scheduled_date']);
    $user_id = validate($_POST['user_id']);

    $statement = $conn->prepare("INSERT INTO vehicle_services (vehicle_model, vehicle_year, vin_number, vehicle_make, scheduled_date, user_id) VALUES (?,?,?,?,?,?)");
    $statement-> bind_param("ssssss", $model, $year, $vinNum, $make, $scheduled_date, $user_id);

    if ($statement->execute()) {
        echo "success";  // Registration successful
    } else {
        echo "failure";  // Registration failed
    }

    $statement -> close();
?>