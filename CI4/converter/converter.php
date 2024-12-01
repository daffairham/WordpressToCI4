<?php

$txtPath = "news.txt";

$txtFile = file($txtPath);



/*
IDE SCRIPT: 

1. baca file .txt per baris
2. stlh dapet pathnya, dapetin nama halamannya (pisahin rugbyindo.id sama index.html)
3. write method per halaman ke controller (fwrite)
4. buat folder views dengan sesuai nama halamannya (mkdir)
5. masukkin file index.html per halaman ke dalem folder viewsnya
6. write route baru ke Routes.php
7. ???
8. jadi (mereun?)
*/

foreach ($txtFile as $baris) {
    $normalizedPath = str_replace(['../', '\\'], ['', '/'], $baris);
    $pathParts = explode('/', $normalizedPath);
    $folderName = $pathParts[count($pathParts) - 2];
    echo $folderName;
    fopen("../$folderName", "w");
}
