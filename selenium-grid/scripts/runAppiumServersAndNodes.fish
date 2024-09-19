#!/usr/bin/env fish
echo "Hello Appium"

# Stop any running Appium servers
pkill -9 -f appium

# Define the file path
set FILE_PATH $PWD/selenium-grid
set CONFIG_FILE_PATH "$FILE_PATH"/config

echo $FILE_PATH

# Start the Appium servers using AppleScript
set appium1 "appium server --config '$CONFIG_FILE_PATH/appium1.yml'"
set appium2 "appium server --config '$CONFIG_FILE_PATH/appium2.yml'"
set appium3 "appium server --config '$CONFIG_FILE_PATH/appium3.yml'"
set appium4 "appium server --config '$CONFIG_FILE_PATH/appium4.yml'"
set appium5 "appium server --config '$CONFIG_FILE_PATH/appium5.yml'"

# Run Appium servers in separate Terminal windows
# osascript -e "
# tell application \"Terminal\"
#     do script \"$appium1\"
# end tell" &
#
# osascript -e "
# tell application \"Terminal\"
#     do script \"$appium2\"
# end tell" &

osascript -e "
tell application \"Terminal\"
    do script \"$appium3\"
end tell" &

osascript -e "
tell application \"Terminal\"
    do script \"$appium4\"
end tell" &

osascript -e "
tell application \"Terminal\"
    do script \"$appium5\"
end tell" &

# Wait for the Appium servers to start
echo "Waiting for Appium servers to start..."
sleep 15

# Start the Selenium Grid nodes
# # echo "Start Selenium node 1 . . ."
# java -jar "$FILE_PATH"/selenium-server-4.24.0.jar node --config "$CONFIG_FILE_PATH/node1.toml" &
#
# # echo "Start Selenium node 2"
# java -jar "$FILE_PATH"/selenium-server-4.24.0.jar node --config "$CONFIG_FILE_PATH/node2.toml" &


# echo "Start Selenium node 3"
java -jar "$FILE_PATH"/selenium-server-4.24.0.jar node --config "$CONFIG_FILE_PATH/node3.toml" &

# echo "Start Selenium node 4"
java -jar "$FILE_PATH"/selenium-server-4.24.0.jar node --config "$CONFIG_FILE_PATH/node4.toml" &

# echo "Start Selenium node 5"
java -jar "$FILE_PATH"/selenium-server-4.24.0.jar node --config "$CONFIG_FILE_PATH/node5.toml" &

# Wait for a few seconds to ensure all processes are up and running
echo "Waiting for Selenium nodes to start..."
sleep 10
