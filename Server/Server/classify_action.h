//
// Created by yendr on 16/11/2021.
//

#ifndef SERVER_CLASSIFY_ACTION_H
#define SERVER_CLASSIFY_ACTION_H

int score_1;
int score_2;
int lives_1;
int lives_2;

char *substring(char *string, int position, int length);

void message_receive(char *message_type, int clients);

#endif //SERVER_CLASSIFY_ACTION_H
