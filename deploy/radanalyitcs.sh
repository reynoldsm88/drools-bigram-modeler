#!/bin/bash

OSHINKO_CMD=$(which oshinko)
OC=$(which oc)
echo $OSHINKO_CMD

if [[ -z $OSHINKO_CMD ]]; then
    echo "The oshinko command for installing Spark clusters to Openshift is not available, please see the docs for how to install"
    echo "https://github.com/radanalyticsio/oshinko-cli/blob/master/README.md"
else
    echo "Using oshinko cmd from $OSHINKO_CMD"
fi

if [[ -z $OC ]]; then
    echo "The Openshift command line tool is not installed"
else
    echo "Using oshinko cmd from $OC"
fi

oc new-project drools-lang-modeler
oc create -f https://radanalytics.io/resources.yaml

oshinko create spark

echo "Deploying a Spark cluster.... please wait"
sleep 70

MASTER_RUNNING=$(oc get pod | grep spark-m | awk '{print $3}')
WORKER_RUNNING=$(oc get pod | grep spark-w | awk '{print $3}')

if [[ "Running" == "$MASTER_RUNNING" ]]; then
    echo "Spark master is running"
fi

if [[ "Running" == "$WORKER_RUNNING" ]]; then
    echo "Spark worker is running"
fi


