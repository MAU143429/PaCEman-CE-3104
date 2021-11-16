//
// Created by gvl19 on 14/11/2021.
//

#ifndef SERVER_CONSOLE_H
#define SERVER_CONSOLE_H

int select_player();

struct position {
    int row;
    int column;
};

struct position set_position();

struct fruit;

struct fruit add_fruit();

int change_speed();

int select_menu();

void init_console();

#endif //SERVER_CONSOLE_H
