#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char passwd[] = "asd";
char usr_input[4];

void target() {
    printf("You have entered the correct passwd\n");
    exit(0);
}

void prompt(){
	char buf[4];
	gets(buf);
    strncpy(usr_input, buf, 4);
}

int main(){
	prompt();
    if(strcmp(usr_input, passwd) == 0) {
        target();
    }else {
        printf("Wrong passwd!\n");
        exit(1);
    }

	return 0;
}
