//
// Created by gvl19 on 10/11/2021.
//

#include <stdio.h>
#include "variables.h"
/*
int select_player() {
    int player;
    printf("Seleccione jugador:\n");
    scanf_s("%d", &player);
    if ((int) player < 1 | (int) player > 2) {
        printf("Solo puede escoger entre los numeros 1 y 2\n");
        select_player();
    } else {
        return player;
    }
    return 0;
}

struct position set_position() {
    struct position pos;
    printf("Ingrese la fila y la columna donde se encontrara el fantasma\n");
    printf("Fila:\n");
    scanf_s("%d", &pos.row);
    printf("Columna:\n");
    scanf_s("%d", &pos.column);
    if (pos.row < 0 | pos.row >= 13) {
        printf("Son 13 filas, favor introducir un valor del 0 al 12\n");
        set_position();
    } else if (pos.column < 0 | pos.column >= 15) {
        printf("Son 15 columnas, favor introducir un valor del 0 al 14\n");
        set_position();
    } else {
        return pos;
    }
    return pos;
}

struct fruit add_fruit() {
    struct fruit fruit_result;
    int fruit_type;
    printf("Ingrese un numero para seleccionar la fruta que desea:\n");
    printf("1) Cereza\n");
    printf("2) Fresa\n");
    printf("3) Naranja\n");
    printf("4) Manzana\n");
    printf("5) Melon\n");
    scanf_s("%d", &fruit_type);
    if (fruit_type < 1 | fruit_type > 5) {
        printf("Solo puede escoger un numero en el rango del 1 al 5\n");
        add_fruit();
    } else {
        switch (fruit_type) {
            case 1:
                fruit_result.fruit_type = 'C';
                break;
            case 2:
                fruit_result.fruit_type = 'F';
                break;
            case 3:
                fruit_result.fruit_type = 'N';
                break;
            case 4:
                fruit_result.fruit_type = 'M';
                break;
            case 5:
                fruit_result.fruit_type = 'W';
                break;
            default:
                add_fruit();
        }
        printf("Ingrese el puntaje de la fruta:\n");
        scanf_s("%d", &fruit_result.points);
        fruit_result.pos = set_position();
    }

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

const char* select_menu(int player) {
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
        select_menu(player);
    } else {
        struct position pos;
        switch (select) {
            case 1:
                pos = set_position();
                char ghost[5] = {'G', ',', pos.row + '0', ',', pos.column + '0'};
                printf( "%s\n", ghost);
                return ghost;
            case 2:
                pos = set_position();
                char pill[5] = {'M', ',', pos.row + '0', ',', pos.column + '0'};
                printf( "%s\n", pill);
                return pill;
            case 3:
                struct fruit fruit_selected =  add_fruit();
                char fruit_code[12] = {'F', fruit_selected.fruit_type, fruit_selected.points + '0', ',',
                                       fruit_selected.pos.row + '0', ',', fruit_selected.pos.column + '0'};
                printf( "%s\n", fruit_code);
                return fruit_code;
            case 4:
                char speed_level = change_speed() + '0';
                char speed[3] = {'V', ',', speed_level};
                printf( "%s\n", speed);
                return speed;
            case 5:
                select_player();
                break;
            default:
                select_menu(player);

        }
    }
    return (const char *) select;
}

void init_console() {
    int player = select_player();
    select_menu(player);
    init_console();
}*/
