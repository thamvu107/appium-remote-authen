echo "Hello Appium"

# Stop any running Appium servers
pkill -9 -f appium

# Define the file path
set FILE_PATH $PWD/selenium-grid
set CONFIG_FILE_PATH "$FILE_PATH"/config

echo $FILE_PATH

# Start the Appium servers using AppleScript
set localAppiumServer "appium server --config '$CONFIG_FILE_PATH/localAppiumServer.yml'"

# Run Appium servers in separate Terminal windows
osascript -e "
tell application \"Terminal\"
    do script \"$localAppiumServer\"
end tell" &

# Wait for a few seconds to ensure all processes are up and running
echo "Waiting for Local Server to start..."
sleep 10
