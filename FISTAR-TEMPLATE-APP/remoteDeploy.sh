export FISTAR_DEV_SERVER=192.168.3.36
echo "Start Deployment to Development Server @$FISTAR_DEV_SERVER"
echo "Copying Snapshot to Development server @FISTAR"
scp target/FISTAR-TEMPLATE-APP-1.0.0-SNAPSHOT.war fistar@192.168.3.36:~/deployment_snapshots
echo "SCP Done!"
echo "Deploying..."
ssh fistar@192.168.3.36 '~/deployment_snapshots/deploy.sh'
echo "Deploy Done!"
