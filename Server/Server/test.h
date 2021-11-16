//
// Created by yendr on 15/11/2021.
//

#ifndef SERVER_TEST_H
#define SERVER_TEST_H
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

void console();



#endif //SERVER_TEST_H
