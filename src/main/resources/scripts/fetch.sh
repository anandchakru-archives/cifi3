#!/usr/bin/env bash
[ $# -ne 2 ] && { echo "Usage: $0 [url] [file]"; echo "Usage: $0 https://api.github.com/repos/USERNAME/REPO_NAME/releases/assets/asset_id?access_token=API_TOKEN /var/www/file.jar"; exit 1; }
echo "name : fetch-v1.0"
echo "url  : $1"
echo "file : $2"
curl -o "$1" -LOJH 'Accept: application/octet-stream' "$2"