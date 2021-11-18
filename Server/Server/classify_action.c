#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "classify_action.h"

/*C substring function: It returns a pointer to the substring */

char *substring(char *string, int position, int length)
{
    char *p;
    int c;

    p = malloc(length+1);

    if (p == NULL)
    {
        exit(1);
    }

    for (c = 0; c < length; c++)
    {
        *(p+c) = *(string+position-1);
        string++;
    }

    *(p+c) = '\0';

    return p;
}

void message_receive(char *message_type){
    char instruction = message_type[0];
    char player = message_type[1];

    //printf("%s", "FUNCIONA");

    if(instruction == 'P'){
        sendMessage("C2");
        /*if(clients == 1){
            sendMessage("C1");
        }
        else{
            sendMessage("C2");
        }*/
    }
    else if(instruction == 'O'){
        if(player == '1'){
            //enviar O1
        }
        else{
            //enviar O22
        }
    }
    else if(instruction == 'U'){
        //enviar el message_type  "U1,x,y"

    }
    else if(instruction == 'C'){ //"C1"
        if(player == '1'){
            score_1+=10;

        }
        else{
            score_2+=10;
        }
    }
    else if(instruction == 'M'){
        if(player == '1'){
            score_1+=50;
        }
        else{
            score_2+=50;
        }
    }
    else if(instruction == 'F'){
        char *value = substring(message_type, 4, (strlen(message_type)-3));
        int valueScore;
        sscanf_s(value, "%d", &valueScore);
        if(player == '1'){
            score_1 += valueScore;
        }
        else{
            score_2 += valueScore;
        }
    }
    else if(instruction == 'G'){
        if(player == '1'){
            score_1+=500;
        }
        else{
            score_2+=500;
        }
    }
    else if(instruction == 'L'){
        char status = message_type[2];
        if(player == '1' && status == '+'){
            lives_1++;
        }
        else if(player == '1' && status == '-'){
            lives_1--;
        }
        else if(player == '2' && status == '+'){
            lives_2++;
        }
        else if(player == '2' && status == '-'){
            lives_2--;
        }
    }

    sendMessage("message_type");

}

