# Application
## Execute
Run build

`./gradlew build`

Execute app

`./gradlew run`

## Endpoints
### UC1 - Flatten input
URI: 
`POST http://localhost:7000/flattener`

Body request:
```
{
	"input": "((10 ; 20 ; 30) ; 40))"
}
```
Expected response: 
```
{
    "flattenResponse": "(10;20;30;40)",
    "depth": 1
}
```

### UC2 - List of successful processed request
URI: `GET http://localhost:7000/flattener`

Expected response:
```
[
    {
        "input": "((10 ; 20 ; 30) ; 40)",
        "result": "(10;20;30;40)",
        "depth": 1,
        "dateTime": "2021-07-25T09:25:20.219753Z"
    },
    {
        "input": "((10 ; ((20 ; (30))) ; (40)))",
        "result": "(10;20;30;40)",
        "depth": 4,
        "dateTime": "2021-07-25T09:52:57.817480Z"
    }
]
```
# Implementation comments
- Parsing, syntax validation and input processing on `FlattenerService` by using list, stacks and tree data structures
- Tree data structure used to represent nested list to be flattened.
- Test coverage evidence on `IntegrationTest` and `FlattenerServiceTest`
- [Javalin](https://javalin.io/) as web framework
