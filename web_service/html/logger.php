<?php

class Logger {
    private static $instance = null;

    private function __construct() {}

    public static function getInstance() {
        if (self::$instance == null) {
            self::$instance = new Logger();
        }

        return self::$instance;
    }

    public function logInfo($log_msg) {
        $log_file = 'log_' . date('d-M-Y') . '.log';
        $log_msg = $_SERVER['REMOTE_ADDR'] . ' - ' .date("F j, Y, g:i a") . ' - ' . $log_msg . PHP_EOL;
        file_put_contents($log_file, $log_msg, FILE_APPEND);
    }
}

?>
