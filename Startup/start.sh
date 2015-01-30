#!/bin/bash
LOG_LOCATION=$(pwd)

MODE=$1

RAILS_SERVER_PID=$(pgrep -f "ruby bin/rails server")
PYTHON_SERVER_PID=$(pgrep -f "/usr/bin/python3 ./pythonserver.py")
if [ "$MODE" = "start" ]; then 
	echo "start gedaan"
fi 
if [[ $RAILS_SERVER_PID ]]; then 
	echo "rails server is already running on pid :  $RAILS_SERVER_PID"


fi

if [[ $PYTHON_SERVER_PID ]]; then
	echo "python server is already running on pid :  $PYTHON_SERVER_PID"
fi
# TODO: maybe add possible error handling here if they fail to start, further let process management be handled by systemd 
sudo systemctl start mysqld #>>  $LOG_LOCATION/nb_db_log 2>&1 &
sudo systemctl start nginx #>>  $LOG_LOCATION/nb_db_log 2>&1 &
sudo systemctl start php-fpm #>>  $LOG_LOCATION/nb_db_log 2>&1 &

# start rails server
cd ../Ruby/NationBuilderService/ && rails server >>  $LOG_LOCATION/nb_db_log 2>&1 &
cd ../Html/MapEngine/ && ./pythonserver.py >> $LOG_LOCATION/nb_rails_log 2>&1 &
echo "NB system succesfully started..."




