<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MicroMOO - The microservice-based test MOO</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
<pre><output>
                                    USERS

<?php
    require_once('logger.php');

    function mb_str_fixedw($string, $str_length, $symbol) {
        return mb_substr(str_pad($string, $str_length + strlen($string) - mb_strlen($string), $symbol), 0, $str_length);
    }

    function FormatUserRow($user) {
        return mb_str_fixedw($user['user_id'], 3, ' ') . " " .
               mb_str_fixedw($user['user_name'], 15, ' ') . " " .
               mb_str_fixedw($user['user_password'], 8, ' ') . " " .
               (($user['user_type'] == "0") ? 'Admin' : 'Regular');
    }

    $logger = Logger::getInstance();

    $curl = curl_init();
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);

    if (empty($_GET['user_id']) && $_GET['user_id'] != "0") {
        $logger->logInfo("Get all users list.");
        curl_setopt($curl, CURLOPT_URL, 'http://users-service:4567/users');
        $users = json_decode(curl_exec($curl), true);
        $result = "";

        foreach($users as $user)
            $result = $result . FormatUserRow($user) . PHP_EOL;
    } else {
        $logger->logInfo("Get user with ID = " . $_GET['user_id']);
        curl_setopt($curl, CURLOPT_URL, 'http://users-service:4567/users/' . $_GET['user_id']);
        $user = json_decode(curl_exec($curl), true);

        if (!isset($user)) {
            echo 'No results found for user ID \'' . $_GET['user_id'] . '\'.' . PHP_EOL;
            $logger->logInfo("No user with ID = " . $_GET['user_id'] . " has been found.");
        } else {
            $result = FormatUserRow($user) . PHP_EOL;
            $logger->logInfo("A user with ID = " . $_GET['user_id'] . " has been found.");
        }
    }

    if (strlen($result) > 0) {
        echo "ID  User            Password Type" . PHP_EOL;
        echo "--- --------------- -------- -------" . PHP_EOL;
        echo $result;
    }
?>

<a href="users_admin.html"><< Back...</a>
</output></pre>
</body>
</html>
