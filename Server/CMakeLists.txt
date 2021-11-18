cmake_minimum_required(VERSION 3.19)
project(Server C)

set(CMAKE_C_STANDARD 99)

add_executable(Server Server/main.c Server/variables.h Server/server.h Server/console.h Server/communication.h)