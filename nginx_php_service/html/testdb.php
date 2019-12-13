<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MicroMOO - The microservice-based test MOO</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css">
</head>

<body>
<pre><output>
Welcome to MicroMOO!

<?php
$server_name = "mysql-server:3306";
$user_name = "micromoo_user";
$password = "123";
$conn = new mysqli($server_name, $user_name, $password);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

echo "Connected successfully!!!";

$sql = "SELECT user_id, user_name, user_password FROM micromoo_db.users";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo PHP_EOL . PHP_EOL;
    while($row = $result->fetch_assoc()) {
        echo "user_id: " . $row["user_id"] . " - user_name: " . $row["user_name"] . " - user_password: " . $row["user_password"] . PHP_EOL;
    }
}

$conn->close();
?>

</output></pre>
</body>
</html>
