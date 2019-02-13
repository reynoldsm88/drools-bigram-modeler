#!/bin/bash

minishift config set memory 4Gb
minishift config set cpus 3

minishift addons install --defaults
minishift addons enable admin-user
minishift addons apply admin-user

minishift start

