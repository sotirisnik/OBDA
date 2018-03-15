docker stop $(docker ps -q)
docker rm $(docker ps -a -q)
docker rmi --force true $(docker images -q)