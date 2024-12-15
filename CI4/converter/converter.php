<?php

$txtPath = "news.txt";
$controllerPath = "../app/Controllers/News.php";
$routesPath = "../app/Config/Routes.php";
$viewsPath = "../app/Views/";

$txtFile = file($txtPath);

$controllerTemplate = "<?php
namespace App\Controllers;
class News extends BaseController
{";
file_put_contents($controllerPath, $controllerTemplate);

foreach ($txtFile as $baris) {
    $filePath = str_replace(['../', '\\'], ['', '/'], $baris);
    $splittedFilePaths = explode('/', $filePath);

    $folderName = $splittedFilePaths[count($splittedFilePaths) - 2];

    $viewFolderPath = $viewsPath . "/news/" . $folderName;
    if (!is_dir($viewFolderPath)) {
        mkdir($viewFolderPath);
    }

    $sourceHtmlPath = "D:/Kuliah/Proyek Informatika/rugbyindonesia.or.id/" . $folderName . "/" . "index.html";
    $destination = $viewFolderPath . "/index.php";
    if (file_exists($sourceHtmlPath)) {
        copy($sourceHtmlPath, $destination);
    }

    $normalizedFolderName = str_replace('-', '_', $folderName);
    $pageFunctions = "
    public function page_$normalizedFolderName()
    {
        return view('news/$folderName/index');
    }
";

    file_put_contents($controllerPath, $pageFunctions, FILE_APPEND);

    $pagesRoute = "\$routes->get('/$folderName', 'News::page_$normalizedFolderName');\n";
    file_put_contents($routesPath, $pagesRoute, FILE_APPEND);
}

file_put_contents($controllerPath, "\n}\n", FILE_APPEND);
