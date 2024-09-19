#!/usr/bin/env fish
# Quit the Terminal application, closing all Terminal windows
osascript -e '
tell application "Terminal"
    quit
end tell'
