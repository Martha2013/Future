<?php

include 'connect.php';

$q = mysql_query("select * from booksinstore");

if ($row=mysql_fetch_array($q)) {
	while ($row=mysql_fetch_array($q))

{
       $field1= $row['BookName'];
       $field2= $row['Author'];
       $field3= $row['Location'];

	    echo "$field1#";
	   
}	
} else {
	echo "No records";
}

?>
