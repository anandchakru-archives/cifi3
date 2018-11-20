#!/usr/bin/env bash
[ $# -ne 2 ] && { echo "Usage: $0 [file] [profiles]"; echo "Usage: $0 /var/www/file.jar prod,default,otherprofile"; exit 1; }
echo "name : start-v1.0"
nohup java -jar -Dspring.profiles.active="$2" -Dlogback-debug=true -Dserver.port=8080 "$1" &