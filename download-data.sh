#!/bin/bash -e

mkdir -p ./data/multinerd

wget https://huggingface.co/datasets/Babelscape/multinerd/resolve/main/train/train_en.jsonl -O ./data/multinerd/train_en.jsonl
wget https://huggingface.co/datasets/Babelscape/multinerd/raw/main/test/test_en.jsonl -O ./data/multinerd/test_en.jsonl
wget https://huggingface.co/datasets/Babelscape/multinerd/raw/main/val/val_en.jsonl -O ./data/multinerd/val_en.jsonl
