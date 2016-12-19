### create container based on Dockerfile
`docker build -t datetimeserver .`

### start container, exposing port 6060 to us / the client
`docker run -d -p 6060:6060 --name dtserver datetimeserver`

### stop and remove so you can re run
`docker stop dtserver`

`docker rm dtserver`


