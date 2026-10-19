# ARCHIVO DE PRUEBA: Dockerfile mal configurado a propósito para validar Checkov.
FROM ubuntu:latest
ADD https://ejemplo.com/script.sh /tmp/script.sh
RUN apt-get update && apt-get install -y curl
EXPOSE 22
CMD ["/bin/bash", "/tmp/script.sh"]
