docker pull redis/redis-stack-server:7.2.0-v2
docker run -d --name redis-stack-server -p 6379:6379 redis/redis-stack-server:7.2.0-v2

