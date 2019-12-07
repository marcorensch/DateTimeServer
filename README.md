### create container based on Dockerfile
`docker build -t datetimeserver .`

### start container, exposing port 6060 to us / the client
`docker run -d -p 6060:6060 --rm --name dtserver datetimeserver`

### stop container
you may rerun it later at any time using the `docker run` command from above. \
`docker stop dtserver`
