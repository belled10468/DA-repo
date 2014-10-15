EOF = 0
INT_LIT = 10
IDENT = 11
ASSIGN_OP = 20
ADD_OP = 21
SUB_OP = 22
MULT_OP = 23
DIV_OP = 24
LEFT_PAREN = 25
RIGHT_PAREN = 26

lexeme = []

def lex(targetString):
    nextToken = -1
    previousIndex = 0
    targetIndex = 0
    
    targetString = targetString.replace(" ","")
    while(targetIndex < len(targetString)):
        targetChar = targetString[targetIndex]
        if isAlpha(targetChar):
            lexeme.append(targetChar)
            if targetIndex < (len(targetString) - 1):
                nextChar = targetString[targetIndex + 1]
                
                while (isAlpha(nextChar) or isDigit(nextChar)):
                    lexeme.append(nextChar)
                    targetIndex+=1
                    if targetIndex < (len(targetString) - 1):
                        nextChar = targetString[targetIndex + 1]
                    else:
                        break
            nextToken = IDENT
        elif isDigit(targetChar):
            lexeme.append(targetChar)
            if targetIndex < (len(targetString) - 1):
                nextChar = targetString[targetIndex + 1]
                
                while isDigit(nextChar):
                    lexeme.append(nextChar)
                    targetIndex+=1
                    if targetIndex < (len(targetString) - 1):
                        nextChar = targetString[targetIndex + 1]
                    else:
                        break
            nextToken = INT_LIT
        else:
            nextToken = noDigitAndAlphaCharAnalyzer(targetChar)
        print "Next token is " + str(nextToken) + ", Next lexeme is " + ''.join(lexeme[previousIndex : targetIndex+1])
        #print 
        #for x in range(previousIndex, targetIndex+1):        
        #    print str(lexeme[x])
        #print "\n"            
        targetIndex += 1
        previousIndex = targetIndex    
    print "Next token is " + "-1" + ", Next lexeme is EOF"
				    
					   
def noDigitAndAlphaCharAnalyzer(targetChar):
		nextToken = 0
		if targetChar ==  '(':
			lexeme.append(targetChar)
			nextToken = LEFT_PAREN
			
		elif targetChar ==  ')':
			lexeme.append(targetChar)
			nextToken = RIGHT_PAREN
			
		elif targetChar ==  '+':
			lexeme.append(targetChar)
			nextToken = ADD_OP
			
		elif targetChar ==  '-':
			lexeme.append(targetChar)
			nextToken = SUB_OP
			
		elif targetChar ==  '*':
			lexeme.append(targetChar)
			nextToken = MULT_OP
			
		elif targetChar ==  '/':
			lexeme.append(targetChar)
			nextToken = DIV_OP
			
		else:
			lexeme.append(targetChar)
			nextToken = EOF					
		return nextToken
	
def isAlpha(targetChar):
    return (65 <= ord(targetChar) and ord(targetChar) <= 90) \
	or (97 <= ord(targetChar) and ord(targetChar) <= 122)
def isDigit(targetChar):
    return (48 <= ord(targetChar) and ord(targetChar) <= 57)