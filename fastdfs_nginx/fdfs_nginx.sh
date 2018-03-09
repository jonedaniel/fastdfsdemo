/usr/bin/fdfs_trackerd /etc/fdfs/tracker.conf start
/usr/bin/fdfs_storaged /etc/fdfs/storage.conf start
/opt/nginx/sbin/nginx
sleep 1
netstat -ntlp
