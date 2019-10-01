#!/usr/bin/env bash
psql -c "drop database plankdb"
psql -c "drop user myuser"
psql -c "create database plankdb"
psql -c "create user myuser with encrypted password 'mypass'"
psql -c "grant all privileges on database plankdb to myuser"