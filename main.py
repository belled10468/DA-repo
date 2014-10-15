import lexeme
choice = raw_input("Please choose input method\n 1: file 2: keyboard else: exit \n");

if choice == "1":
    fileAddress = raw_input("Please input file address.(It will only read the first line) default: ./file.txt\n")
    if fileAddress: 
        file = open(fileAddress, 'r')
        lexeme.lex(file.readline())
    else:
        file = open("./file.txt", 'r')
        lexeme.lex(file.readline())
    file.close()
elif choice == "2":
    equation = raw_input("Please input equation. Ex: (sum + 47) / total\n")
    if equation:
        lexeme.lex(equation)
    else:
        lexeme.lex("(sum + 47) / total")

