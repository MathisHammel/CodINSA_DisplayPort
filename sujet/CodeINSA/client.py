#!/usr/bin/env python
# coding: utf-8

import socket

hote = "localhost"
port = 8080

socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket.connect((hote, port))
print "Connection on {}".format(port)
serverMess = socket.recv(50)
print("connection done"+serverMess)

socket.send("OK\n")
for i in range(0,20):
    inp=raw_input("wait for input player :\n")
    print("send start")
    socket.send(inp+"\n")
    print("send end")
    result =  socket.recv(5000)
    print ("Received: "+result)

print "Close"
socket.close()