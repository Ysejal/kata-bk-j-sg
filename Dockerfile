FROM jenkins/jenkins:2.303.2-lts-jdk11

USER root

# Installation de Docker CLI
RUN apt-get update && \
    apt-get -y install apt-transport-https \
                       ca-certificates \
                       curl \
                       gnupg-agent \
                       software-properties-common && \
    curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add - && \
    add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/debian $(lsb_release -cs) stable" && \
    apt-get update && \
    apt-get -y install docker-ce-cli

# Installation de Maven
RUN apt-get update && \
    apt-get -y install maven

# Réduire les privilèges de l'utilisateur jenkins
USER jenkins
