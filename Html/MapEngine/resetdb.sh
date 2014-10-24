#!/bin/bash
echo "running database reset script"

cd ../../Ruby/NationBuilderService/ && rake db:reset  
