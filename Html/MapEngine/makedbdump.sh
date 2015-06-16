#!/bin/bash
source  ~/.dbpasswd
mysqldump -h localhost -u root -p$NB_PASSWD NationBuilder > $1 

