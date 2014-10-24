#!/bin/bash
echo "running database creation script"

cd ../../Ruby/NationBuilderService/ && rake db:drop  
cd ../../Ruby/NationBuilderService/ && rake db:create  
cd ../../Ruby/NationBuilderService/ && rake db:migrate  
