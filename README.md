# SoundCloud Bot
This bot is based on Selenium and Selenium Grid to perform automated tasks on SoundCloud. 

## Getting Started
Make sure to have the correct driver for the browser you prefer to use installed and the location mentioned in the Selenium Grid driver initialization. 

There are three ways to start the test.
1. Using python: if you prefer python as your coding language, you can use [Pyjnius](https://github.com/kivy/pyjnius). Please check main.py file for instructions.

2. Using GUI: there's a sample class FrameMain.java to create a GUI and call StreamMain.java file. You can setup the appropriate fields and call the functions accordingly to initiate tests.

3. Directly: to start a test directly, use Stream.java file. 


## Setting up
All the steps requires three information:
1. Number of threads
2. Hub host
3. Hub port (1)
4. Node port (based on number of nodes)

The function automatically allocates the tasks to different nodes. If any of the provided port is busy, the bot will automatically select the next available port.

### Closing the test
There are two steps involved in shutting down the tests
1. Stopping the running threads
2. Calling a POST or GET request to 
```
"http://" + hub_host + ":" + hub_port +"/lifecycle-manager?action=shutdown" 
```
where hub_port is the host address of your hub and hub_port is the port number of your host.


