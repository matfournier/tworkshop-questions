# Type Gymnastics Workshop - Mat Fournier 

## Goals

- get used to thinking with types
- get used to having contexts nested within contexts within contexts 
- learn how to attack problems 
- learn how type signatures can help you consider contracts 

The exercises simulates taking a list of requests, batching them, sending batched requests to a server, getting a batched response back, having to unpack the elements of the batched response, and returning.  This is similar to production code that hits the facebook batch API. 

The hairiest type you may run into is something like `List[Future[List[Either[ServiceError, ParsedResponse]]]]` or something along those lines.

## Models

Don't modify these but you will use them:

- Request - a single request, don't dig inside of it.
- BatchRequest - holds a List[Request]
- RawResponse - the raw, unparsed string returned from the server 
- BatchResponse - holds a List[RawResponse]

You will need to parse a `RawResponse` into a `ParsedResponse` using `ParsedResponse.parser(string)` 

## Http

`mf.http.Http` is a dummy service that takes in a `BatchRequest` and returns a `BatchResponse`. The elements will be in the same order going out as coming in.  Do not modify this file but you will need to use the `Http.getBatch` function. 

## Exercises

There are five exercises with tests. Your job is to figure out how to make all the tests pass. 

- You can't modify the tests.  
- Don't modify the models or the Http object 
- You should only modify `Exercise1/2/3/4/5.scala`. 
    - Do not change the response type provided. 
    - The requests have already been batched for you. 
    
It does not matter what string you jam into ServiceError for purposes of the tests.

* Exercise 1 
    - If any Future fails (call to http.getBatch), should return failed future
    - If any RawResponse fails to parse, should return Left(ServiceError)
    - If everything works, return List[ParsedResponse]

* Exercise 2
    - If any Future fails (call to http.getBatch), should return failed future
    - Ignore any RawResponses that fail to parse and keep going
    - Return as many ParsedResponses as you can 

* Exercise 3 
    - As per Exercise1 except collect _all_ the parsing failures

* Exercise 4
    - If any Future fails (call to http.getBatch), ignore it and keep going
    - Ignore any RawResponses that fail to parse and keep going
    - Return as many ParsedResponses as you can 

* Exercise 5
    - If any Future Fails, convert it to a ServiceError
    - Return as many ParsedResponses as you can
    - Collect all the ServiceErrors (failed futures AND parsing failures) 
    - Return the tuple of (List[ServiceErrors], List[ParsedResponses])
