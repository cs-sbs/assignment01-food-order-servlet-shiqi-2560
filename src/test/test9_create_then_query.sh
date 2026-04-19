#!/bin/bash

set -euo pipefail
BASE_URL="http://localhost:8080"

# 创建订单并捕获响应中的订单 ID
response=$(curl -s -X POST $BASE_URL/order \
  -d "customer=Charlie" \
  -d "food=Noodles" \
  -d "quantity=3")

# 从响应中提取订单 ID（格式："Order Created: 1003"）
order_id=$(echo "$response" | sed 's/Order Created: //')

# 检查是否成功获取订单 ID
if [[ -z "$order_id" ]]; then
  echo "FAIL: could not get order ID from response"
  exit 1
fi

# 查询刚创建的订单
tmp="$(mktemp)"
status=$(curl -s -o "$tmp" -w "%{http_code}" "$BASE_URL/order/$order_id")
body="$(cat "$tmp")"
rm -f "$tmp"

if [[ "$status" == "200" ]] && [[ "$body" == *"Charlie"* ]]; then
  echo "PASS: create then query works"
  exit 0
else
  echo "FAIL: create then query failed"
  exit 1
fi
