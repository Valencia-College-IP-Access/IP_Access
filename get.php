<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept');
header('Access-Control-Allow-Methods: GET, POST, PUT');



$data = '[
 {
 "name": "Student 1",
 "percentage"; "90"
 },
 {
 "name": "Student 2",
 "percentage":"80"
 }
 {
 "name": "Student 3",
 "percentage": "96"
 }
 ]';

echo $data;
    ?>