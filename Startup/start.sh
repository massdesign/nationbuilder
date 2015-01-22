#!/bin/bash
LOG_LOCATION=$(pwd)
sudo systemctl start mysqld
sudo systemctl start nginx
sudo systemctl start php-fpm
# start rails server
cd ../Ruby/NationBuilderService/ && rails server >>  $LOG_LOCATION/nb_db_log 2>&1 &
cd ../Html/MapEngine/ && ./pythonserver.py >> $LOG_LOCATION/nb_rails_log 2>&1 &




