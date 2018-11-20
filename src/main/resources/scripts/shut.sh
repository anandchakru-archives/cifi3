#!/usr/bin/env bash
echo "name : shut-v1.0"
echo "pid using 8080"
fuser 8080/tcp
echo "killing 8080"
fuser -k 8080/tcp
echo "done"