echo "Register Node"

# Define the file path
set FILE_PATH $PWD/selenium-grid
set CONFIG_FILE_PATH "$FILE_PATH"/config

echo $FILE_PATH


# Start the Selenium Grid nodes
# echo "Start Selenium node 1 . . ."
java -jar "$FILE_PATH"/selenium-server-4.24.0.jar node  --hub http://192.168.1.251:4444 --config "$CONFIG_FILE_PATH/node1.toml"  &

# echo "Start Selenium node 2"
java -jar "$FILE_PATH"/selenium-server-4.24.0.jar node --hub http://192.168.1.251:4444  --config "$CONFIG_FILE_PATH/node2.toml" &


# Wait for a few seconds to ensure all processes are up and running
echo "Waiting for Selenium nodes to start..."
sleep 10
