<?php

namespace App\Controllers;

class News extends BaseController
{
    public function index(): string
    {
        return view('welcome_message');
    }
}
