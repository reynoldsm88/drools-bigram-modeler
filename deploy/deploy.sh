#!/bin/bash

oc new-project drools-lang-modeler
oc create -f https://radanalytics.io/resources.yaml

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


oc new-app --template oshinko-scala-spark-build-dc \
 -p APPLICATION_NAME=drools-bigram-modeler \
 -p GIT_URI=https://github.com/reynoldsm88/drools-bigram-modeler \
 -p GIT_REF="endpoint_impl"
 -p APP_MAIN_CLASS=org.reynoldsm88.bigram.modeler.endpoint.Main \
 -p APP_FILE=bigram-modeler-endpoint-assembly-0.0.1-SNAPSHOT.jar \
 -p SBT_ARGS="clean assembly" \
 -p APP_ARGS="-Xms1024M, -Xmx2048M, -XX:MaxMetaspace=1024M" \
 -p OSHINKO_CLUSTER_NAME="spark"

oc rollout cancel dc drools-bigram-modeler
oc set volume dc/drools-bigram-modeler --add --claim-name=data --type pvc --claim-size=512M --mount-path /etc/data
oc rollout latest dc/drools-bigram-modeler

#POD=$(oc get pod | grep rhdmcentr | awk '{print $1}')
#oc rsync GIT_HOOKS_INTEGRATION/target/hooks/ $POD:$GIT_HOOKS_DIR
# oc get all --selector app=drools-bigram-modeler
# oc delete all --selector app=drools-bigram-modeler

