<?php

namespace App\Controllers;

class About extends BaseController
{
    public function index(): string
    {
        return view('welcome_message');
    }
}
