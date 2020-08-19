import sys
from typing import Dict

if len(sys.argv) <= 2:
    print("Please write your files name as argument in command line!\nTwo files as argument expected")
    sys.exit(0)

try:
    arg1 = sys.argv[1]
    arg2 = sys.argv[2]
except IndexError as error:
    print("There is something Wrong with your files!")
    sys.exit(0)

try:
    file1 = open(arg1, "r")
    file2 = open(arg2, "r")
except FileNotFoundError as error:
    print("File Not Found!!")
    sys.exit(0)
else:
    contents1 = file1.readlines()
    contents2 = file2.readlines()
    hash2df1: Dict[str, Dict[int, str]] = {}
    hash2df2: Dict[str, Dict[int, str]] = {}

    # load first file
    for Line in contents1:
        Line = Line.split(" ")
        total = int(Line[1]) + int(Line[2]) + int(Line[3].replace("\n", ""))
        hash2df1[Line[0]] = {total: Line[1] + ", " + Line[2] + ", " + Line[3].replace("\n", "")}

    # load second file
    for Line in contents2:
        Line = Line.split(" ")
        total = int(Line[1]) + int(Line[2]) + int(Line[3].replace("\n", ""))
        hash2df2[Line[0]] = {total: Line[1] + ", " + Line[2] + ", " + Line[3].replace("\n", "")}

    # intersection
    if len(hash2df1) < len(hash2df2):
        for i in sorted(hash2df1):
            if i in hash2df2:
                print(i, ": ", arg1, " --> ", hash2df1.get(i), ", ", arg2, " --> ", hash2df2.get(i))
    else:
        for i in sorted(hash2df2):
            if i in hash2df1:
                print(i, ": ", arg1, " --> ", hash2df1.get(i), ", ", arg2, " --> ", hash2df2.get(i))
