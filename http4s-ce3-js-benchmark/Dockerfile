FROM node:16.17-alpine
WORKDIR /app
COPY target/scala-2.13/http4s-ce3-js-benchmark-opt/main.js /app
EXPOSE 8080
ENTRYPOINT ["node", "main.js"]
