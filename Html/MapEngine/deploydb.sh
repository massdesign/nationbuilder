#!/bin/bash
source ~/.dbpasswd

cd ../../Ruby/NationBuilderService/ && rake db:drop
cd ../../Ruby/NationBuilderService/ && rake db:create
#cd ../../Exports && mysql -v -u root -p$NB_PASSWD NationBuilder < unittest.sql
#cd ../../Exports && mysql -v -u root -p$NB_PASSWD NationBuilder < NB14.sql
cd ../../Exports && mysql -v -u root -p$NB_PASSWD NationBuilder < NB46.sql
