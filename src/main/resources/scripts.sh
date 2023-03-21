start-all-svcs.sh -- it will start services in order of dependencies


docker run --name=api-gateway-svc --net=host -d -p 9090:9090 icredittechnocrats/api-gateway-svc:latest
docker run --name=rahasya-svc --net=host -d -p 8080:8080 gsasikumar/forwardsecrecy:latest
docker run --name=aa-svc --net=host -p 9092:9092 -v $(pwd)/aa-svc-logs:/logs -d -e JAVA_TOOL_OPTIONS='-Duser=fiu_aa_testing -Dpwd=fiu_aa_testing -DCLIENT_API_KEY=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IkdPTDAxMzQiLCJ0eXBlIjoiRklVIiwiaWF0IjoxNjc1NDA4MDM1LCJleHAiOjE2OTI2ODgwMzV9.8-XuwlHbCMhtrs0y7anY1PlbKpYZzTtvDQ0lMF9iMDI' icredittechnocrats/aa-svc:latest
docker run --name=ui-svc --net=host -p 9091:9091 -v $(pwd)/ui-svc-logs:/logs -d -e JAVA_TOOL_OPTIONS='-Duser=fiu_aa_testing -Dpwd=fiu_aa_testing' icredittechnocrats/ui-svc:latest

stop-all-svcs.sh

docker stop ui-svc
docker stop aa-svc
docker stop rahasya-svc
docker stop api-gateway-svc

remove-all-images.sh

docker rm ui-svc
docker rm aa-svc
docker rm rahasya-svc
docker rm api-gateway-svc
