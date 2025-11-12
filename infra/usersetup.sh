#!/bin/bash

read -p "How many users do you want to configure? " users

for ((i=1; i<=$users; i=i+1))
do
    USER="user$i"
    echo "Setting up $USER"

    oc new-project $USER-apps
    oc adm policy add-role-to-user edit $USER -n $USER-apps

    echo "Setup for $USER done"
done