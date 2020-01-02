<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MicroMOO - The microservice-based test MOO</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
<pre><output>
<?php
    require_once('logger.php');

    $logger = Logger::getInstance();

    if ((empty($_GET['user_name']) && $_GET['user_name'] != "0") ||
        (empty($_GET['user_password']) && $_GET['user_password'] != "0") ||
        (empty($_GET['user_type']) && $_GET['user_type'] != "0")) {
        echo "The user could not be created: missing data." . PHP_EOL;
        $logger->logInfo("The user could not be created: missing data.");
    } else {
        $logger->logInfo("User is going to be created: name=" . $_GET['user_name'] . ", type=" . $_GET['user_type']);
        $user_data_array = array('user_name' => $_GET['user_name'], 'user_password' => $_GET['user_password'], 'user_type' => $_GET['user_type']);
        $post_string = http_build_query($user_data_array, '', '&');

        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, 'http://users-service:4567/users');
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "POST");
        curl_setopt($curl, CURLOPT_POSTFIELDS, $post_string);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        $response = curl_exec($curl);

        $logger->logInfo("User creation response: " . $response);

        preg_match('/{"user_id":"(\d+)","user_name":"([^"]+)","user_password":"([^"]+)","user_type":"([^"]+)"}/', $response, $matches);

        if (count($matches) > 0) {
            echo "  User has been sucessfully created: " . PHP_EOL . PHP_EOL;
            echo "    ID ....: " . $matches[1] . PHP_EOL;
            echo "    Name ..: " . $matches[2] . PHP_EOL;
            echo "    Pass ..: " . $matches[3] . PHP_EOL;
            echo "    Type ..: " . (($matches[4] == "0")?"Admin":"Regular") . PHP_EOL;
        } else
            echo "  User could not be created." .PHP_EOL;
    }
?>

<a href="users_admin.html"><< Back...</a>
</output></pre>
</body>
</html>
