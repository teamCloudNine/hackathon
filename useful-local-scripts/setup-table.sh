"#!/bin/bash"

echo "Setting up your dynamodb table..."

aws dynamodb create-table --cli-input-json file://./dynamodb/beneficiary-collaboration-table.json --endpoint-url http://localhost:8000

echo "Done!"