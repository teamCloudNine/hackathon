# Beneficiary Collaboration - Spring Board Service

## Summary

TBD

## DynamoDb Table Setup

We are following a single table model. What that means is that all of our data is stored in a single DynamoDb table.

This allows us to optimize our persistence layer.

We define the PK to be the name of our object, i.e. Beneficiary then we have a sort key that would be the unique identifier of the object,
then we will have a `EntityData` attribute that will provide dynamic data (this will depend on the object it is associated with). 

```bash
aws dynamodb describe-table --table-name beneficary-collaboration-table
```
```json 
{
    "Table": {
        "AttributeDefinitions": [
            {
                "AttributeName": "EntityKey",
                "AttributeType": "S"
            },
            {
                "AttributeName": "EntityType",
                "AttributeType": "S"
            }
        ],
        "TableName": "beneficary-collaboration-table",
        "KeySchema": [
            {
                "AttributeName": "EntityType",
                "KeyType": "HASH"
            },
            {
                "AttributeName": "EntityKey",
                "KeyType": "RANGE"
            }
        ],
        "TableStatus": "ACTIVE",
        "CreationDateTime": "2023-09-21T21:19:37.894000-05:00",
        "ProvisionedThroughput": {
            "LastDecreaseDateTime": "2023-09-21T21:28:57.505000-05:00",
            "NumberOfDecreasesToday": 2,
            "ReadCapacityUnits": 1,
            "WriteCapacityUnits": 1
        },
        "TableSizeBytes": 0,
        "ItemCount": 0,
        "TableArn": "arn:aws:dynamodb:us-east-1:416539513472:table/beneficary-collaboration-table",
        "TableId": "83fd4717-df34-4a81-a29b-95c029d1dd99",
        "TableClassSummary": {
            "TableClass": "STANDARD"
        }
    }
}
```