cmake_minimum_required(VERSION 3.19)
project(Server C)

set(CMAKE_C_STANDARD 99)
SET(CMAKE_C_FLAGS -pthread)

add_executable(Server Server/main.c)