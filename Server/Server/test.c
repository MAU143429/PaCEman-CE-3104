//
// Created by gvl19 on 10/11/2021.
//

#include <stdio.h>
#include "variables.h"

int select_player() {
    int player;
    printf("Seleccione jugador:\n");
    scanf_s("%d", &player);
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

struct position add_ghost() {
    struct position pos;
    printf("Ingrese la fila y la columna donde se encontrara el fantasma\n");
    printf("Fila:\n");
    scanf_s("%d", &pos.x);
    printf("Columna:\n");
    scanf_s("%d", &pos.y);
    return pos;
}

struct position add_pill() {
    struct position pos;
    printf("Ingrese la fila y la columna donde se encontrara la pastilla\n");
    printf("Fila:\n");
    scanf_s("%d", &pos.x);
    printf("Columna:\n");
    scanf_s("%d", &pos.y);
    return pos;
}

struct fruit add_fruit() {
    struct fruit fruit_result;
    printf("Ingrese un numero para seleccionar la fruta que desea:\n");
    printf("1) Cereza\n");
    printf("2) Fresa\n");
    printf("3) Naranja\n");
    printf("4) Manzana\n");
    printf("5) Melon\n");
    scanf_s("%d", &fruit_result.fruit_type);
    if (fruit_result.fruit_type < 1 | fruit_result.fruit_type > 5) {
        printf("Solo puede escoger un numero en el rango del 1 al 5\n");
        add_fruit();
    }
    printf("Ingrese el puntaje de la fruta:\n");
    scanf_s("%d", &fruit_result.points);

    return fruit_result;
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
        add_fruit();
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
            case 1:
                add_ghost();
                break;
            case 2:
                add_pill();
                break;
            case 3:
                add_fruit();
                break;
            case 4:
                change_speed();
                break;
            case 5:
                select_player();
                break;
            default:
                select_menu();

        }
    }
    return select;
}

int main() {
    select_player();
    select_menu();
    printf("Gracias.");

    return 0;
};

