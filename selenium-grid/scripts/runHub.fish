
#!/usr/bin/env fish

echo Hello Grid

# Stop any running Selenium processes
# pkill -9 -f selenium

# sleep 1

# Define the file path
set FILE_PATH $PWD/selenium-grid

# Start the Selenium hub
# java -jar "$FILE_PATH"/selenium-server-4.24.0.jar hub &
# java -jar $FILE_PATH/selenium-server-4.24.0.jar hub --host 2402:800:6341:fc8f:7136:edae:748a:86db%en0 --port 4444 &
# java -jar $FILE_PATH/selenium-server-4.24.0.jar hub --host 192.168.1.6 --port 4444 &

java -jar $FILE_PATH/selenium-server-4.24.0.jar hub --host [2402:800:6341:fc8f:7136:edae:748a:86db] --port 4444 &


# sleep 3

sleep 5

