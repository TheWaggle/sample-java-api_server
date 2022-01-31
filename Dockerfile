FROM openjdk:11-slim-stretch

WORKDIR /opt

RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    git \
    vim \
    && apt-get install sqlite3 libsqlite3-dev -y\
    && apt-get clean && rm -rf /var/lib/apt/lists/*
ENV TZ Asia/Tokyo

COPY . /opt