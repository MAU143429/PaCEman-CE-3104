cmake_minimum_required(VERSION 3.19)
project(Server C)

set(CMAKE_C_STANDARD 99)
SET(CMAKE_C_FLAGS -pthread)

add_executable(Server Server/main.c Server/variables.h Server/server.h Server/console.h Server/classify_action.h Server/classify_action.c Server/communication.h)