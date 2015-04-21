export FISTAR_DEV_SERVER=192.168.1.203
echo "Start Deployment to Development Server @$FISTAR_DEV_SERVER"
echo "Copying Snapshot to Development server @FISTAR"
scp target/FISTAR-SHIELD-UI-1.0.0-SNAPSHOT.war fistar@kronos:~/deployment_snapshots
echo "SCP Done!"
echo "Deploying..."
ssh fistar@kronos '~/deployment_snapshots/deploySHIELD.sh'
echo "Deploy Done!"
