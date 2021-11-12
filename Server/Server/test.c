//
// Created by gvl19 on 10/11/2021.
//

#include <stdio.h>
#include "variables.h"

int seleccionar_jugador() {
    int player;
    printf("Seleccione jugador:\n");
    scanf("%d", &player);
    if ((int) player < 1 | (int) player > 2) {
        printf("Solo puede escoger entre los numeros 1 y 2\n");
        seleccionar_jugador();
    }
    return player;
}

int * seleccionar_fruta() {
    int tipo_fruta;
    int valor;
    static int fruta[2];
    printf("Ingrese un numero para seleccionar la tipo_fruta que desea:\n");
    printf("1) Cereza\n");
    printf("2) Fresa\n");
    printf("3) Naranja\n");
    printf("4) Manzana\n");
    printf("5) Melon\n");
    scanf_s("%d", &tipo_fruta);
    if (tipo_fruta < 1 | tipo_fruta > 5) {
        printf("Solo puede escoger un numero en el rango del 1 al 5\n");
        seleccionar_fruta();
    }
    printf("Ingrese el valor de la tipo_fruta:\n");
    scanf_s("%d", &valor);
    fruta[0] = tipo_fruta;
    fruta[1] = valor;

    return fruta;
}

int cambiar_velocidad() {
    int speed;
    printf("Seleccione el nuevo valor de la velocidad:\n");
    printf("1) Baja\n");
    printf("2) Media\n");
    printf("3) Alta\n"); //definir valor aleatorio
    scanf_s("%d", &speed);

    return speed;
}

int seleccionar_opcion() {
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
        seleccionar_opcion();
    } else {
        switch (select) {
            case 3: seleccionar_fruta();
                break;
            case 4: cambiar_velocidad();
                break;
            default: seleccionar_opcion();

        }
    }
    return select;
}

int main() {
    int player;
    int select;
    player = seleccionar_jugador();
    select = seleccionar_opcion();

    printf("%d, %d", player, select);

    return 0;
};

