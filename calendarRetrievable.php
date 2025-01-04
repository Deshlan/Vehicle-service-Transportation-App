<?php
// db_connection.php
include 'conn.php';

header('Content-Type: application/json');

// This query  is to fetch booking requests from both transportation and vehicle services tables
$sql = "SELECT scheduled_date, transportID, items FROM transportation
        UNION ALL
        SELECT service_id, vin_number, scheduled_date FROM vehicle_services";

$result = $conn->query($sql);

$data = [];
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
}

// Return data as JSON
echo json_encode($data);

$conn->close();
?>
