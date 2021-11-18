#include "console.h"
#include "server.h"


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
    scanf_s("%d", &pos.column);
    printf("Columna:\n");
    scanf_s("%d", &pos.row);
    if (pos.column <= 0 | pos.column > 13) {
        printf("Son 13 filas, favor introducir un valor del 0 al 12\n");
        set_position();
    } else if (pos.row <= 0 | pos.row > 15) {
        printf("Son 15 columnas, favor introducir un valor del 0 al 14\n");
        set_position();
    } else {
        return pos;
    }
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

void select_menu(int player) {
    int select;
    printf("Ingrese un numero para definir lo que desea hacer:\n");
    printf("1) Crear fantasma\n");
    printf("2) Crear pastilla\n");
    printf("3) Crear fruta\n");
    printf("4) Cambiar velocidad de fantasmas\n");
    printf("5) Salir\n");
    scanf_s("%d", &select);
    if (select < 1 | select > 5) {
        printf("Solo puede escoger un numero en el rango del 1 al 5\n");
        select_menu(player);
    } else {
        struct position pos;
        char strpos[16];
        int j = 3;
        switch (select) {
            case 1:
                pos = set_position();
                char ghost[16] = {player + '0','G', ','};
                sprintf(strpos, "%d", pos.row);
                for (int i = 0; strpos[i]; ++i) {
                    ghost[j] = strpos[i];
                    j++;
                }
                ghost[j] = ',';
                j++;
                sprintf(strpos, "%d", pos.column);
                for (int i = 0; strpos[i]; ++i) {
                    ghost[j] = strpos[i];
                    j++;
                }
                ghost[j] = '/';
                printf( "%s\n", ghost);
                sendMessage(ghost);
                break;
            case 2:
                pos = set_position();
                char pill[16] = {player + '0','M', ',',};
                sprintf(strpos, "%d", pos.row);
                for (int i = 0; strpos[i]; ++i) {
                    pill[j] = strpos[i];
                    j++;
                }
                pill[j] = ',';
                j++;
                sprintf(strpos, "%d", pos.column);
                for (int i = 0; strpos[i]; ++i) {
                    pill[j] = strpos[i];
                    j++;
                }
                pill[j] = '/';
                printf( "%s\n", pill);
                sendMessage(pill);
                break;
            case 3:
                struct fruit fruit_selected =  add_fruit();
                char points[128];
                sprintf(points, "%d", fruit_selected.points);
                char fruit_code[256] = {player + '0', 'F', fruit_selected.fruit_type};
                for (int i = 0; points[i]; ++i) {
                    fruit_code[j] = points[i];
                    j++;
                }
                fruit_code[j] = ',';
                j++;
                sprintf(strpos, "%d", fruit_selected.pos.row);
                for (int i = 0; strpos[i]; ++i) {
                    fruit_code[j] = strpos[i];
                    j++;
                }
                fruit_code[j] = ',';
                j++;
                sprintf(strpos, "%d", fruit_selected.pos.column);
                for (int i = 0; strpos[i]; ++i) {
                    fruit_code[j] = strpos[i];
                    j++;
                }
                fruit_code[j] = '/';
                printf( "%s\n", fruit_code);
                sendMessage(fruit_code);
                break;
            case 4:
                char speed_level = change_speed() + '0';
                char speed[5] = {player + '0','V', ',', speed_level, '/'};
                printf( "%s\n", speed);
                sendMessage(speed);
                break;
            case 5:
                select_player();
                break;
            default:
                select_menu(player);
                break;
        }
    }
}

void init_console() {
    int player = select_player();
    select_menu(player);
    init_console();
}


void messageReceive(char *messageType, int client){
    char instruction = messageType[1]; //1U,1,2
    char player = messageType[0];
    //printf("%s", "FUNCIONA");

    if(player == 'P'){
        if(client > 1){
            //printf("%s", "FUNCIONA >1");
            char user[3] = {'C','2','/'};
            //printf("%s", user);
            sendMessage(user);
        }else{
            //printf("%s", "FUNCIONA C1");
            char user[3] = {'C','1','/'};
            //printf("%s", user);
            sendMessage(user);}
    }
    else if(instruction == 'U'){

        sendMessage(messageType);
    }
}


// Funcion que corre en un hilo aparte para ejecutar el servidor
static DWORD WINAPI serverThread(void *threadParams)
{
    start_server();

    return 0;
}

static DWORD WINAPI consoleThread(void *threadParams)
{
    init_console();
    return 0;
}

int main()
{
    message[0] = '\0'; // Inicializa el mensaje que se envia a todos los clientes
    char input[BUFLEN];
    DWORD threadDescriptor;
    DWORD consoleThreadDescriptor;
    CreateThread(NULL, 0, serverThread, NULL, 0, &threadDescriptor); // Hilo para el servidor
    //CreateThread(NULL, 0, consoleThread, NULL, 0, &consoleThreadDescriptor); // Hilo para el servidor

    //messageReceive("hola");

    /*while (running)
    {
        scanf_s("%s", input, BUFLEN);
        if (strcmp("close", input) == 0)
        {
            running = 0;
        }
        else
        {
            strcpy_s(message, BUFLEN, input);
        }
        fflush(stdout);
    }
    printf("Cerrando el servidor...\n");
    Sleep(1000);*/
    Sleep(1000);
    init_console();
    return 0;
}
