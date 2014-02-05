<?php
$dsn = '127.0.0.1'; 
$user = 'root'; 
$password = '';


$db=mysql_connect($dsn, $user, $password)  or die ("Could not connect to database"); 
	
mysql_select_db('librarydata', $db) or die('Error selecting database : ' . mysql_error());

?>
