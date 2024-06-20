#!/usr/bin/env bash

go get

tinygo build -o vowels.wasm -target wasi main.go