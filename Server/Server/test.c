//
// Created by gvl19 on 10/11/2021.
//

#include <stdio.h>
#include "variables.h"

int select_player() {
    int player;
    printf("Seleccione jugador:\n");
    scanf("%d", &player);
    if ((int) player < 1 | (int) player > 2) {
        printf("Solo puede escoger entre los numeros 1 y 2\n");
        select_player();
    }
    return player;
}

struct position {
    int x;
    int y;
};

struct fruit {
    struct position pos;
    int fruit_type;
    int points;
};

struct fruit select_fruit() {
    int fruit_type;
    int points;
    struct fruit fruit_return;
    printf("Ingrese un numero para seleccionar la fruit_type que desea:\n");
    printf("1) Cereza\n");
    printf("2) Fresa\n");
    printf("3) Naranja\n");
    printf("4) Manzana\n");
    printf("5) Melon\n");
    scanf_s("%d", &fruit_return.fruit_type);
    if (fruit_return.fruit_type < 1 | fruit_return.fruit_type > 5) {
        printf("Solo puede escoger un numero en el rango del 1 al 5\n");
        select_fruit();
    }
    printf("Ingrese el points de la fruit_type:\n");
    scanf_s("%d", &fruit_return.points);

    return fruit_return;
}

int change_speed() {
    int speed;
    printf("Seleccione el nuevo valor de la velocidad:\n");
    printf("1) Baja\n");
    printf("2) Media\n");
    printf("3) Alta\n"); //definir valor aleatorio
    scanf_s("%d", &speed);
    if (speed < 1 | speed > 3) {
        printf("Solo puede escoger un numero en el rango del 1 al 5\n");
        select_fruit();
    }
    return speed;
}

int select_menu() {
    int select;
    printf("Ingrese un numero para definir lo que desea hacer:\n");
    printf("1) Crear fantasma\n");
    printf("2) Crear pastilla\n");
    printf("3) Crear fruta\n"); //definir valor aleatorio
    printf("4) Cambiar velocidad de fantasmas\n"); //baja, media, alta
    printf("5) Salir\n");
    scanf_s("%d", &select);
    if (select < 1 | select > 5) {
        printf("Solo puede escoger un numero en el rango del 1 al 5\n");
        select_menu();
    } else {
        switch (select) {
            case 3:
                select_fruit();
                break;
            case 4:
                change_speed();
                break;
            default:
                select_menu();

        }
    }
    return select;
}

int main() {
    int player;
    int select;
    player = select_player();
    select = select_menu();

    printf("%d, %d", player, select);

    return 0;
};

