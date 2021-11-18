//
// Created by yendr on 17/11/2021.
//

#ifndef SERVER_COMMUNICATION_H
#define SERVER_COMMUNICATION_H

#include "server.h"

void sendMessage(char* instruction){
    int sendRes;
    for (int i = 0; i < MAX_CLIENTS; i++)
    {
        if (!clients[i])
        {
            continue;
        }
        sd = clients[i];
        sendRes = send(sd, instruction, strlen(instruction), 0);

        if (sendRes == SOCKET_ERROR)
        {
            printf("No se pudo enviar mensaje al cliente\n", WSAGetLastError());
            shutdown(sd, SD_BOTH);
            closesocket(sd);
            clients[i] = 0;
            curNoClients--;
        }
    }
    instruction[0] = '\0';
}

#endif //SERVER_COMMUNICATION_H
